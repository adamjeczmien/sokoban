package sokoban;
import java.util.Properties;
import javax.swing.*;
/**
 * This class shows how many lives a player has.
 * @author jeczm
 */
public class Lives extends JComponent{
    JPanel panel = new JPanel();
    JLabel label;
    Properties gameOptions;
    public boolean isGameOver;
    int numOfLives;
    
    /**
     * Creates the message about lives number
     */
    private void createLives(){
        label= new JLabel();
        label.setText("Lives left: "+numOfLives);
        panel.add(label);
    }

    public Lives(Properties p){
        gameOptions=p;
        setNumOfLives();
        createLives();
    }
    /**
     * Set starting number of lives based on difficulity level from config file
     */
    private void setNumOfLives(){
        String diffLevel=gameOptions.getProperty("Difficulity_Level");
        switch (diffLevel) {
            case "Easy":
                numOfLives=3;
                break;
            case "Hard":
                numOfLives=1;
                break;
            default:
                numOfLives=2;
                break;
        }
    }
    
    /**
     * Decrements number of lifes or sets isGameOver flag as true
     */
    public void liveLoss(){
        if(numOfLives>1){
            numOfLives--;
            label.setText("Lives left: "+numOfLives);
        }
        else{
            isGameOver=true;
        }
    }
    /**
     * Get number of lives left value from the class.
     * @return Number of lives.
     */
    public int getLives(){
        return this.numOfLives;
    }
    
   
}
