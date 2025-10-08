package app.main;

import app.ui.EditorPanel;
import app.ui.MenuPanel;
import app.ui.SideBarPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private SideBarPanel sideBar;
    private EditorPanel editor;
    private MenuPanel menu;

    public MainWindow() {
        setTitle("NoteHub");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        editor = new EditorPanel();

        sideBar = new SideBarPanel(this);

        menu = new MenuPanel(this);
        setJMenuBar(menu);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sideBar,
                editor
        );
        splitPane.setDividerLocation(200);

        add(splitPane, BorderLayout.CENTER);
    }

    public EditorPanel getEditor() {
        return editor;
    }

    public MenuPanel getMenu() {
        return menu;
    }

    public SideBarPanel getSideBar() {
        return sideBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}