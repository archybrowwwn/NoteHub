package app.markdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MarkdownRendererTest {
    
    private MarkdownRenderer renderer;
    
    @BeforeEach
    public void setUp() {
        renderer = new MarkdownRenderer();
    }
    
    @Test
    public void testRenderBasicMarkdown() {
        // Arrange
        String markdown = "# Header\n\nThis is **bold** text.";
        
        // Act
        String html = renderer.render(markdown);
        
        // Assert
        assertTrue(html.contains("<h1>Header</h1>"), "HTML should contain h1 header");
        assertTrue(html.contains("<strong>bold</strong>"), "HTML should contain strong tag for bold text");
    }
    
    @Test
    public void testRenderEmptyString() {
        // Act
        String html = renderer.render("");
        
        // Assert
        assertNotNull(html, "HTML should not be null for empty input");
    }
    
    @Test
    public void testRenderNull() {
        // Act
        String html = renderer.render(null);
        
        // Assert
        assertNotNull(html, "HTML should not be null for null input");
    }
}