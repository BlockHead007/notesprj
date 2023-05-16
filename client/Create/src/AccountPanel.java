import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {
    public AccountPanel() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Настройки аккаунта");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10)); //

        JButton changeUsernameButton = new JButton("Изменить имя пользователя");
        JButton changePasswordButton = new JButton("Изменить пароль");
        JButton deleteAccountButton = new JButton("Удалить аккаунт");
        JButton logoutButton = new JButton("Выйти из аккаунта");

        buttonPanel.add(changeUsernameButton);
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(deleteAccountButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        changeUsernameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField newUsernameField = new JTextField(20);
                Object[] message = {"Новый логин:", newUsernameField};
                int option = JOptionPane.showConfirmDialog(null, message, "Изменить имя пользователя",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String newUsername = newUsernameField.getText();
                }
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPasswordField oldPasswordField = new JPasswordField(20);
                JPasswordField newPasswordField = new JPasswordField(20);
                JPasswordField confirmPasswordField = new JPasswordField(20);
                Object[] message = {
                        "Старый пароль:", oldPasswordField,
                        "Новый пароль:", newPasswordField,
                        "Подтвердите пароль:", confirmPasswordField
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Изменить пароль",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    char[] oldPassword = oldPasswordField.getPassword();
                    char[] newPassword = newPasswordField.getPassword();
                    char[] confirmPassword = confirmPasswordField.getPassword();
                }
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Вы действительно хотите удалить аккаунт?",
                        "Удалить аккаунт", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Вы действительно хотите выйти из аккаунта?",
                        "Выйти из аккаунта", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                }
            }
        });
    }
}