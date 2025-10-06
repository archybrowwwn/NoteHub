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

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        add(editMenu);
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
