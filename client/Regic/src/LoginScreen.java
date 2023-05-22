package client.Regic.src;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registrationButton;

    public LoginScreen() {
        frame = new JFrame("Добро пожаловать!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        usernameLabel = new JLabel("Логин:");
        usernameField = new JTextField();
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameField.setPreferredSize(new Dimension(200, 30));

        passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordField.setPreferredSize(new Dimension(200, 30));

        loginButton = new JButton("Вход");
        loginButton.setBackground(Color.GREEN);
        loginButton.setPreferredSize(new Dimension(200, 30));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registrationButton = new JButton("Регистрация");
        registrationButton.setBackground(Color.GREEN);
        registrationButton.setPreferredSize(new Dimension(200, 30));
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationScreen();
            }
        });

        int borderRadius = 10;
        Border roundedBorder = new RoundedBorder(borderRadius);
        Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10));

        usernameField.setBorder(compoundBorder);
        passwordField.setBorder(compoundBorder);
        loginButton.setBorder(compoundBorder);
        registrationButton.setBorder(compoundBorder);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(passwordField, constraints);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 5, 5)); // Changed to 3 rows
        buttonsPanel.add(loginButton);
        
        // Add the additional text label
        JLabel registerLabel = new JLabel("Нет аккаунта? Зарегистрируйтесь");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonsPanel.add(registerLabel);

        buttonsPanel.add(registrationButton);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(buttonsPanel, constraints);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        containerPanel.add(panel, BorderLayout.NORTH);

        frame.getContentPane().add(containerPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("Логин: " + username);
        System.out.println("Пароль: " + password);
    }

    private void openRegistrationScreen() {
        frame.dispose();
        RegistrationScreen registrationScreen = new RegistrationScreen();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen();
            }
        });
    }
}
