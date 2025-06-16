// gui/AuthFrame.java
package gui;

import service.AuthService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {
    private final AuthService authService;
    // Add this inside the AuthFrame class
    private LoginSuccessListener loginSuccessListener;

    public void setLoginSuccessListener(LoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }
    // Replace JOptionPane after successful login:

    public AuthFrame() {
        authService = new AuthService();
        setTitle("Quiz System - Login/Signup");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Login", buildLoginPanel());
        tabs.add("Sign Up", buildSignupPanel());

        add(tabs);
        setVisible(true);
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        getContentPane().setBackground(new Color(240, 245, 255));

        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(51, 153, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = String.valueOf(passField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                showError("All fields are required!");
                return;
            }

            try {
                User user = authService.login(email, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome " + user.getName());
                    dispose();
                    if (loginSuccessListener != null) {
                        loginSuccessListener.onLogin(user); // callback here
                    }
                } else {
                    showError("Invalid email or password.");
                }
            } catch (Exception ex) {
                showError("Login failed: " + ex.getMessage());
            }
        });

        return panel;
    }

    private JPanel buildSignupPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton signUpBtn = new JButton("Sign Up");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(signUpBtn);

        signUpBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = String.valueOf(passField.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showError("All fields are required!");
                return;
            }

            if (password.length() < 4) {
                showError("Password must be at least 4 characters.");
                return;
            }

            try {
                boolean success = authService.registerUser(name, email, password);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Signup successful! You can login now.");
                } else {
                    showError("Email already registered.");
                }
            } catch (Exception ex) {
                showError("Signup failed: " + ex.getMessage());
            }
        });

        return panel;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public interface LoginSuccessListener {
        void onLogin(User user);
    }

}
