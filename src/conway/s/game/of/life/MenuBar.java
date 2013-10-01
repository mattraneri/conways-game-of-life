/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author matt
 */
public class MenuBar extends JMenuBar implements ActionListener{
    JMenu file = new JMenu("File");
    public MenuBar()
    {
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem options = new JMenuItem("Options...");
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        save.addActionListener(this);
        file.add(save);
        file.add(load);
        file.add(options);
        exit.addActionListener(this);
        options.addActionListener(this);
        file.add(exit);
        this.add(file);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "Save":
                GUIFrame.saveGame();
                break;
            case "Options...":
                JOptionPane.showMessageDialog(this, "Not yet implemented.");
                break;
        }
    }
}
