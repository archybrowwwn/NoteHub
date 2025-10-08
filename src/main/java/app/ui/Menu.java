package app.ui;

import app.data.Note;
import app.data.NoteRepository;
import app.main.MainWindow;
import app.undo_manager.UndoManager;
import app.undo_manager.UndoableFilter;

import javax.swing.text.AbstractDocument;
import javax.swing.*;

public class Menu extends JMenuBar {

    private final NoteRepository repo = new NoteRepository();
    private Note currentNote;

    private final MainWindow mainWindow;
    private final UndoManager undo;

    // ----- Меню -----
    private final JMenu fileMenu;
    private final JMenu editMenu;
    private final JMenu viewMenu;

    // ----- Пункты File -----
    private JMenuItem newNoteItem;
    private JMenuItem saveNoteItem;
    private JMenuItem exitItem;

    // ----- Пункты Edit -----
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    private JMenuItem searchItem;

    // ----- Пункты View -----
    private JMenuItem editorViewItem;
    private JMenuItem previewViewItem;

    public Menu(MainWindow mainWindow, UndoManager undo) {
        this.mainWindow = mainWindow;
        this.undo = undo;

        fileMenu = createFileMenu();
        editMenu = createEditMenu();
        viewMenu = createViewMenu();

        add(fileMenu);
        add(editMenu);
        add(viewMenu);

        AbstractDocument doc = (AbstractDocument) mainWindow.getViewController()
                .getEditor().getTextArea().getDocument();
        doc.setDocumentFilter(new UndoableFilter(
                mainWindow.getViewController().getEditor().getTextArea(), undo));

        // Таймер для включения/отключения кнопок undo/redo
        new Timer(200, e -> {
            undoItem.setEnabled(undo.canUndo());
            redoItem.setEnabled(undo.canRedo());
        }).start();
    }

    // ---------- fileMenu ----------
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        newNoteItem = createMenuItem("New note", "control N", this::handleNewNote);
        saveNoteItem = createMenuItem("Save note", "control S", this::handleSaveNote);
        exitItem = createMenuItem("Exit", "control Q", this::handleExit);

        fileMenu.add(newNoteItem);
        fileMenu.add(saveNoteItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        return fileMenu;
    }

    // ---------- editMenu ----------
    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");

        undoItem = createMenuItem("Undo", "control Z", this::handleUndo);
        redoItem = createMenuItem("Redo", "control Y", this::handleRedo);
        searchItem = createMenuItem("Search", "control F", this::handleSearch);

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(searchItem);

        return editMenu;
    }

    // ---------- viewMenu ----------
    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");

        editorViewItem = createMenuItem("Editor", "control E",
                () -> mainWindow.getViewController().switchToEditor());
        previewViewItem = createMenuItem("Preview", "control R",
                () -> mainWindow.getViewController().switchToPreview());

        viewMenu.add(editorViewItem);
        viewMenu.add(previewViewItem);

        return viewMenu;
    }

    private void handleNewNote() {
        int index = mainWindow.getSideBar().getNextUntitledIndex();
        Note note = new Note("Untitled " + index, "");
        mainWindow.getSideBar().addNoteButton(note);
        setCurrentNote(note);
        mainWindow.getViewController().getEditor().setText("");
    }

    private void handleSaveNote() {
        if (currentNote == null) {
            JOptionPane.showMessageDialog(
                    mainWindow,
                    "No note selected to save.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        currentNote.setText(mainWindow.getViewController().getEditor().getText());

        try {
            repo.saveNote(currentNote);
            JOptionPane.showMessageDialog(
                    mainWindow,
                    "Note saved successfully.",
                    "NoteHub",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    mainWindow,
                    "Error while saving note: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleExit() {
        System.exit(0);
    }

    private void handleUndo() {
        undo.undo(mainWindow.getViewController().getEditor().getTextArea());
    }

    private void handleRedo() {
        undo.redo(mainWindow.getViewController().getEditor().getTextArea());
    }

    private void handleSearch() {
        String query = JOptionPane.showInputDialog(
                mainWindow,
                "Enter text to search"
        );
        if (query == null || query.isBlank()) {
            return;
        }

        JTextArea textArea = mainWindow.getViewController().getEditor().getTextArea();
        String text = textArea.getText();

        int index = text.indexOf(query.trim());
        if (index >= 0) {
            textArea.requestFocus();
            textArea.select(index, index + query.length());
        } else {
            JOptionPane.showMessageDialog(
                    mainWindow,
                    "Text not found: \"" + query + "\""
            );
        }
    }

    // ---------- Вспомогательный метод ----------
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

    public Note getCurrentNote() {
        return currentNote;
    }
}
