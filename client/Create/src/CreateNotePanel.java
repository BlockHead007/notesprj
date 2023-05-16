import javax.swing.*;
import java.awt.*;

public class CreateNotePanel extends JPanel {
    private JTextArea noteTextArea;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> fontComboBox;
    private JButton saveButton;

    public CreateNotePanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        noteTextArea = new JTextArea();
        noteTextArea.setBackground(Color.LIGHT_GRAY);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());

        fontSizeComboBox = new JComboBox<>(new String[]{"12", "14", "16"});
        fontComboBox = new JComboBox<>(new String[]{"Arial", "Times New Roman", "Courier New"});
        saveButton = new JButton("Сохранить заметку");

        optionsPanel.add(new JLabel("Размер текста:"));
        optionsPanel.add(fontSizeComboBox);
        optionsPanel.add(new JLabel("Шрифт:"));
        optionsPanel.add(fontComboBox);
        optionsPanel.add(saveButton);

        inputPanel.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        inputPanel.add(optionsPanel, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("Заметки");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public String getNoteText() {
        return noteTextArea.getText();
    }
}
