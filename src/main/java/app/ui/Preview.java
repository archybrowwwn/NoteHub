package app.ui;

import javax.swing.*;
import java.awt.*;

import app.markdown.MarkdownRenderer;

public class Preview extends JPanel {

    private final JEditorPane previewPane;
    private final MarkdownRenderer markdownRenderer;

    private static final String CONTENT_TYPE_HTML = "text/html";

    public Preview() {
        configureLayout();

        markdownRenderer = new MarkdownRenderer();
        previewPane = createEditorPane();

        add(createScrollPane(previewPane), BorderLayout.CENTER);
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
    }

    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType(CONTENT_TYPE_HTML);
        editorPane.setEditable(false);
        editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        editorPane.setFont(AppStyle.FONT);
        return editorPane;
    }

    private JScrollPane createScrollPane(JEditorPane editorPane) {
        return new JScrollPane(editorPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void showMarkdown(String markdown) {
        String html = markdownRenderer.render(markdown);
        previewPane.setText(html);
        previewPane.setCaretPosition(0);
    }
}