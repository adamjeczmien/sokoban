package sokoban;

import java.awt.Graphics;
import java.util.Properties;
import javax.swing.JComponent;

/**
 *  Abstract class of the graphical components of the game
 * @author jeczm
 */
public abstract class GameObject extends JComponent{
    private static final String OBJECT_WIDTH = "element_width";
    private static final String OBJECT_HEIGHT = "element_height";
    protected float x,y;
    protected int width, height;
    Properties levelProperties;
    /**
     * This function draws each object visible in the game panel
     * @param g Since unction draws it has to have Graphics parametr
    */
    abstract public void draw(Graphics g);
    /**
     * Used to define dimensions of the object
     */
    private void loadDims(){
        width=Integer.parseInt(levelProperties.getProperty(OBJECT_WIDTH));
        height=Integer.parseInt(levelProperties.getProperty(OBJECT_HEIGHT));
    };
    
    /**
     * Constructor loads properties of current level.
     * @param p Current level Properties.
     */
    public GameObject(Properties p){
        levelProperties = p;
        loadDims();
    }
    
    
    public void pushedLeft(Avatar p){
        
    }
    public void pushedUp(Avatar p){
        
    }
    public void pushedRight(Avatar p){
        
    }
    public void pushedDown(Avatar p){
        
    }
}