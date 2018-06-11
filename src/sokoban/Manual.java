package sokoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Displays tips how to play the game.
 * @author jeczm
 */
public class Manual extends JFrame{
    File manualFile;
    Properties manual;
    private String manualMessage="";   
    private int numOfTips;
    
    public Manual(){
       JPanel panel= new JPanel();
       this.add(panel);
       importManual();
       createMessage();
       JOptionPane.showMessageDialog(Manual.this, manualMessage, "Rules", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Imports Manual from the file.
     */
    private void importManual(){
        manualFile=new File("manual.properties");
        manual=new Properties();
        InputStream is;
        try {
            is = new FileInputStream(manualFile);
            manual.load(is);
        } 
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }
    
    /**
     * Creates message.
     */
    private void createMessage(){
        numOfTips=Integer.parseInt(manual.getProperty("number_of_tips"));
        for(int i=1;i<numOfTips+1;i++){
            manualMessage=manualMessage+i+". "+manual.getProperty("tip_"+i)+"\n";
        }
    }
}
