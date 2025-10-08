package app.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import app.ui.Editor;
import app.ui.Preview;

public class ViewController {

    // ---------- Dependencies ----------
    private final MainWindow mainWindow;
    private final Editor editor;
    private final Preview preview;

    private Component currentView;

    public ViewController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.editor = new Editor();
        this.preview = new Preview();
        this.currentView = editor;
    }

    public Component getCurrentView() {
        return currentView;
    }

    public Editor getEditor() {
        return editor;
    }

    public void switchToEditor() {
        swapView(editor);
    }

    public void switchToPreview() {
        preview.showMarkdown(editor.getText());
        swapView(preview);
    }

    private void swapView(Component newView) {
        Container content = mainWindow.getContentPane();
        content.remove(currentView);
        content.add(newView, BorderLayout.CENTER);
        currentView = newView;
        content.revalidate();
        content.repaint();
    }
}
