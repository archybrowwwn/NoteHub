package app.ui;

import javax.swing.*;
import java.awt.*;

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
}
