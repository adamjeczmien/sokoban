package sokoban;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * Reads leaders from file and displays on the screen.
 * @author jeczm
 */
public class Leaderboard extends JFrame {
    private static final String NUMBEROFLEADERS="number_of_leaders";
    File leadersFile;
    Properties leaderboard;
    private List<Leader> leadersList;
    private String message="";
    private int numberOfLeaders;
    
    public Leaderboard(){
        JPanel thePanel= new JPanel();
        this.add(thePanel);
        importLeaderboard();
        makeLeadersList();
        createMessage();
        JOptionPane.showMessageDialog(Leaderboard.this, message, "Leaders", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
    * Imports leaderboard from the file.
    */
    private void importLeaderboard(){
        leadersFile=new File("leaderboard.properties");
        leaderboard=new Properties();
        InputStream is;
        try {
            is = new FileInputStream(leadersFile);
            leaderboard.load(is);
        } 
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }
    
    /**
     * Creates leaders list.
     */
     private void makeLeadersList(){
       leadersList = new ArrayList<>();
       numberOfLeaders=Integer.parseInt(leaderboard.getProperty(NUMBEROFLEADERS));
       String tempName;
       int tempScore;
       for(int i=0;i<numberOfLeaders;i++){
           tempName=leaderboard.getProperty("name_"+(i+1)+"_leader");
           tempScore=Integer.parseInt(leaderboard.getProperty("score_"+(i+1)+"_leader"));
           leadersList.add(i,new Leader(tempName,tempScore));
       }
    }
     
     /**
      * Creates message for display.
      */
     private void createMessage(){
       Leader tempLeader;
       for(int i=0;i<numberOfLeaders;i++){
           tempLeader=leadersList.get(i);
           message=message+(i+1)+". "+tempLeader.name+"  "+tempLeader.score+" points\n"; 
       }
    }
}