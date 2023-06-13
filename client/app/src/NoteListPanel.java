package client.app.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NoteListPanel extends JPanel {
    private DefaultListModel<NotePanel> noteListModel;
    public JList<NotePanel> noteList;
    public Map<NotePanel, String[]> noteContentMap;
    private Serverhandler serverhandler = LoginScreen.serverhandler;
    private NotesApplication notesApplication = LoginScreen.notesApplication;
    private RoundButton deleteButton;
    private RoundButton editButton;
    private NotePanel panel;

    public NoteListPanel() {
        panel = null;
        noteList = null;
        noteListModel = null;
        noteContentMap = null;
        setLayout(new BorderLayout());

        noteListModel = new DefaultListModel<>();
        noteList = new JList<>(noteListModel);
        noteList.setCellRenderer(new PanelListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(noteList);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        deleteButton = new RoundButton("Удалить");
        deleteButton.setBackground(Color.GREEN);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });

        editButton = new RoundButton("Редактировать название");
        editButton.setBackground(Color.GREEN);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });
        //notesApplication.showCreateNotePanel(notesApplication.mass[0], notesApplication.mass[1], notesApplication.mass[2]);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(deleteButton, gbc);
        gbc.gridy++;
        buttonPanel.add(editButton, gbc);

        JPanel buttonWrapperPanel = new JPanel(new BorderLayout());
        buttonWrapperPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(buttonWrapperPanel, BorderLayout.EAST);

        JLabel titleLabel = new JLabel("Ваши заметки");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        noteContentMap = new HashMap<>();
        try {
            getlist();
        }
        catch (Exception ex){
            System.out.println("Strange thing is happening!");
            ex.printStackTrace();
        }
    }

    public void addNoteToList(String note, String note_id, String note_text) {

        panel = new NotePanel();
        panel.setBackground(getBackground());

        JTextField textField = new JTextField(note);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setBackground(getBackground());
        panel.add(textField, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        String temp_text[] = new String[3];
        temp_text[0] = note_id;
        temp_text[1] = note;
        temp_text[2] = note_text;
        noteListModel.addElement(panel);
        noteContentMap.put(panel, temp_text);
    }

    public void updateNoteContent(NotePanel panel, String content) {
        String[] temp_text = noteContentMap.get(panel);
        temp_text[1] = content;
        noteContentMap.put(panel, temp_text);
    }

    /*public String getNoteContent(NotePanel panel) {
        return noteContentMap.get(panel);
    }*/

    //public JList<NotePanel> getNoteList() {return noteList;}

    private void deleteNote() { if (serverhandler.index != -1) {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            try {
                serverhandler.notedelete(Integer.parseInt(serverhandler.mass[0]));
                serverhandler.index = -1;
            } catch (Exception ex) {
                System.out.println("Strange thing is happening!");
            }
        }
    }
    }

    public void editNote() { if (serverhandler.index != -1) {
        NotePanel panel = noteListModel.getElementAt(serverhandler.index);
        JTextField textField = (JTextField) panel.getComponent(0);
        String note = textField.getText();
        String editedNote = JOptionPane.showInputDialog("Введите новое название заметки");
        if (editedNote != null) {
            try{serverhandler.notenameupdate(Integer.parseInt(serverhandler.mass[0]), editedNote);
                serverhandler.index = -1;}
            catch (Exception ex){
                System.out.println("Strange thing is happening!");
                ex.printStackTrace();
            }
        }
    }
    }

    public JList<NotePanel> getNoteList() {
        return noteList;
    }

    private static class NotePanel extends JPanel {
        private static final int ARC_WIDTH = 30;
        private static final int ARC_HEIGHT = 30;

        private JTextField textField;

        public JTextField getTextField() {
            return textField;
        }

        public NotePanel() {
            setLayout(new BorderLayout());

            textField = new JTextField();
            textField.setEditable(false);
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            add(textField, BorderLayout.CENTER);
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

    public void getlist() throws Exception{
            int temp_list = serverhandler.getnoteslist();
            String id = "", name = "", text = "";
            int temp = 0;
            for(String s : serverhandler.noteslist){
                if (temp == 3)
                    temp = 0;
                if (temp == 0)
                    id = s;
                if (temp == 1)
                    name = s;
                if (temp == 2) {
                    text = s;
                    addNoteToList(name, id, text);
                    System.out.println("\nid: " + id + "\nname: " + name + "\ntext: " + text);
                }
                temp++;
            }
    }
}
