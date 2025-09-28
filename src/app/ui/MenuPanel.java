package app.ui;

import app.main.MainWindow;
import app.model.NoteFile;
import app.model.NoteRepository;
import javax.swing.*;

public class MenuPanel extends JMenuBar {
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem newTabMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private NoteRepository repo = new NoteRepository();
    private NoteFile currentNote;

    public MenuPanel(MainWindow parent) {
        fileMenu = new JMenu("Файл");
        newTabMenuItem = new JMenuItem("Новая вкладка");
        saveMenuItem = new JMenuItem("Сохранить");
        exitMenuItem = new JMenuItem("Выход");

        currentNote = null;

        newTabMenuItem.addActionListener(e -> {
            int idx = parent.getSideBar().getNextUntitledIndex();
            NoteFile newNote = new NoteFile("Untitled " + idx, "");
            parent.getSideBar().addTabButton(newNote);
            setCurrentNote(newNote);
            parent.getEditor().setText("");
        });

        saveMenuItem.addActionListener(e -> {
            if (currentNote != null) {
                currentNote.setContent(parent.getEditor().getText());
                try {
                    repo.saveNote(currentNote);
                    JOptionPane.showMessageDialog(parent, "Заметка сохранена!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parent, "Ошибка при сохранении: " + ex.getMessage());
                }
            }
        });

        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newTabMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        editMenu = new JMenu("Изменить");

        undoMenuItem = new JMenuItem("Отменить");
        redoMenuItem = new JMenuItem("Восстановить");

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        add(editMenu);
    }

    public void setCurrentNote(NoteFile note) {
        currentNote = note;
    }
}