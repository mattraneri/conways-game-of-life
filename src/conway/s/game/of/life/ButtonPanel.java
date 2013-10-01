/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author matt
 */
public class ButtonPanel extends JPanel implements ActionListener{
    GUIPanel pan;
    JButton constant;
    boolean buttonRun = false;
    JTextField stepsRun = new JTextField("1",10);
    JSlider slider = new JSlider(100, 1000);
    public ButtonPanel(GUIPanel pan)
    {
        this.setLayout(new FlowLayout());
        JButton step = new JButton("Step");
        constant = new JButton("Run");
        step.addActionListener(this);
        constant.addActionListener(this);
        slider.setOrientation(JSlider.HORIZONTAL);
        this.add(stepsRun);
        this.add(step);
        this.add(slider);
        this.add(constant);
        
        this.pan = pan;
    }
    
    public class iterateThread extends Thread
    {
        @Override
        public void run()
        {
            while(buttonRun)
            {
                pan.iterate();
                pan.execute_iteration();
                pause(slider.getValue());
            }
        }
        
        public void pause(long time)
        {
            try{
                Thread.sleep(time);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Step"))
        {
            try
            {
                String a = stepsRun.getText();
                int x = Integer.parseInt(a);
                if(x <= 0)
                {
                    Exception exx;
                    exx = new Exception();
                    throw exx;
                }
                iterate(x);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(super.getParent(), "Make sure step txt box is a positive integer.");
            }
        }
        else if(e.getActionCommand().equals("Run"))
        {
            constant.setText("Stop");
            buttonRun = true;
            iterateThread t = new iterateThread();
            t.start();
        }
        else if(e.getActionCommand().equals("Stop"))
        {
            constant.setText("Run");
            buttonRun = false;
        }
    }
    
    public void iterate(int times)
    {
        for(int i = 0; i < times; i++)
        {
            pan.iterate();
            pan.execute_iteration();
        }
    }
}
