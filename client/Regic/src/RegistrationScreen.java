import javax.swing.*;
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
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        loginLabel = new JLabel("Логин:");
        loginField = new JTextField();

        emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();

        confirmPasswordLabel = new JLabel("Подтвердите пароль:");
        confirmPasswordField = new JPasswordField();

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

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(registerButton);
        panel.add(existingAccountButton);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
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
        loginScreen.show();  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationScreen();
            }
        });
    }
}

