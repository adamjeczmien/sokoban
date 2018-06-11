package sokoban;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.*;

/**
 * Class, which object is created when game is finished.
 * @author jeczm
 */
public class FinishGame extends JPanel{
        Game game;
        JPanel writeLeaders,endOfGame;
        JButton save,noThanks,exit,playAgain,returnToMain, leaders;
        JTextField newName;
        private static final String NUMBEROFLEADERS="number_of_leaders";
        File leadersFile;
        Properties leaderboard;
        private List<Leader> leadersList;
        int points;
        String name;
        private int numberOfLeaders;
        private int placeOnList;
        
        
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
         * Creates list of the objects Leader
         */
        private void createLeaders(){
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
         * Checks if the current player is the new leader.
         * @return  
         */
        private boolean checkIfNewLeader(){
            placeOnList=numberOfLeaders+1;
            Leader tempLeader;
            for(int i=0;i<numberOfLeaders;i++){
                tempLeader=leadersList.get(i);
                if(tempLeader.checkIfBetterScore(points)){
                    placeOnList=i;
                    break;
                }
                else{}
            }
            return placeOnList<=numberOfLeaders; 
        }
        
        /**
         * Creates ending screen of the game.
         */
        private void makeEndScreen(){
            this.removeAll();
            endOfGame= new JPanel();
            exit=        new JButton("        EXIT       ");
            playAgain=   new JButton("     Play Again    ");
            returnToMain=new JButton("Return to Menu");
            leaders=     new JButton("   See Leaders  ");
            ListenForEndingAction lForAction=new ListenForEndingAction(game);
            exit.addActionListener(lForAction);
            leaders.addActionListener(lForAction);
            returnToMain.addActionListener(lForAction);
            playAgain.addActionListener(lForAction);
            JLabel label=new JLabel();
            JLabel label2=new JLabel();
            label.setText("THE GAME HAS FINISHED");
            label2.setText("Your score is "+this.points);
            endOfGame.setLayout(new GridLayout(0,1,10,10));
            endOfGame.add(label);
            endOfGame.add(label2);
            endOfGame.add(playAgain);
            endOfGame.add(returnToMain);
            endOfGame.add(leaders);
            endOfGame.add(exit);
            this.add(endOfGame);
            game.setSize(400, 400);
        }
        
        /**
         * Creates frame which allows current player to write himself into the 
         * leaderboard.
         */
        private void makeWriteIntoLeaders(){
            JLabel label1=new JLabel();
            label1.setText("THE GAME HAS FINISHED");
            JLabel label2=new JLabel();
            label2.setText("Your score is "+this.points);
            JLabel label3=new JLabel();
            label3.setText("You are "+(placeOnList+1)+" on the leaderboard");
            newName=new JTextField("Enter your name",10);
            save=new JButton("Save");
            noThanks=new JButton("Skip");
            writeLeaders=new JPanel();
            writeLeaders.setLayout(new GridLayout(0,1,10,10));
            Box buttons=Box.createHorizontalBox();
            buttons.add(save);
            buttons.add(noThanks);
            ListenForWriting lForWrite= new ListenForWriting(this);
            save.addActionListener(lForWrite);
            noThanks.addActionListener(lForWrite);
            writeLeaders.add(label1);
            writeLeaders.add(label2);
            writeLeaders.add(label3);
            writeLeaders.add(newName);
            writeLeaders.add(buttons);
            this.add(writeLeaders);
            game.setSize(350, 350);
        }
        
        /**
         * 
         * @param p Reference on Points object.
         * @param g Reference on current game frame.
         */
        public FinishGame(Points p,Game g){
            points = p.getPoints();
            game=g;
            importLeaderboard();
            createLeaders();
            if(checkIfNewLeader())
                makeWriteIntoLeaders();
            else
                makeEndScreen();
        }
        
        /**
         * Removes last leader from teh list and adds new leader
         * on the right place.
         */
        public void saveLeader(){
            String tempName=newName.getText();
            int tempScore=points;
            Leader leaderToRemove;
            leaderToRemove=leadersList.get(numberOfLeaders-1);
            leadersList.remove(leaderToRemove);
            leadersList.add(placeOnList,new Leader(tempName,tempScore));
        }
        
        /**
         * Writes leader list into the file.
         */
        public void writeLeadersIntoFile(){
            OutputStream os;
            leadersFile=new File("leaderboard.properties");
            leaderboard=new Properties();
            try {
                os = new FileOutputStream(leadersFile);
                leaderboard.setProperty("number_of_leaders",numberOfLeaders+"");
                for(int i=0;i<numberOfLeaders;i++){
                    Leader tempLeader;
                    tempLeader=leadersList.get(i);
                    int tempScore=tempLeader.score;
                    String tempName=tempLeader.name;
                    leaderboard.setProperty("name_"+(i+1)+"_leader",tempName);
                    leaderboard.setProperty("score_"+(i+1)+"_leader",tempScore+"");
                }
                leaderboard.store(os, null);
            }
            catch (FileNotFoundException e) {
            } 
            catch (IOException e) {
            }   
        }
        
    /**
     * Action listener for the last game screen.
     */    
    private class ListenForEndingAction implements ActionListener{
            private Game game;
            
            public ListenForEndingAction(Game g){
                game=g;
            }
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==exit){
                    System.exit(0);
                }
                else if(ae.getSource()==leaders){
                    new Leaderboard();
                }
                else if(ae.getSource()==returnToMain){
                    game.dispose();
                    new MainMenu();
                }
                else if(ae.getSource()==playAgain){
                    game.closeGame();
                    new Game();
                }
            }
            
        }
        
    /**
     * Action listener for the "write into leaderboard" screen.
     */
    private class ListenForWriting implements ActionListener{
        
        FinishGame fg;
        
        public ListenForWriting(FinishGame f){
            fg=f;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==save){
                saveLeader();
                writeLeadersIntoFile();
                makeEndScreen();
            }
            else
                makeEndScreen();
        } 
    }
}
