package sokoban;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Properties;
import javax.swing.JComponent;

/**
 * This class creates and paint the background of the level
 * @author jeczm
 */
public class Background extends JComponent{
    private static final String MAP_WIDTH = "map_width";
    private static final String MAP_HEIGHT = "map_height";
    private static final String PICTURE = "wall.jpg";
    public int startingElementWidth,startingElementHeight;
    public int elementWidth,elementHeight;
    Properties levelProperties;
    Dimension mapSize;
    Image img;
/**
 * 
 * @param p properties of current level.
 */
    public Background(Properties p){
        levelProperties = p;
        addNotify();
        loadDim();
    }
    /**
     * Adds Image to the object
     */
    @Override
    public void addNotify() {
        super.addNotify();
        img = Toolkit.getDefaultToolkit().getImage(PICTURE);
        
    }
    /**
     * Gets Dimensions of the current level map
     */
    private void loadDim(){
        mapSize=new Dimension();
        mapSize.width=Integer.parseInt(levelProperties.getProperty(MAP_WIDTH));
        mapSize.height=Integer.parseInt(levelProperties.getProperty(MAP_HEIGHT));
        startingElementWidth=Integer.parseInt(levelProperties.getProperty("element_width"));
        startingElementHeight=Integer.parseInt(levelProperties.getProperty("element_height"));
        elementWidth=startingElementWidth;
        elementHeight=startingElementHeight;
        img=img.getScaledInstance(elementWidth, elementHeight, 0);
    } 

    public final void draw(Graphics g){
       this.drawPlain(g);
       this.drawFrame(g);
    }
    
    /**
     * Draws "floor" of the level
     * @param g Graphics function
     */
    private void drawPlain(Graphics g){
        for(int i=0; i<mapSize.width;i++){
            for(int j=0; j< mapSize.height;j++){
            g.setColor(Color.CYAN);
            g.fillRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            g.setColor(Color.BLACK);
            g.drawRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            }
        }
    }
    /**
     * Draws "frame" of the level
     * @param g Graphics function
     */
    private void drawFrame(Graphics g){
        for(int j=0; j<mapSize.height;j++){
            int i = 0;
            g.drawImage(img, i*elementWidth, j*elementHeight, this);
            g.setColor(Color.BLACK);
            g.drawRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            }
        for(int j=0; j<mapSize.height;j++){
            int i = mapSize.width-1;
            g.drawImage(img, i*elementWidth, j*elementHeight, this);
            g.setColor(Color.BLACK);
            g.drawRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            }
        for(int i=0; i<mapSize.width-1;i++){
            int j = 0;
            g.drawImage(img, i*elementWidth, j*elementHeight, this);
            g.setColor(Color.BLACK);
            g.drawRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            }
        for(int i=0; i<mapSize.width-1;i++){
            int j = mapSize.height-1;
            g.drawImage(img, i*elementWidth, j*elementHeight, this);
            g.setColor(Color.BLACK);
            g.drawRect(i*elementWidth, j*elementHeight, elementWidth, elementHeight);
            }
    }
    
    public void resizeBackground(int h, int w){
        elementHeight=h;
        elementWidth=w;
        img=img.getScaledInstance(elementWidth, elementHeight, 0);
    }
}
