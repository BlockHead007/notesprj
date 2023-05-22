package client.Create.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteListPanel extends JPanel {
    private DefaultListModel<String> noteListModel;
    private JList<String> noteList;
    private JButton deleteButton;
    private JButton editButton;

    public NoteListPanel() {
        setLayout(new BorderLayout());

        noteListModel = new DefaultListModel<>();
        noteList = new JList<>(noteListModel);
        JScrollPane scrollPane = new JScrollPane(noteList);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });

        editButton = new JButton("Редактировать");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        add(buttonPanel, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("Список заметок");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }

    public void addNoteToList(String note) {
        noteListModel.addElement(note);
    }

    private void deleteNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            noteListModel.remove(selectedIndex);
        }
    }

    private void editNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            String note = noteListModel.get(selectedIndex);
            String editedNote = JOptionPane.showInputDialog("Введите новый текст заметки", note);
            if (editedNote != null) {
                noteListModel.set(selectedIndex, editedNote);
            }
        }
    }

    public JList<String> getNoteList() {
        return noteList;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Note List Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                NoteListPanel noteListPanel = new NoteListPanel();
                noteListPanel.addNoteToList("Заметка 1");
                noteListPanel.addNoteToList("Заметка 2");
                noteListPanel.addNoteToList("Заметка 3");

                frame.getContentPane().add(noteListPanel);
                frame.setSize(800, 600);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
    }
}
