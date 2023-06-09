package client.app.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NoteListPanel extends JPanel {
    private DefaultListModel<NotePanel> noteListModel;
    private JList<NotePanel> noteList;

    public NoteListPanel() {
        setLayout(new BorderLayout());

        noteListModel = new DefaultListModel<>();
        noteList = new JList<>(noteListModel);
        noteList.setCellRenderer(new PanelListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(noteList);

        add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Ваши заметки");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }

    public void addNoteToList(String note) {
        NotePanel panel = new NotePanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JTextField textField = new JTextField(note);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setBackground(Color.LIGHT_GRAY);
        panel.add(textField, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));

        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(e -> deleteNote());
        deleteButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        deleteButton.setBackground(Color.GREEN);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);
        deleteButton.setOpaque(true);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(0, 150, 0)); // Darker shade of green on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(Color.GREEN);
            }
        });
        deleteButton.setPreferredSize(new Dimension(100, 30)); // Adjust button size
        deleteButton.setUI(new RoundedCornerButtonUI(30, 30)); // Apply rounded corner UI with arc width and height of 10


        buttonPanel.add(deleteButton);

        JButton editButton = new JButton("Редактировать");
        editButton.addActionListener(e -> editNote());
        editButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        editButton.setBackground(Color.GREEN);
        editButton.setForeground(Color.BLACK);
        editButton.setFocusPainted(false);
        editButton.setOpaque(true);
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editButton.setBackground(new Color(0, 150, 0)); // Darker shade of green on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editButton.setBackground(Color.GREEN);
            }
        });
        editButton.setPreferredSize(new Dimension(120, 30)); // Adjust button size
        editButton.setUI(new RoundedCornerButtonUI(30, 30)); // Apply rounded corner UI with arc width and height of 10


        buttonPanel.add(editButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        noteListModel.addElement(panel);
    }

    public JList<NotePanel> getNoteList() {
        return noteList;
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
            NotePanel panel = noteList.getSelectedValue();
            JTextField textField = (JTextField) panel.getComponent(0);
            String note = textField.getText();
            String editedNote = JOptionPane.showInputDialog(panel, "Введите новый текст заметки", note);
            if (editedNote != null) {
                textField.setText(editedNote);
            }
        }
    }

    private static class NotePanel extends JPanel {
        private static final int ARC_WIDTH = 30;
        private static final int ARC_HEIGHT = 30;

        private JTextField textField;

        public NotePanel() {
            setLayout(new BorderLayout());

            textField = new JTextField();
            textField.setEditable(false);
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            add(textField, BorderLayout.CENTER);
        }

        public void setNoteText(String text) {
            textField.setText(text);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arcWidth = 10;
            int arcHeight = 10;
            int borderWidth = 2;
            Color borderColor = Color.LIGHT_GRAY;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(borderWidth, borderWidth, getWidth() - 2 * borderWidth, getHeight() - 2 * borderWidth, arcWidth, arcHeight);
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRoundRect(borderWidth, borderWidth, getWidth() - 2 * borderWidth, getHeight() - 2 * borderWidth, arcWidth, arcHeight);
            g2d.dispose();
        }
    }

    private static class PanelListCellRenderer implements ListCellRenderer<NotePanel> {
        public Component getListCellRendererComponent(JList<? extends NotePanel> list, NotePanel value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return value;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Note List Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            NoteListPanel noteListPanel = new NoteListPanel();
            noteListPanel.addNoteToList("Первая заметка");
            noteListPanel.addNoteToList("Вторая заметка");
            noteListPanel.addNoteToList("Третья заметка");

            frame.getContentPane().add(noteListPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
}
