package sokoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;

/**
 * Displays rules of the game.
 * @author jeczm
 */
public class Rules extends JFrame{
    File rulesFile;
    Properties rules;
    private String rulesMessage="";   
    private int numOfRules;
    
    public Rules(){
       JPanel panel= new JPanel();
       this.add(panel);
       importRules();
       createMessage();
       JOptionPane.showMessageDialog(Rules.this, rulesMessage, "Rules", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Imports rules from the file.
     */
    private void importRules(){
        rulesFile=new File("rules.properties");
        rules=new Properties();
        InputStream is;
        try {
            is = new FileInputStream(rulesFile);
            rules.load(is);
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
        numOfRules=Integer.parseInt(rules.getProperty("number_of_rules"));
        for(int i=1;i<numOfRules+1;i++){
            rulesMessage=rulesMessage+i+". "+rules.getProperty("rule_"+i)+"\n";
        }
    }
}
