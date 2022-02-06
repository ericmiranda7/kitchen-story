package com.kitchenstory.controller;

import com.kitchenstory.model.Product;
import com.kitchenstory.model.User;
import com.kitchenstory.service.AuthService;
import com.kitchenstory.service.ProductService;
import com.kitchenstory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MainController {
    private final ProductService productService;
    private final AuthService authService;
    private final UserService userService;

    public MainController(@Autowired ProductService productService, AuthService authService, UserService userService) {
        this.productService = productService;
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("")
    public String healthCheck() {
        return "Up n' running";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestHeader String Authorization, @RequestBody Product product) {
        authService.authenticate(Authorization);
        return productService.add(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@RequestHeader String Authorization, @PathVariable int id) {
        authService.authenticate(Authorization);
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/login")
    public User handleLogin(@RequestHeader String Authorization) {
        return authService.authenticate(Authorization);
    }

    @PostMapping("/cpass")
    public ResponseEntity<?> changePassword(@RequestHeader String Authorization, @RequestBody Map<String, String> rMap) {
        authService.authenticate(Authorization);
        this.userService.changePassword(rMap.get("username"), rMap.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
