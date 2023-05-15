import javax.swing.*;
import java.awt.*;

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
    }
}
