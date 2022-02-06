package com.kitchenstory.service;

import com.kitchenstory.DAO.DAO;
import com.kitchenstory.DAO.UserDAO;
import com.kitchenstory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final DAO<User> userDAO;

    public UserService(@Autowired UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Boolean changePassword(String username, String pass) {
        ((UserDAO) this.userDAO).update(username, pass);
        return true;
    }
}
