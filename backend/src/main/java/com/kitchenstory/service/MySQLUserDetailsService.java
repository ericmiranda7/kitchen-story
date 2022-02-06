/*
package com.kitchenstory.service;

import com.kitchenstory.DAO.UserDAO;
import com.kitchenstory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MySQLUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        System.out.println("kakao macao");
        try {
            user = userDAO.getUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        System.out.println("bajao mastao");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), null);
    }
}
*/
