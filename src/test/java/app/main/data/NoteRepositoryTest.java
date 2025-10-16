package app.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoteRepositoryTest {
    
    private NoteRepository repository;
    
    @BeforeEach
    public void setUp() {
        repository = new NoteRepository();
    }
    
    @Test
    public void testSaveAndLoadNote() throws Exception {
        // Arrange
        Note note = new Note("TestNote", "This is test content");
        
        // Act
        repository.saveNote(note);
        Note loadedNote = repository.loadNote("TestNote");
        
        // Assert
        assertEquals(note.getTitle(), loadedNote.getTitle(), "Loaded note should have the same title");
        assertEquals(note.getText(), loadedNote.getText(), "Loaded note should have the same text");
    }
    
    @Test
    public void testListNoteTitles() throws Exception {
        // Arrange
        repository.saveNote(new Note("Note1", "Content 1"));
        repository.saveNote(new Note("Note2", "Content 2"));
        
        // Act
        List<String> titles = repository.listNoteTitles();
        
        // Assert
        assertTrue(titles.contains("Note1"), "Titles list should contain Note1");
        assertTrue(titles.contains("Note2"), "Titles list should contain Note2");
    }
}