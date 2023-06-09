    package client.app.src;
    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NotesApplication {
    private JFrame frame;
    private JPanel sidePanel;
    private JLabel titleLabel;
    private JTextArea notesTextArea;
    private JButton createNoteButton;
    private JButton accountButton;
    private JButton noteListButton;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> fontComboBox;
    private JButton saveButton;
    private AccountPanel accountPanel;
    private NoteListPanel noteListPanel;
    private CreateNotePanel createNotePanel;

    public NotesApplication() {
        frame = new JFrame("Заметки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Боковая панель
        sidePanel = new JPanel();
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setPreferredSize(new Dimension(150, frame.getHeight()));

        // Компоненты боковой панели и их кнопки
        createNoteButton = new JButton("Создать новую заметку");
        noteListButton = new JButton("Список заметок");
        accountButton = new JButton("Аккаунт");

        // Компоненты боковой панели
        createNoteButton.setForeground(Color.WHITE);
        accountButton.setForeground(Color.WHITE);
        noteListButton.setForeground(Color.WHITE);
        createNoteButton.setBackground(Color.BLACK);
        accountButton.setBackground(Color.BLACK);
        noteListButton.setBackground(Color.BLACK);

        sidePanel.setLayout(new GridLayout(5, 1));
        sidePanel.add(noteListButton);
        sidePanel.add(createNoteButton);
        sidePanel.add(accountButton);

        accountPanel = new AccountPanel();
        noteListPanel = new NoteListPanel();

        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccountPanel();
            }
        });

        noteListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNoteListPanel();
            }
        });

        titleLabel = new JLabel("Заметки");
        notesTextArea = new JTextArea();
        fontSizeComboBox = new JComboBox<>(new String[]{"12", "14", "16"});
        fontComboBox = new JComboBox<>(new String[]{"Arial", "Times New Roman", "Courier New"});
        saveButton = new JButton("Сохранить");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        notesTextArea.setBackground(Color.LIGHT_GRAY);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveNote();
            }
        });

        JPanel optionsPanel = new JPanel(new BorderLayout());
        JPanel topOptionsPanel = new JPanel();
        topOptionsPanel.setLayout(new BoxLayout(topOptionsPanel, BoxLayout.LINE_AXIS));

        topOptionsPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Add some spacing
        topOptionsPanel.add(new JLabel("Размер текста:"));
        topOptionsPanel.add(fontSizeComboBox);
        topOptionsPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Add some spacing
        topOptionsPanel.add(new JLabel("Шрифт:"));
        topOptionsPanel.add(fontComboBox);
        topOptionsPanel.add(Box.createHorizontalGlue());  // Push components to the right

        optionsPanel.add(topOptionsPanel, BorderLayout.NORTH);

        notesTextArea.setBackground(Color.LIGHT_GRAY);
        notesTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add some padding

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(notesTextArea, BorderLayout.CENTER);

        saveButton.setPreferredSize(new Dimension(150, 30));
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFocusPainted(false);
        saveButton.setOpaque(true);
        saveButton.setUI(new RoundedCornerButtonUI(30, 30));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(titleLabel, BorderLayout.NORTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);

        createNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreateNotePanel();
            }
        });
    }

    private void saveNote() {
        String noteText = notesTextArea.getText();
        System.out.println("Заметка сохранена: " + noteText);
    }

    private void showAccountPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(accountPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showNoteListPanel() {
        noteListPanel = new NoteListPanel();
        noteListPanel.addNoteToList("Первая заметка");
        noteListPanel.addNoteToList("Вторая заметка");
        noteListPanel.addNoteToList("Третья заметка");

        noteListPanel.getNoteList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showCreateNotePanel();
                }
            }
        });

        frame.getContentPane().removeAll();
        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(noteListPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showCreateNotePanel() {
        createNotePanel = new CreateNotePanel();

        JButton saveButton = createNotePanel.getSaveButton();
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String noteText = createNotePanel.getNoteText();
                noteListPanel.addNoteToList(noteText);
                showNoteListPanel();
            }
        });

        frame.getContentPane().removeAll();
        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(createNotePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotesApplication();
            }
        });
    }
}
