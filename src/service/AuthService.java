// service/AuthService.java
package service;

import dao.UserDAO;
import model.User;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    // --------------------------
    // Sign Up
    // --------------------------
    public boolean registerUser(String name, String email, String password) {
        if (userDAO.userExists(email)) {
            System.out.println("User already exists with email: " + email);
            return false;
        }

        User newUser = new User(name, email, password);
        userDAO.insertUser(newUser);
        System.out.println("Registration successful!");
        return true;
    }

    // --------------------------
    // Login
    // --------------------------
    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Invalid email or password.");
            return null;
        }
    }
}
