package sokoban;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Properties;

/**
 * This class creates and paints unmoveable endpoints.
 * Also it is only class which can end the level.
 * @author jeczm
 */
public class Endpoint extends GameObject{
    private static final String PICTURE = "endpoint.gif";
    private boolean isOccupied;
    Image img;
    /**
     * Constructor assing placement of the ending point
     * @param someX position on X axis, given by config file.
     * @param someY position on Y axis, given by config file.
     * @param p properties of current level.
     */
    public Endpoint(int someX, int someY,Properties p){
        super(p);
        addNotify();
        this.isOccupied=false;
        x=someX;
        y=someY;
      
    }
    
    /**
     * Sets isOccupied flag 
     * @param check condition
     */
    public void setOccupied(boolean check){
        if(check){
           this.isOccupied=true;
        }
        else
            this.isOccupied=false;
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
     * Draws endpoint
     * @param g graphical context
     */
    @Override
    public void draw(Graphics g){
            g.drawImage(img, (int)Math.round(x*width), (int)Math.round(y*height), this);

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
