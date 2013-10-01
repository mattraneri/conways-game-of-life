/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class GUIPanel extends JPanel implements MouseListener{
    GameCell[][] grid = new GameCell[50][50]; //Think- 500 by 500, 50 by 50, 10 pixels each
    public GUIPanel()
    {//Got the columns and rows messed up, oops
        this.setBackground(Color.BLACK);
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                grid[j][i] = new GameCell(i * 10, j * 10);
            }
        }
        this.addMouseListener(this);
        grid[5][5].setVisible(true);
        grid[5][6].setVisible(true);
        grid[4][5].setVisible(true);
        grid[4][6].setVisible(true);
        
        grid[6][7].setVisible(true);
        grid[6][8].setVisible(true);
        grid[7][7].setVisible(true);
        grid[7][8].setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Set it to red just because
        g.setColor(Color.red);
        draw(g);
    }
    
    public void iterate()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(getNeighbors(i, j).size() < 2)
                {
                    grid[i][j].setNextStep(false);
                }//Here is the problem, only replicate if it is three, and stay alive if 2
                else if(getNeighbors(i, j).size() == 2 || getNeighbors(i, j).size() == 3)
                {
                    if(grid[i][j].isVisible() == true)
                    {
                        grid[i][j].setNextStep(true);
                    }
                    else if(grid[i][j].isVisible() == false && getNeighbors(i, j).size() == 3)
                    {//DONE!! WORKING!!!
                        grid[i][j].setNextStep(true);
                    }
                }
                else if(getNeighbors(i, j).size() > 3)
                {
                    grid[i][j].setNextStep(false);
                }
            }
        }
    }
    
    public void execute_iteration()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid.length; j++)
            {
                grid[i][j].setVisible(grid[i][j].isNextStep());
            }
        }
    }
    
    public void draw(Graphics g)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid.length; j++)
            {
                grid[i][j].draw(g);
            }
        }
    }
    
    public ArrayList<GameCell> getNeighbors(int row, int column)
    {
        ArrayList<GameCell> returnArray = new ArrayList();
        
        try{
            if(grid[row][column + 1].isVisible() == true)
            {
                returnArray.add(grid[row][column + 1]);
            }
        }catch(Exception e){}
        
        
        try{
            if(grid[row][column - 1].isVisible() == true)
            {
                returnArray.add(grid[row][column - 1]);
            }
        }catch(Exception e){}
        
        
        try{
            if(grid[row + 1][column].isVisible() == true)
            {
                returnArray.add(grid[row + 1][column]);
            }
        }catch(Exception e){}
        
        
        try{
            if(grid[row - 1][column].isVisible() == true)
            {
                returnArray.add(grid[row - 1][column]);
            }
        }catch(Exception e){}
        
        
        try{
            if(grid[row + 1][column + 1].isVisible() == true)
            {
                returnArray.add(grid[row + 1][column + 1]);
            }
        }catch(Exception e){}
        
        
        try{
            if(grid[row - 1][column + 1].isVisible() == true)
            {
                returnArray.add(grid[row - 1][column + 1]);
            }
        }catch(Exception e){}
        
        try{
            if(grid[row + 1][column - 1].isVisible() == true)
            {
                returnArray.add(grid[row + 1][column - 1]);
            }
        }catch(Exception e){}
        try{
            if(grid[row - 1][column - 1].isVisible() == true)
            {
                returnArray.add(grid[row - 1][column - 1]);
            }
        }catch(Exception e){}
        return returnArray;
    }
    public void clearGrid()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                grid[i][j].setVisible(false);
                grid[i][j].setNextStep(false);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1)
        {
            int x = Math.round(e.getY() / 10);
            int y = Math.round(e.getX() / 10);
            grid[x][y].setVisible(!(grid[x][y].isVisible()));
            //System.out.println("test");
        }
        else if(e.getButton() == 3)
        {
            clearGrid();
            //System.out.println("JADFSKLFJADSKLFSA");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
