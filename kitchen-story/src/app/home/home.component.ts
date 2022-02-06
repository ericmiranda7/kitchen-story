import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Cart } from '../common/models/cart';
import { Product } from '../common/models/product';
import { User } from '../common/models/user';
import { AuthService } from '../common/services/auth.service';
import { CartService } from '../common/services/cart.service';
import { ProductService } from '../common/services/product.service';

const baseUrl = 'http://localhost:8080/api/cpass'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products: Product[] = [];
  searchText: string = '';
  filteredProducts: Product[] = [];
  user: User = { username: '', password: '', admin: false } as any;

  changePassGroup = this.formBuilder.group({
    username: this.user.username,
    password: ''
  })

  cart: Cart = { products: [], items: 0, total: 0 };

  selectedProduct: Product = null as any;

  constructor(private productService: ProductService,
    private cartService: CartService,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.getAllProducts();
    this.getCart();
    this.user = this.authService.user;
  }

  getCart() {
    this.cartService.getCart().subscribe((cart) => {
      this.cart = cart
    });
  }

  getAllProducts() {
    this.productService.getAll().subscribe((products: any) => {
      this.filteredProducts = products;
      this.products = products;
    });
  }

  updateFilter(text: string) {
    this.filteredProducts = this.products.filter(p => p.name.toLowerCase().includes(text) || p.brand.toLowerCase().includes(text) || p.category.toLowerCase().includes(text))
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }

  deleteProduct(product: Product) {
    this.productService.deleteProduct(product.id);
  }

  handleSubmit() {
    const body = { username: this.user.username, password: this.changePassGroup.value.password };
    const b64Pass = btoa(`${this.user.username}:${this.user.password}`)
    const authHeader = `Basic ${b64Pass}`

    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: authHeader
      })
    }
    this.httpClient.post(baseUrl, body, httpOptions)
      .subscribe({
        next: (res) => console.log(res),
        error: (err) => console.log(err),
        complete: () => console.log('done')
      });
  }
}
