package sokoban;

/**
 * Used to transfer data between game objects
 * @author jeczm
 */
public class GameControler {
     LevelMap levelMap;
     public FinishGame end;
     int startLevelNumber;
     int nextLevel;
    /**
     * passes time left from Counter to Points class
     * @param t Counter class
     * @param p Points class
     */
    private void passTime(Counter t,Points p){
        int timeLeft;
        timeLeft = t.getTime();
        p.setTimeLeft(timeLeft);
    }
    /**
     * passes number of lives from Lives to Points class
     * @param l Lives class
     * @param p Points class
     */
    private void passLives(Lives l,Points p){
        int livesLeft;
        livesLeft = l.getLives();
        p.setLifesLeft(livesLeft);
    }
    
    /**
     * Constructor which loads starting level.
     */
    public GameControler(){
        CurrentLevel options=new CurrentLevel();
        startLevelNumber=Integer.parseInt(options.gameOptions.getProperty("Start_Level"));
        levelMap=new LevelMap(startLevelNumber);
        nextLevel=Integer.parseInt(levelMap.levelProperties.getProperty("next_level"));
    }
    
    /**
     * Congested constructor, which loads level number i.
     * @param i number of the level.
     */
    public GameControler(int i){
        levelMap=new LevelMap(i);
        nextLevel=Integer.parseInt(levelMap.levelProperties.getProperty("next_level"));
    }
    
    /**
     * Method, which is called when game is finished.
     * @param p Reference on Points object.
     * @param l Reference on Lives object.
     * @param c Reference on Counter object.
     * @param lvl Reference on LevelLabel object.
     */
    public void levelFinished(Points p, Lives l, Counter c, LevelLabel lvl){
        passLives(l,p);
        passTime(c,p);
        p.setLevelNumber(lvl.levelNumber);
        p.calculate();
    }
    
    /**
     * Method, which calls FinishGame constructor.
     * @param p Reference on Points object.
     * @param g Reference on current Game object.
     */
    public void gameFinished(Points p,Game g){
        end=new FinishGame(p,g);
    }
}