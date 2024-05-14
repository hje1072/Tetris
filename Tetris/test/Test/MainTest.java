package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Frame;

import main.Main;

import javax.swing.JFrame;

import org.junit.Test;

public class MainTest {

    @Test
    public void testMain() {
        Main.main(null);

        Frame[] frames = JFrame.getFrames();
        assertEquals(1, frames.length);

        JFrame window = (JFrame) frames[0];
        assertEquals("Tetris", window.getTitle());
        assertFalse(window.isResizable());
        assertTrue(window.isVisible());
    }
}
