package main;

import org.lwjgl.system.Platform;
import uno.awt.LwjglCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by elect on 15/02/17.
 */
public class Prova {

    public static void main(String[] argvs) {

        if (Platform.get() != Platform.WINDOWS)
            throw new UnsupportedOperationException("This demo can only run on Windows.");

        LwjglCanvas canvas = new LwjglCanvas();
        canvas.setSize(640, 480);

        JFrame frame = new JFrame("JAWT Demo");

        // set it, because default is HIDE_ON_CLOSE
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                canvas.destroyInternal();
            }
        });

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped " + Thread.currentThread().getName());
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
                    frame.dispose();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased");
            }
        };

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);

        frame.pack();
        frame.addKeyListener(keyListener);
        frame.setVisible(true);
    }
}
