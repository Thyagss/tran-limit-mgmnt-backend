package com.transaction.service;

import com.transaction.dao.UserDAO;
import com.transaction.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(String name,
                                String username,
                                String email,
                                String mobile,
                                String password) {

        String role = "USER";

        User user = new User(name, username, email, mobile, password, role);

        return userDAO.saveUser(user);
    }

    public User loginUser(String username, String password) {

        User user = userDAO.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}