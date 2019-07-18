package sokoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JComponent;

/**
 * Abstract class, which chooses config file to load and saves properties of specific level
 * @author jeczm
 */
public class CurrentLevel extends JComponent{
    private static final String OPTIONS = "options.properties";
    private static final String DEFAULTNAME = "level";
    private static final String EXTENTION = ".properties";
    Properties levelProperties,gameOptions;
    File currentFile,optionFile;
    GameControler control;
    int currentLevel;
    /**
     * Opens level file and loads properties.
     * @param i number of level.
     */
    protected void openFile(int i){
        currentFile = new File(DEFAULTNAME+i+EXTENTION);
        levelProperties = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(currentFile);
            levelProperties.load(is);
        } 
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }    
    }
    /**
     * Opens options file and loads properties.
     */
    private void openOptions(){
        optionFile = new File(OPTIONS);
        gameOptions = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(optionFile);
            gameOptions.load(is);
        } 
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
       
    }
    /**
     * Constructor opens option file.
     */
    public CurrentLevel(){
        openOptions();
    }
   
    
}
