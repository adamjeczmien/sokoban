package sokoban;

import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
/**
 * This the class of the main menu, also it is starting class,which have 
 * main function. Acts as a "launcher".
 * @author jeczm
 */
public class MainMenu extends JFrame {
    
    MenuControler control;
    JButton playButton , exitButton , leaderboardButton , optionsButton;
    JButton onlineButton;
    JLabel gameTitle;
    
    
    
    public MainMenu(){
        this.control= new MenuControler();
        windowCreation();
        createButtons();
        createButtonsListeners();
        layoutCreator();
        makeVisible();          
    }
    /**
     * Creating main menu window 
     */
    private void windowCreation(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sokoban");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
    }
    /**
     * Creating components
     */
    private void createButtons(){
        playButton =        new JButton("Play");
        playButton.setToolTipText("LET'S PLAY!");
        exitButton =        new JButton("Exit");
        exitButton.setToolTipText("Exit the game");
        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setToolTipText("See who is best");
        optionsButton=      new JButton("Options");
        optionsButton.setToolTipText("Adjust the game");
        onlineButton=       new JButton("Go Online");
        onlineButton.setToolTipText("Check online version");
    }
    /** 
     * Takes care of making layout of OptionPane
     */
    private void layoutCreator(){
        JPanel layoutCreated= new JPanel();
        layoutCreated.setLayout(new BorderLayout());
        Box separate= Box.createVerticalBox();
        Box adjust = Box.createHorizontalBox();
        JPanel title= new JPanel();
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        Font titleFont = new Font("Arial", Font.BOLD, 50);
        Font buttonsFont = new Font("Arial",Font.PLAIN, 10);
        
        GridBagConstraints gridConstraints = new GridBagConstraints();
        
        
        gridConstraints.gridx=1;
        gridConstraints.gridy=1;
        gridConstraints.gridwidth= 1;
        gridConstraints.gridheight=1;
        gridConstraints.weightx=50;
        gridConstraints.weighty=100;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor= GridBagConstraints.SOUTH;
        gridConstraints.fill= GridBagConstraints.BOTH;
        
        gameTitle = new JLabel("SOKOBAN");
        gameTitle.setFont(titleFont);
        gameTitle.setForeground(Color.red);
        title.add(gameTitle);
        
        gridConstraints.gridx=1;
        gridConstraints.gridwidth=20;
        
        menu.add(playButton,gridConstraints);
        playButton.setFont(buttonsFont);
        
        gridConstraints.gridy=2;
        menu.add(onlineButton,gridConstraints);
        onlineButton.setFont(buttonsFont);
        
        gridConstraints.gridy=3;
        menu.add(leaderboardButton,gridConstraints);
        leaderboardButton.setFont(buttonsFont);
        
        gridConstraints.gridy=4;
        menu.add(optionsButton,gridConstraints);
        optionsButton.setFont(buttonsFont);
        
        gridConstraints.gridy=5;
        menu.add(exitButton,gridConstraints);
        exitButton.setFont(buttonsFont);
        
        adjust.add(Box.createHorizontalStrut(this.getWidth()/3));
        adjust.add(menu);
        adjust.add(Box.createHorizontalStrut(this.getWidth()/3));
        
        separate.add(title);
        separate.add(adjust);
        
        layoutCreated.add(separate, BorderLayout.CENTER);
        
        this.add(layoutCreated);
        
    }
    /**
     * Creates and assign button listeners
     */
    private void createButtonsListeners(){
        MenuButtons listenButton= new MenuButtons();
        MenuButtons listenPlay= new MenuButtons(this);
        playButton.addActionListener(listenPlay);
        exitButton.addActionListener(listenButton);
        leaderboardButton.addActionListener(listenButton);
        optionsButton.addActionListener(listenButton);
        onlineButton.addActionListener(listenButton);
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
    
    /**
     * Subclass used to define ActionLiteners
     */
    private class MenuButtons implements ActionListener{
        private MainMenu menu;
        MenuButtons(){}
        MenuButtons(MainMenu m){
            menu=m;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
           if(ae.getSource()==exitButton){
              System.exit(0);
            }
           else if(ae.getSource()==leaderboardButton){
               control.openLeaderboard();
            }
           else if(ae.getSource()==optionsButton){
               control.openOptionPane();
           }
           else if(ae.getSource()==onlineButton){
               //go online
           }
           else {
               control.startGame(menu);
           }
          
        }
    }
}