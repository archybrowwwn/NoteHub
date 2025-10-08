package app.undo_manager;

import javax.swing.JTextArea;

import app.linear_structures.*;

public class UndoManager {
    private final Stack<Change> undoStack = new Stack<>();
    private final Stack<Change> redoStack = new Stack<>();

    private boolean performing = false;
    public boolean isPerforming(){
        return performing;
    }

    public void clear(){
        undoStack.clear();
        redoStack.clear();
    }

    public void record(Change c){
        if (c.removeText.isEmpty() && c.addText.isEmpty()) return;
        undoStack.push(c);
        redoStack.clear();
    }

    public boolean canUndo(){ return !undoStack.isEmpty(); }

    public boolean canRedo(){ return !redoStack.isEmpty(); }

    public void undo(JTextArea area) {
        if (canUndo()){
            Change c = undoStack.pop();
            performing = true;
            try {
                area.replaceRange(c.removeText, c.index, c.index + c.addText.length());
            } finally {
                performing = false;
            }
            redoStack.push(c);
        }
    }

    public void redo(JTextArea area) {
        if (canRedo()){
            Change c = redoStack.pop();
            performing = true;
            try {
                area.replaceRange(c.addText, c.index, c.index + c.removeText.length());
            } finally {
                performing = false;
            }
            undoStack.push(c);
        }
    }
}
