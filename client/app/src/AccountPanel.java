package client.app.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {
    JPanel buttonPanel;
    private Serverhandler serverhandler = LoginScreen.serverhandler;
    public AccountPanel() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Аккаунт");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton changeUsernameButton = new JButton("Изменить имя пользователя");
        changeUsernameButton.setBorder(new RoundedBorder(10, Color.BLACK));
        JButton changePasswordButton = new JButton("Изменить пароль");
        changePasswordButton.setBorder(new RoundedBorder(10,Color.BLACK));

        changeUsernameButton.setBackground(Color.GREEN);
        changePasswordButton.setBackground(Color.GREEN);

        // Установка размера кнопок
        Dimension buttonSize = new Dimension(300, 30);
        changeUsernameButton.setPreferredSize(buttonSize);
        changePasswordButton.setPreferredSize(buttonSize);

        buttonPanel.add(changeUsernameButton, gbc);
        buttonPanel.add(changePasswordButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Добавьте обработчики событий для кнопок здесь

        // ...

        changeUsernameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField newUsernameField = new JTextField(20);
                Object[] message = {"Новый логин:", newUsernameField};
                int option = JOptionPane.showConfirmDialog(null, message, "Изменить имя пользователя",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String msg = newUsernameField.getText();
                    try{serverhandler.chname(msg);}
                    catch (Exception ex){
                        System.out.println("Strange thing is happening!");
                        ex.printStackTrace();
                    }
                }
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPasswordField oldPasswordField = new JPasswordField(20);
                JPasswordField newPasswordField = new JPasswordField(20);
                Object[] message = {
                        "Старый пароль:", oldPasswordField,
                        "Новый пароль:", newPasswordField,
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Изменить пароль",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String oldPassword = new String(oldPasswordField.getPassword());
                    String newPassword = new String(newPasswordField.getPassword());
                    if (oldPassword == newPassword){
                    try{serverhandler.chpass(newPassword);}
                    catch (Exception ex){
                        System.out.println("Strange thing is happening!");
                        ex.printStackTrace();
                    }
                    } else {
                        JOptionPane.showMessageDialog(buttonPanel, "Пароли не совпадают.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        /*deleteAccountButton.addActionListener(new ActionListener() {
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
        });*/
    }

    private void changeUsername(){

    }

}
