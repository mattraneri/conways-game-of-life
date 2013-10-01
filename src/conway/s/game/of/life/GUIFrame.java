/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                    if (cells[i][j].isVisible()) {
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

    public static void loadGame() {
        File file = new File("save.txt");
        try {
            BufferedReader in = Files.newBufferedReader(Paths.get("save.txt"), StandardCharsets.UTF_8);
            GameCell[][] cells = new GameCell[50][50];
            String nextLine = in.readLine();
            int incre = 0;
            while (nextLine != null || incre < 50) {
                if (nextLine.length() > 50) {
                    throw new Exception("Corrupt.");
                }
                for (int i = 0; i < 50; i++) {
                    char c = nextLine.charAt(i);
                    if(c == '1') {
                        cells[incre][i] = new GameCell(i * 10, incre * 10);
                        cells[incre][i].setVisible(true);
                    } else if (c == '0') {
                        cells[incre][i] = new GameCell(i * 10, incre * 10);
                        cells[incre][i].setVisible(false);
                    } else {
                        throw new Exception("Corrupt.");
                    }
                }
                incre++;
                nextLine = in.readLine();
            }
            GUIPanel.setCells(cells);
            JOptionPane.showMessageDialog(null, "File loaded successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File doesn't exist or is corrupt");
        }
    }
}
