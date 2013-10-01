/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conway.s.game.of.life;

import java.awt.Graphics;

/**
 *
 * @author matt
 */
public class GameCell {
    private int x, y;
    private int size;
    private boolean visible;
    private boolean nextStep; //In conway's game of life, the iteration goes at the same time
    public GameCell()
    {
        x = 0;
        y = 0;
        size = 10;
        visible = false;
        nextStep = false;
    }
    
    public GameCell(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        visible = false;
        nextStep = false;
    }
    
    public GameCell(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.size = 10;
        this.visible = false;
        nextStep = false;
    }
    
    public void draw(Graphics g)
    {
        if(this.visible == true)
        {
            g.fillRect(x, y, size, size);
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the nextStep
     */
    public boolean isNextStep() {
        return nextStep;
    }

    /**
     * @param nextStep the nextStep to set
     */
    public void setNextStep(boolean nextStep) {
        this.nextStep = nextStep;
    }
}
