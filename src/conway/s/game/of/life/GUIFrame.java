/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author matt
 */
public class GUIFrame extends JFrame {

    public GUIFrame() {
        this.setSize(500, 500);
        this.setTitle("Conway's game of life");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        GUIPanel p = new GUIPanel();
        MenuBar menu = new MenuBar();
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        ButtonPanel p2 = new ButtonPanel(p);
        this.add(p2, BorderLayout.SOUTH);
        this.setJMenuBar(menu);
        this.setVisible(true);
        while (true) {
            pause(10);
            p.repaint();
        }
    }

    public void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveGame() {
        
    }
}
