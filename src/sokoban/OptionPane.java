package sokoban;
import java.io.*;
import java.util.Properties;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
 /**
  * This is class, which takes care of options of the game
  * @author jeczmien
  */
public class OptionPane extends JFrame{
    JButton returnButton, applyButton;
    JSlider level;
    JRadioButton hard, easy, medium, soundOn, soundOff;
    JPanel levelsPanel, soundPanel;
    JLabel label;
    private int startLevel;
    private String diffLevel;
    public static boolean isOpened;
    /**
     * Constructor gets options from file and sets components
     */
    public OptionPane(){
        isOpened=true;
        createWindow();
        createComponents();
        asignListiners();
        layoutManager();
        getValuesFromFile();
        setOptions();
    }

    
    /**
     * Listen for events of the buttons
     */
    private class ListenForButton implements ActionListener{
        private final OptionPane op;
        ListenForButton(OptionPane o){
            op = o;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==returnButton){
                isOpened=false;
                op.dispose();
            }
            else if(ae.getSource()==applyButton){
                apply();
                isOpened=false;
                op.dispose();
            }
        }
    }
    /** 
     * Listen for events of slider
    */
    private class ListenForSlider implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent ce) {
            if(ce.getSource()==level){
                label.setText("Start from Level: "+ level.getValue());
            }
        }
        
    }
    
    /**
     * Creating window of Options 
     */
    private void createWindow(){
        this.setSize(350,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Options");
    }   
    /**
     * Creating components
     */
    private void createComponents(){
        returnButton= new JButton("Return");
        applyButton = new JButton("Apply");
        levelSelection();
        levelSliders();
    }
    /**
     * This function assign listiners to buttons and sliders
     */
    private void asignListiners(){
        ListenForButton lForButton= new ListenForButton(this);
        ListenForSlider lForSlider= new ListenForSlider();
        returnButton.addActionListener(lForButton);
        applyButton.addActionListener(lForButton);
        level.addChangeListener(lForSlider);
    }
    /**
     * Creates components of JPanel, wich is responsible for choosing difficulty
     * level
     */
    private void levelSelection(){
       easy   = new JRadioButton("Easy");
       medium = new JRadioButton("Medium");
       hard   = new JRadioButton("Hard");
       ButtonGroup levels = new ButtonGroup();
       
       levels.add(easy);
       levels.add(medium);
       levels.add(hard);
       
       levelsPanel = new JPanel();
       Border border = BorderFactory.createTitledBorder("Choose difficulity");
       levelsPanel.setBorder(border);
       
       levelsPanel.add(easy);
       levelsPanel.add(medium);
       levelsPanel.add(hard);
    }
    /**
     * Creates components of JPanel, wich is responsible for choosing starting
     * level
     */
    private void levelSliders(){
        label = new JLabel("Start from Level:  ");
        level = new JSlider(1,10,1);
        level.setMajorTickSpacing(1);
        level.setPaintTicks(true);
        level.setPaintLabels(true);
    }
    /** 
     * Takes care of making layout of OptionPane
     */
    private void layoutManager(){
        JPanel thePanel= new JPanel();
        thePanel.setLayout(new BorderLayout());
        
        Box mainBox = Box.createVerticalBox();
        Box checkSelect = Box.createHorizontalBox();
        JPanel levels = new JPanel();
        levels.add(label);
        levels.add(level);
        JPanel buttons = new JPanel();
        buttons.add(applyButton);
        buttons.add(returnButton);

        checkSelect.add(levelsPanel);
        mainBox.add(checkSelect);
        mainBox.add(levels);
        mainBox.add(buttons);
        thePanel.add(mainBox);
        this.add(thePanel);
        makeVisible(); 
    }
    
    
    /**
     * Takes set options and then writes them into config file 
     */
    private void apply(){
    OutputStream os;
    File f = new File("options.properties");
    Properties properties = new Properties();
    startLevel=level.getValue();
    String lvl=""+startLevel;
    diffLevel=getDiff();
    try {
        os = new FileOutputStream(f);
        properties.setProperty("Start_Level",lvl);
        properties.setProperty("Difficulity_Level",diffLevel);
        properties.store(os, null);
    }
    catch (FileNotFoundException e) {
        } 
    catch (IOException e) {
        }
    }
    /**
     * Gets value from levelSelection and turns it into the string
     * @return Returns difficulity in the form of the String 
     */
    private String getDiff(){
        String difficulity;
        if(easy.isSelected())
        {
            difficulity="Easy";
        }
        else if(hard.isSelected())
        {
            difficulity="Hard";
        }
        else 
        {
            difficulity="Medium";
        }
        return difficulity;
    }
    /**
     * Gets previous options form config file
     */
    private void getValuesFromFile(){
        File f = new File("options.properties");
        Properties properties = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(f);
            properties.load(is);
        } 
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
        diffLevel=properties.getProperty("Difficulity_Level");
        startLevel=Integer.parseInt(properties.getProperty("Start_Level"));
    }
    /**
     * Sets previous options
     */
    private void setOptions(){
        level.setValue(startLevel);
        switch (diffLevel) {
            case "Easy":
                easy.setSelected(true);
                break;
            case "Hard":
                hard.setSelected(true);
                break;
            default:
                medium.setSelected(true);
                break;
        }
    }
    
    /**
     * Simple function to make window visible, and keep code clean.
     */
    private void makeVisible(){
        EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				setVisible(true);
			}
		});
    }
}   