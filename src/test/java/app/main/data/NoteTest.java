package app.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testNoteCreation() {
        // Arrange
        String title = "Test Title";
        String text = "Test Text";
        
        // Act
        Note note = new Note(title, text);
        
        // Assert
        assertEquals(title, note.getTitle(), "Title should match the constructor parameter");
        assertEquals(text, note.getText(), "Text should match the constructor parameter");
    }
    
    @Test
    public void testSetText() {
        // Arrange
        Note note = new Note("Initial Title", "Initial Text");
        String newText = "Updated Text";
        
        // Act
        note.setText(newText);
        
        // Assert
        assertEquals(newText, note.getText(), "Text should be updated after setText call");
    }
    
    @Test
    public void testEmptyValues() {
        // Arrange & Act
        Note note = new Note("", "");
        
        // Assert
        assertEquals("", note.getTitle(), "Empty title should be allowed");
        assertEquals("", note.getText(), "Empty text should be allowed");
    }
}