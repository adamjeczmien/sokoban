package sokoban;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class which deals with current level map.
 * @author jeczm
 */
public class LevelMap extends CurrentLevel{
    private static final String MAP_WIDTH = "map_width";
    private static final String MAP_HEIGHT = "map_height";
    private static final String NUM_OF_CHESTS = "number_of_chests";
    private static final String NUM_OF_WALLS = "number_of_walls";
   
    public boolean levelFinished;
    public boolean isPaused;
    public DrawMap panel;
    public int mapWidth, mapHeight;
    public int elementWidth,elementHeight;
    public int numOfWalls, numOfChests;
    
    protected List<Chest> chestList;
    protected List<Wall> wallList;
    protected List<Endpoint> endpointList;
    
    public Avatar player;
    public Background bg;
    
    /**
     * Used to define dimensions of the object
     */
    private void loadDims(){
        mapWidth=Integer.parseInt(levelProperties.getProperty(MAP_WIDTH));
        mapHeight=Integer.parseInt(levelProperties.getProperty(MAP_HEIGHT));
        elementWidth=Integer.parseInt(levelProperties.getProperty("element_width"));
        elementHeight=Integer.parseInt(levelProperties.getProperty("element_height"));
    }
    
    /**
     * Used to get info about the number of the components
     */
    private void loadLevelInfo(){
            numOfChests=Integer.parseInt(levelProperties.getProperty(NUM_OF_CHESTS));
            numOfWalls=Integer.parseInt(levelProperties.getProperty(NUM_OF_WALLS));
    }
    
    /**
     * Calls draw method of the Chest object
     * @param g Graphics method
     */
    public void drawChests(Graphics g){
        Chest tempChest;
        for(int i=0;i<numOfChests;i++){
            tempChest=chestList.get(i);
            tempChest.draw(g);
            }
    }
    /**
     * Calls draw method of the Wall object
     * @param g Graphics method
     */
    public void drawWalls(Graphics g){
        Wall tempWall;
        for(int i=0;i<numOfWalls;i++){
            tempWall=wallList.get(i);
            tempWall.draw(g);
        }
    }
    /**
     * Calls draw method of the Endpont object
     * @param g Graphics method
     */
    public void drawEndpoints(Graphics g){
        Endpoint temp;
        for(int i=0;i<numOfChests;i++){
            temp=endpointList.get(i);
            temp.draw(g);
        }
    }
    /**
     * Calls draw method of the Avatar object
     * @param g Graphics method
     */
    public void drawAvatar(Graphics g){
        player.draw(g);
        player.setNeighbors(this);
    }
    
    
    /**
     * Makes ArrayList of the Chest objects 
     */
    private void makeChestList(){
        chestList = new ArrayList<>();
        for(int i=1; i<numOfChests+1;i++){
            int temp_x,temp_y;
            temp_x=Integer.parseInt(levelProperties.getProperty("chest_"+i+"_x"));
            temp_y=Integer.parseInt(levelProperties.getProperty("chest_"+i+"_y"));      
            chestList.add(i-1,new Chest(temp_x,temp_y,this,levelProperties));
        }
    }
    /**
     * Makes ArrayList of the Wall objects 
     */
    private void makeWallList(){
        wallList = new ArrayList<>();
        for(int i=1; i<numOfWalls+1;i++){
            int temp_x,temp_y;
            temp_x=Integer.parseInt(levelProperties.getProperty("wall_"+i+"_x"));
            temp_y=Integer.parseInt(levelProperties.getProperty("wall_"+i+"_y"));
            wallList.add(i-1, new Wall(temp_x,temp_y,levelProperties));
        }
    }
    /**
     * Makes ArrayList of the Endpoint objects 
     */
    private void makeEndpointList(){
        endpointList = new ArrayList<>();
        for(int i=1; i<numOfChests+1;i++){
            int temp_x,temp_y;
            temp_x=Integer.parseInt(levelProperties.getProperty("endpoint_"+i+"_x"));
            temp_y=Integer.parseInt(levelProperties.getProperty("endpoint_"+i+"_y"));
            endpointList.add(i-1, new Endpoint(temp_x,temp_y,levelProperties));
        }
    }
    /**
     * Calls the constructor of the Avatar class
     */
    private void makeAvatar(){
        int tempX,tempY;
            tempX=Integer.parseInt(levelProperties.getProperty("avatar_x"));
            tempY=Integer.parseInt(levelProperties.getProperty("avatar_y"));
            player=new Avatar(tempX,tempY,this,levelProperties);
    }
    /**
     * Calls methods, which creates graphical components
     */
    private void makeObjectsList(){
        bg=new Background(levelProperties);
        makeChestList();
        makeWallList();
        makeEndpointList();
        makeAvatar();
        Chest tempChest;
        for(int i=0;i<numOfChests;i++){
            tempChest=chestList.get(i);
            tempChest.setNeighbors(this);
            }
    }
    
    
    /**
     * Calls all methods needed to create the game map
     */
    private void createMap(){
        loadDims();
        loadLevelInfo();
        makeObjectsList();
        for(int i=0;i<this.numOfChests;i++){
            Chest tempChest;
            tempChest=chestList.get(i);
            tempChest.checkIfOnEndpoint(this);
            }
        }
    
    /**
     * Sets neighbors for each chest.
     */
    private void setChestsNeighbors(){
        Chest tempChest;
        for(int i=0;i<numOfChests;i++){
            tempChest=chestList.get(i);
            tempChest.setNeighbors(this);
            tempChest.checkIfOnEndpoint(this);
            }
    }
    
    /**
     * Moves avatar.
     * @param direction Tells which way avatar should go. 
     */
    public void moveAvatar(String direction){
        setChestsNeighbors(); 
        this.player.move(direction);
        panel.run();
        player.setNeighbors(this);   
    }
    
    /**
     * Moves avatar if pull opiton is on
     * @param direction Tells which way avatar should go. 
     */
    public void moveAvatarWithPull(String direction){
        setChestsNeighbors(); 
        this.player.moveWithPull(direction);
        panel.run();
        player.setNeighbors(this);   
    }   
    
    /**
     * Chesks if all chests are on ending point
     */
    public void checkLevelFinished(){
       Chest tempChest;
       int licz=0;
        for(int i=0;i<numOfChests;i++){
            tempChest=chestList.get(i);
            if(tempChest.isOnEndpoint)
                licz++;
        }
     
        if(numOfChests==licz)
           this.levelFinished= true;
        else
           this.levelFinished= false;
    }
    
    
    
    /**
     * Set map to the starting point.
     */
    public void restartMap(){
        createMap();
        panel = new DrawMap(this);
        this.add(panel);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }
    
    /**
     * Loads and displays the level number i.
     * @param i level number.
     */
    public LevelMap(int i){
        this.levelFinished=false;
        openFile(i);
        createMap();
        panel = new DrawMap(this);
        panel.run();
        this.add(panel);
        panel.setFocusable(true);
    }
    
    public void pause(){
        this.isPaused=true;
    }
    
    public void continuePlaying(){
        this.isPaused=false;
    }
    
}   

