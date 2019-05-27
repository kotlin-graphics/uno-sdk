package uno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//public class Test {
//
//    public static void main(String[] arg) {
//        System.out.println("Ciao");
//    }
//}

public class QueueDelay {
    private static Color ourBackground = Color.WHITE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JComponent content = new JComponent() {
                @Override
                public void paint(Graphics g) {
                    g.setColor(ourBackground);
                    g.fillRect(0, 0, getWidth(), getHeight());

                    g.setColor(Color.BLACK);
                    g.drawString("Press 1 to request a repaint", 7, 20);
                }
            };
            content.setFocusable(true);
            content.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_1) {
                        SwingUtilities.invokeLater(() -> pause(500));

                        ourBackground = Color.WHITE.equals(ourBackground) ? Color.GREEN : Color.WHITE;
                        content.repaint();

                        pause(500);
                    }
                }
            });

            JFrame frame = new JFrame("Event queue delay");
            frame.getContentPane().add(content);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setPreferredSize(new Dimension(350, 350));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void pause(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}