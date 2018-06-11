package sokoban;

/**
 * Controls main menu.
 * @author jeczm
 */
public class MenuControler {
    /**
     * Opens Options if they are not already opened
     */
    public void openOptionPane(){
        if(!OptionPane.isOpened){
            new OptionPane();
        }
        else{
        //Don't open
        }
        
    }
    
    /**
     * Opens leadersboard
     */
    public void openLeaderboard(){
        new Leaderboard();
    }
    
    /**
     * Starts game if they OptionPane is not opened.
     * @param m Reference on the Main Menu screen.
     */
    public void startGame(MainMenu m){
        if(!OptionPane.isOpened){
           m.dispose();
           new Game();
        }
        else{
        //Don't open
        }
    }
}