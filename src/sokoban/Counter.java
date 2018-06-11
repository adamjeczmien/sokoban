package sokoban;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;


/**
 * This class controls time of the level
 * @author jeczmien
 */
public class Counter extends JComponent  {
    JPanel panel=new JPanel();
    private int startingTimeValue;
    JLabel message;
    Properties gameOptions,levelProperties;
    Timer count= new Timer(); 
    private boolean isPaused;
    
    /**
     * Constructor creates message about the time and displays it.
     * @param p properties of the game.
     * @param l properties of the current level.
     */
     public Counter(Properties p,Properties l){
        gameOptions=p;
        levelProperties=l;
        startingTimeValue=loadTime();
        createMessage();
        startCounter();
    }
    
    
    TimerTask task = new TimerTask(){
        @Override
        public void run(){
           if(!isPaused){
           if(startingTimeValue!=0)
           startingTimeValue--;
           }
           refresh();  
        }
    };
    /**
     * This function calculate value of the timer every second
     */
    private void startCounter(){
        if(startingTimeValue!=0){
        count.scheduleAtFixedRate(task, 1000,1000);
        }
    }
    /**
     * Used for first-time creation of message
     */
    private void createMessage(){
        message= new JLabel();
        message.setText("Seconds left: "+ startingTimeValue);
        panel.add(message);
        
        
    }
    /**
     * Used to change the message
     */
    private void changeMessage(){
        message.setText("Seconds left: "+ startingTimeValue);
    }
    /**
     * Refreshes the clock
     */
    private void refresh(){
        changeMessage();
        this.repaint();
    }
    
    /**
     * Gets starting value of the time form config file
     */
    private int loadTime(){
            int timeForLevel=Integer.parseInt(levelProperties.getProperty("Time_For_Level"));
            return timeForLevel;
        }
    
    /**
     * Get timer value from the counter.
     * @return Value of timer.
     */
    public int getTime(){
        return this.startingTimeValue;
    }
    
    public void restart(){
        startingTimeValue=loadTime();
    }
    
    
    public void stop(){
        this.isPaused=true;
    }
    
    public void continueCounting(){
        this.isPaused=false;
    }
    
}