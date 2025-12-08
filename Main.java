import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::makeWindow);
    }

    static void makeWindow() {
        JFrame f = new JFrame("Close me!");
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Close this!", SwingConstants.CENTER);
        f.add(label);

        Random r = new Random();
        f.setLocation(r.nextInt(800), r.nextInt(600));

        // Make the window always on top
        f.setAlwaysOnTop(true);

        // Extra tricks to force it to the front
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                forceToFront(f);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // When closed, spawn two new ones
                makeWindow();
                makeWindow();
            }
        });

        f.setVisible(true);
        forceToFront(f);
    }

    // Attempts to force window priority (best effort; OS may limit this)
    static void forceToFront(JFrame f) {
        f.toFront();
        f.repaint();
        f.requestFocus();
        f.requestFocusInWindow();
    }
}
