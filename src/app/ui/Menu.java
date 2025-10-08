package app.ui;

import app.main.MainWindow;
import app.model.Note;
import app.model.NoteRepository;
import javax.swing.*;

public class Menu extends JMenuBar {
    private final JMenu fileMenu;
    private final JMenu editMenu;
    private final JMenuItem newNoteItem;
    private final JMenuItem saveItem;
    private final JMenuItem exitItem;
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;
    private final JMenuItem searchItem;

    private final NoteRepository repo = new NoteRepository();
    private Note currentNote;

    public Menu(MainWindow parent) {
        fileMenu = new JMenu("File");

        newNoteItem = createMenuItem("New note", "control N", () -> {
            int idx = parent.getSideBar().getNextUntitledIndex();
            Note note = new Note("Untitled " + idx, "");
            parent.getSideBar().addNoteButton(note);
            setCurrentNote(note);
            parent.getEditor().setText("");
        });

        saveItem = createMenuItem("Save", "control S", () -> {
            if (currentNote == null) {
                return;
            }
            currentNote.setText(parent.getEditor().getText());
            try {
                repo.saveNote(currentNote);
                JOptionPane.showMessageDialog(parent, "Note saved successfully", "NoteHub", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Error while saving note: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitItem = createMenuItem("Exit", "control Q", () -> System.exit(0));

        fileMenu.add(newNoteItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        add(fileMenu);

        editMenu = new JMenu("Edit");

        undoItem = createMenuItem("Undo", "control Z", () -> { /* действие */ });
        redoItem = createMenuItem("Redo", "control Y", () -> { /* действие */ });
        searchItem = createMenuItem("Search", "control F", () -> {
            performSearch(parent);
        });

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(searchItem);

        
        add(editMenu);
    }

    private void performSearch(MainWindow parent) {
        String query = JOptionPane.showInputDialog(parent, "Enter text to search");
        if (query == null || query.isBlank()) {
            return;
        }

        JTextArea textArea = parent.getEditor().getTextArea();
        String text = textArea.getText();

        int index = findIndex(text, query.trim());
        if (index >= 0) {
            textArea.requestFocus();
            textArea.select(index, index + query.length());
        } else {
            JOptionPane.showMessageDialog(parent, "Text not found: \"" + query + "\"");
        }

    }
            

    private int findIndex(String text, String query) {
        int n = text.length();
        int m = query.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != query.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }

        return -1;
    }
                

    private JMenuItem createMenuItem(String text, String accelerator, Runnable action) {
        JMenuItem menuItem = new JMenuItem(text);
        if (accelerator != null) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator));
        }
        menuItem.addActionListener(e -> action.run());
        return menuItem;
    }


    public void setCurrentNote(Note note) {
        currentNote = note;
    }
}
