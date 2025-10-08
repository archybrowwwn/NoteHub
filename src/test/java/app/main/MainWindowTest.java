package app.main;

import org.junit.Test;
import static org.junit.Assert.*;

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
