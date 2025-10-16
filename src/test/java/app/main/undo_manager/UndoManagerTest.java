package app.undo_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextArea;

public class UndoManagerTest {
    
    private UndoManager undoManager;
    private JTextArea textArea;
    
    @BeforeEach
    public void setUp() {
        undoManager = new UndoManager();
        textArea = new JTextArea("Initial text");
    }
    
    @Test
    public void testRecordAndUndo() {
        // Arrange
        Change change = new Change(0, "", "New text");
        
        // Act
        undoManager.record(change);
        
        // Assert
        assertTrue(undoManager.canUndo(), "Should be able to undo after recording a change");
        assertFalse(undoManager.canRedo(), "Should not be able to redo initially");
        
        // Act
        undoManager.undo(textArea);
        
        // Assert
        assertFalse(undoManager.canUndo(), "Should not be able to undo after undoing the only change");
        assertTrue(undoManager.canRedo(), "Should be able to redo after undoing");
    }
    
    @Test
    public void testClear() {
        // Arrange
        undoManager.record(new Change(0, "", "Text 1"));
        undoManager.record(new Change(0, "", "Text 2"));
        
        // Act
        undoManager.clear();
        
        // Assert
        assertFalse(undoManager.canUndo(), "Should not be able to undo after clearing");
        assertFalse(undoManager.canRedo(), "Should not be able to redo after clearing");
    }
}