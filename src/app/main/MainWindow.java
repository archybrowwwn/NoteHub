package app.main;

import app.ui.AppStyle;
import app.ui.Editor;
import app.ui.Menu;
import app.ui.SideBar;
import app.undo_manager.UndoManager;
import app.undo_manager.UndoableFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;

public class MainWindow extends JFrame {
    private final SideBar sideBar;
    private final Editor editor;
    private final Menu menu;

    public MainWindow() {
        setTitle("NoteHub");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            UIManager.put("Menu.font", AppStyle.FONT);
            UIManager.put("MenuItem.font", AppStyle.FONT);
            UIManager.put("TextArea.font", AppStyle.FONT);
            UIManager.put("Button.font", AppStyle.FONT);
        } catch (Exception ignored) {}

        editor = new Editor();

        UndoManager undo = new UndoManager();
        AbstractDocument doc = (AbstractDocument) editor.getTextArea().getDocument();
        doc.setDocumentFilter(new UndoableFilter(editor.getTextArea(), undo));


        sideBar = new SideBar(this);
        menu = new Menu(this, undo);
        setJMenuBar(menu);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBar, editor);
        splitPane.setDividerLocation(220);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);
    }

    public Editor getEditor() {
        return editor;
    }

    public Menu getMenu() {
        return menu;
    }

    public SideBar getSideBar() {
        return sideBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}