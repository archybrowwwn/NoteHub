package app.linear_structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    
    private Stack<String> stack;
    
    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }
    
    @Test
    public void testPushAndPeek() {
        // Act
        stack.push("First");
        stack.push("Second");
        
        // Assert
        assertEquals("Second", stack.peek(), "Top element should be 'Second'");
    }
    
    @Test
    public void testPop() {
        // Arrange
        stack.push("First");
        stack.push("Second");
        
        // Act
        String popped = stack.pop();
        
        // Assert
        assertEquals("Second", popped, "Popped element should be 'Second'");
        assertEquals("First", stack.peek(), "New top element should be 'First'");
    }
    
    @Test
    public void testIsEmpty() {
        // Assert
        assertTrue(stack.isEmpty(), "New stack should be empty");
        
        // Act
        stack.push("Element");
        
        // Assert
        assertFalse(stack.isEmpty(), "Stack with elements should not be empty");
    }
    
    @Test
    public void testEmptyStackExceptions() {
        // Assert
        assertThrows(NoSuchElementException.class, () -> stack.peek(), 
                    "peek() should throw NoSuchElementException on empty stack");
        assertThrows(NoSuchElementException.class, () -> stack.pop(), 
                    "pop() should throw NoSuchElementException on empty stack");
    }
    
    @Test
    public void testClear() {
        // Arrange
        stack.push("First");
        stack.push("Second");
        
        // Act
        stack.clear();
        
        // Assert
        assertTrue(stack.isEmpty(), "Stack should be empty after clear");
    }
}