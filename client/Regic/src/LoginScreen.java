import javax.swing.*;
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
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        usernameLabel = new JLabel("Логин:");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Вход");
        loginButton.setBackground(Color.GREEN);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registrationButton = new JButton("Нет аккаунта? Зарегистрируйтесь.");
        registrationButton.setBackground(Color.GREEN);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationScreen();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(registrationButton);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
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

