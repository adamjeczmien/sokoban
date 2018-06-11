package sokoban;


import java.util.Properties;
import javax.swing.*;

/**
 * Informs the player which level he is taking 
 * @author jeczm
 */
public class LevelLabel extends JComponent{
    JPanel panel = new JPanel();
    JLabel label;
    int levelNumber;
    Properties levelProperties;
    
    /**
     * Creates message
     */
    private void createLevelLabel(){
        label= new JLabel();
        label.setText("Level: "+levelNumber);
        panel.add(label);
    }
    
    public LevelLabel(Properties p){
        levelProperties = p;
        getLevelNumber();
        createLevelLabel();
    }
    
    /**
     * Gets info from the config file
     */
    private void getLevelNumber(){
        levelNumber=Integer.parseInt(levelProperties.getProperty("level_Number"));
    }
}
