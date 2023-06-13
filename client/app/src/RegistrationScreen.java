package client.app.src;
import client.app.src.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.app.src.Serverhandler;

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
    private Serverhandler serverhandler = LoginScreen.serverhandler;
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
            public void actionPerformed(ActionEvent e){
                try{
                    register();
                }
                catch (Exception ex){
                    ex.printStackTrace();//
                }
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
    int constate = -1;
    private void register() throws Exception{
        String login = loginField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Пароли не совпадают. Пожалуйста, повторите ввод.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            confirmPasswordField.setText("");
        } else {
            if (serverhandler.s == null){
                constate = serverhandler.newcon();
            }
            switch (constate) {
                case -1:
                    JOptionPane.showMessageDialog(frame, "Не удаётся подключиться к серверу.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    break;
                case -2:
                    System.out.println("Unknown error happened! Handler returned -2");
                    break;
                case 0:
                    int state = serverhandler.registration(login, password, email);
                    if (state == -1){
                        JOptionPane.showMessageDialog(frame, "Пользователь с таким именем уже зарегестрирован!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        serverhandler.id = state;
                        JOptionPane.showMessageDialog(frame, "Регистрация прошла успешно!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                        openApp();
                        break;
                    }
            }
        }
    }

    private void openApp() throws Exception {
        frame.dispose();
        NotesApplication notesApplication = new NotesApplication();
    }

    private void showLoginScreen() {

        frame.dispose();
        LoginScreen loginScreen = new LoginScreen();

    }
}