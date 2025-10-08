package app.ui;

import java.awt.*;
import javax.swing.*;

public class Editor extends JPanel {
    private final JTextArea textArea;

    public Editor() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}