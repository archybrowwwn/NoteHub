package app.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.SwingUtilities;

public class MainWindowTest {

    @Test
    public void testMainWindowInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            MainWindow window = new MainWindow();

            assertNotNull(window);

            assertNotNull(window.getSideBar());
            assertNotNull(window.getMenu());
            assertNotNull(window.getViewController());

            assertNotNull(window.getViewController().getCurrentView());

            window.setVisible(true);
            assertTrue(window.isVisible());

            window.dispose();
        });
    }
}
