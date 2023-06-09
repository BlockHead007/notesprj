package client.app.src;
import client.app.src.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationScreen {
    private JFrame frame;
    private JPanel panel;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton existingAccountButton;

    public RegistrationScreen() {
        frame = new JFrame("Регистрация");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        loginLabel = new JLabel("Логин:");
        loginField = new JTextField();
        loginLabel.setHorizontalAlignment(JLabel.CENTER);

        emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailLabel.setHorizontalAlignment(JLabel.CENTER);

        passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        confirmPasswordLabel = new JLabel("Подтвердите пароль:");
        confirmPasswordField = new JPasswordField();
        confirmPasswordLabel.setHorizontalAlignment(JLabel.CENTER);

        registerButton = new JButton("Зарегистрироваться");
        registerButton.setBackground(Color.GREEN);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        existingAccountButton = new JButton("Уже есть аккаунт?");
        existingAccountButton.setBackground(Color.GREEN);
        existingAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
            }
        });

        /*int borderRadius = 10;
        Border roundedBorder = new RoundedBorder(borderRadius);
        Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));

        loginField.setBorder(compoundBorder);
        emailField.setBorder(compoundBorder);
        passwordField.setBorder(compoundBorder);
        confirmPasswordField.setBorder(compoundBorder);
        registerButton.setBorder(compoundBorder);
        existingAccountButton.setBorder(compoundBorder);*/

        ComponentSizeSetter.setFixedSize(loginField, 200, 30);
        ComponentSizeSetter.setFixedSize(emailField, 200, 30);
        ComponentSizeSetter.setFixedSize(passwordField, 200, 30);
        ComponentSizeSetter.setFixedSize(confirmPasswordField, 200, 30);
        ComponentSizeSetter.setFixedSize(registerButton, 200, 30);
        ComponentSizeSetter.setFixedSize(existingAccountButton, 200, 30);


        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(loginLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(loginField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(emailLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(confirmPasswordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(confirmPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        panel.add(existingAccountButton, constraints);

        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void register() {
        String login = loginField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        System.out.println("Логин: " + login);
        System.out.println("Email: " + email);
        System.out.println("Пароль: " + password);
        System.out.println("Подтверждение пароля: " + confirmPassword);
    }

    private void showLoginScreen() {

        frame.dispose();
        LoginScreen loginScreen = new LoginScreen();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationScreen();
            }
        });
    }
}
