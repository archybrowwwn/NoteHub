package app.linear_structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    
    private LinkedList<String> list;
    
    @BeforeEach
    public void setUp() {
        list = new LinkedList<>();
    }
    
    @Test
    public void testAddToEnd() {
        // Act
        list.addToEnd("First");
        list.addToEnd("Second");
        
        // Assert
        assertEquals("First", list.getAt(0), "First element should be 'First'");
        assertEquals("Second", list.getAt(1), "Second element should be 'Second'");
    }
    
    @Test
    public void testAddToFirst() {
        // Act
        list.addToFirst("First");
        list.addToFirst("Second");
        
        // Assert
        assertEquals("Second", list.getAt(0), "First element should be 'Second'");
        assertEquals("First", list.getAt(1), "Second element should be 'First'");
    }
    
    @Test
    public void testInsertAt() {
        // Arrange
        list.addToEnd("First");
        list.addToEnd("Third");
        
        // Act
        list.insertAt("Second", 1);
        
        // Assert
        assertEquals("First", list.getAt(0), "First element should be 'First'");
        assertEquals("Second", list.getAt(1), "Second element should be 'Second'");
        assertEquals("Third", list.getAt(2), "Third element should be 'Third'");
    }
    
    @Test
    public void testRemoveAt() {
        // Arrange
        list.addToEnd("First");
        list.addToEnd("Second");
        list.addToEnd("Third");
        
        // Act
        list.removeAt(1);
        
        // Assert
        assertEquals("First", list.getAt(0), "First element should be 'First'");
        assertEquals("Third", list.getAt(1), "Second element should be 'Third'");
    }
    
    @Test
    public void testGetAtOutOfBounds() {
        // Arrange
        list.addToEnd("First");
        
        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> list.getAt(1), 
                    "Should throw IndexOutOfBoundsException when index is out of bounds");
    }
}