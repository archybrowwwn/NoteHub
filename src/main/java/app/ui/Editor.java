package app.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Editor extends JPanel {
    private final JTextArea textArea;

    public Editor() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(AppStyle.FONT);

        JScrollPane scrollText = new JScrollPane(textArea);
        add(scrollText, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}