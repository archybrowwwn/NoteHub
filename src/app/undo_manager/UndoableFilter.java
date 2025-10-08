package app.undo_manager;

import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class UndoableFilter extends DocumentFilter {
    private final JTextArea area;
    private final UndoManager undo;

    public UndoableFilter(JTextArea area, UndoManager undo) {
        this.area = area;
        this.undo = undo;
    }

    @Override
    public void insertString(FilterBypass fb, int index, String text, AttributeSet attrs)
            throws BadLocationException {
        replace(fb, index, 0, text, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int index, int length)
            throws BadLocationException {
        replace(fb, index, length, "", null); 
    }

    @Override
    public void replace(FilterBypass fb, int index, int length, String text, AttributeSet attrs)
            throws BadLocationException {
                if (undo.isPerforming()) {
                    super.replace(fb, index, length, text, attrs);
                    return;
                }

        Document doc = fb.getDocument();
        String removed = (length > 0) ? doc.getText(index, length) : "";
        String added = (text != null) ? text : "";

        super.replace(fb, index, length, added, attrs);

        if (!removed.isEmpty() || !added.isEmpty()) {
            undo.record(new Change(index, removed, added));
        }
    }
}
