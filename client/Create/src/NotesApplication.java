import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        //боковая панель
        sidePanel = new JPanel();
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setPreferredSize(new Dimension(150, frame.getHeight()));

        // компоненты боковой панели и их кнопки
        createNoteButton = new JButton("Создать новую заметку");
        noteListButton = new JButton("Список заметок");
        accountButton = new JButton("Аккаунт");

        // компоненты боковой панели
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
        saveButton = new JButton("Сохранить заметку");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        notesTextArea.setBackground(Color.LIGHT_GRAY);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveNote();
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(notesTextArea), BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(new JLabel("Размер текста:"));
        optionsPanel.add(fontSizeComboBox);
        optionsPanel.add(new JLabel("Шрифт:"));
        optionsPanel.add(fontComboBox);
        optionsPanel.add(saveButton);

        mainPanel.add(optionsPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
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
        noteListPanel.addNoteToList("Заметка 1");
        noteListPanel.addNoteToList("Заметка 2");
        noteListPanel.addNoteToList("Заметка 3");

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
                noteListPanel.addNoteToList(noteText); // Добавить новую заметку в список
                showNoteListPanel(); // Вернуться на первую страницу с заметками
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

