package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Properties;

/**
 * This class draws walls - unmoveable obstacles in the game
 * @author jeczm
 */
public class Wall extends GameObject{
    private static final String PICTURE = "wall.jpg";
    
    Image img;
    /**
     * Constructor assing placement of the wall 
     * @param someX position on X axis, given by config file.
     * @param someY position on Y axis, given by config file.
     * @param p properties of the current level.
     */
    public Wall(int someX, int someY, Properties p){
        super(p);
        addNotify();
        x=someX;
        y=someY;
    }
    /**
     * Adds Image to the object
     */
    @Override
    public void addNotify() {
        super.addNotify();
        img = Toolkit.getDefaultToolkit().getImage(PICTURE);
        img=img.getScaledInstance(width, height, 0);
    }
    /**
     * Draws wall.
     * @param g graphical context
     */
    @Override
    public final void draw(Graphics g){
            g.drawImage(img, (int)Math.round(x*width), (int)Math.round(y*height), this);
            g.setColor(Color.black);
            g.drawRect((int)Math.round(x*width), (int)Math.round(y*height), width, height);
    }
    /**
     * Changes Dimensions of the element
     * @param h new height
     * @param w new width 
     */
     public void resizeElement(int h, int w){
        height=h;
        width=w;
        img=img.getScaledInstance(width, height, 0);
     }
}
