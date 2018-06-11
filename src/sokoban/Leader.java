package sokoban;

/**
 * Class defining single leader on leaderboard.
 * @author jeczm
 */
public class Leader {
    
    String name;
    int score;
        
    /**
     * Assigns the name and score to the leader.
     * @param n new name of the leader.
     * @param s new score of the leader.
     */   
    public Leader(String n,int s){
        name=n;
        score=s;
    }
    
    /**
     * Used to rename the leader.
     * @param newName new name of the leader.
     */    
    public void rename(String newName){
        name = newName;
    }
    
    /**
     * Used to change the score of the leader.
     * @param newScore new score.
     */
    public void setNewScore(int newScore){
        score=newScore;
    }
    
    /**
     * Check if the score is better then current score.
     * @param someScore new score.
     * @return true if the new score is better than old.
     */
    public boolean checkIfBetterScore(int someScore){
        return someScore>score;
    }
}
