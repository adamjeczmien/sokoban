package sokoban;

import java.awt.*;
import java.util.Properties;
import javax.swing.JComponent;

/**
 *This class calculates and shows points
 * @author jeczm
 */
public class Points extends JComponent{
    Panel panel = new Panel();
    Label label;
    Properties gameOptions;
    private int numOfPoints;
    private int MParametr, timeLeft, livesLeft,levelNum;
    
    /**
     * Displays the message about points number
     */
    private void createPoints(){
        label= new Label();
        label.setText("Points: "+numOfPoints);
        panel.add(label);
    }
    
    /**
     * This function calculates points for level.
     */
    public void calculate(){
        int score=(MParametr*livesLeft)+(timeLeft*levelNum);
        numOfPoints=numOfPoints+score;
        label.setText("Points: "+numOfPoints);
    }
    
    public Points(Properties p){
        gameOptions=p;
        setMParametr();
        createPoints();
    } 
    
    /**
     * Set starting value of parametr needed to calculate points.
     */
    private void setMParametr(){
        String diffLevel=gameOptions.getProperty("Difficulity_Level");
        switch (diffLevel) {
            case "Easy":
                MParametr=1;
                break;
            case "Hard":
                MParametr=8;
                break;
            default:
                MParametr=3;
                break;
        }
    }
    
    /**
     * Set timeLeft value
     * @param t value of the timer
     */
    public void setTimeLeft(int t){
        this.timeLeft=t;
    }
    
    /**
     * Set livesLeft value
     * @param l value of the lifes
     */
    public void setLifesLeft(int l){
        this.livesLeft=l;
    }
    
    /**
     * Sets current level number.
     * @param i level number.
     */
    public void setLevelNumber(int i){
        this.levelNum=i;
    }
    
    /**
     * Interface used to get number of points.
     * @return points value.
     */
    public int getPoints(){
        return this.numOfPoints;
    }
}
