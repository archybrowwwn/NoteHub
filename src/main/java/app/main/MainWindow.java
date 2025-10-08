package app.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import app.ui.AppStyle;
import app.ui.SideBar;
import app.ui.Menu;
import app.ui.Editor;

import app.undo_manager.UndoManager;
import app.undo_manager.UndoableFilter;

import javax.swing.text.AbstractDocument;

public class MainWindow extends JFrame {

    // ---------- Dependencies ----------
    private final SideBar sideBar;
    private final Menu menu;
    private final ViewController viewController;

    public MainWindow() {
        configureWindow();
        configureLookAndFeel();

        viewController = new ViewController(this);
        sideBar = new SideBar(this);

        UndoManager undo = new UndoManager();
        menu = new Menu(this, undo);

        setJMenuBar(menu);
        add(sideBar, BorderLayout.WEST);
        add(viewController.getCurrentView(), BorderLayout.CENTER);

        // ---------- Настройка Undo/Redo для Editor ----------
        Editor editor = viewController.getEditor();
        AbstractDocument doc = (AbstractDocument) editor.getTextArea().getDocument();
        doc.setDocumentFilter(new UndoableFilter(editor.getTextArea(), undo));
    }

    // ---------- Configuration ----------
    private void configureWindow() {
        setTitle("NoteHub");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    private void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Menu.font", AppStyle.FONT);
            UIManager.put("MenuItem.font", AppStyle.FONT);
            UIManager.put("TextArea.font", AppStyle.FONT);
            UIManager.put("Button.font", AppStyle.FONT);
        } catch (Exception ignored) {}
    }

    // ---------- Getters ----------
    public ViewController getViewController() {
        return viewController;
    }

    public SideBar getSideBar() {
        return sideBar;
    }

    public Menu getMenu() {
        return menu;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
