/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    
    private static void error() {
        JOptionPane.showMessageDialog(null, "This shouldn't happen..");
    }

    public static void saveGame() {
        GameCell[][] cells = GUIPanel.getCells();
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("save.txt"), "UTF-8"));
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells.length; j++) {
                    if(cells[i][j].isVisible())
                    {
                        out.write("1");
                    } else {
                        out.write("0");
                    }
                }
                out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("This shouldn't ever happen...");
            error();
            return;
        }
        JOptionPane.showMessageDialog(null, "Saved successfully.");
    }
}
