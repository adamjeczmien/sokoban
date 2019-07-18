package sokoban;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Properties;

/**
 * Class, wich is responsible for player avatar
 * @author jeczm
 */
public class Avatar extends GameObject{
    private static final String PICTURE = "avatar.gif";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private GameObject upNeighbor;
    private GameObject downNeighbor;
    private GameObject leftNeighbor;
    private GameObject rightNeighbor;
    private LevelMap map;
    boolean isRunning;
   
    
    Image img;
    /**
     * Constructor assing placement of the avatar 
     * @param someX position on X axis, given by config file.
     * @param someY position on Y axis, given by config file.
     * @param m reference on current map.
     * @param p properties of current level.
     */
    public Avatar(int someX, int someY, LevelMap m,Properties p){
        super(p);
        this.addNotify();
        x=someX;
        y=someY;
        map=m;
    }
    
    
    /**
     * Chooses witch way avatar should go
     * @param direction String defining direction
    */
    public void move(String direction){
        if(isRunning){
            return;
        }
        switch (direction) {
            case LEFT:
                this.moveLeft();
                break;
            case UP:
                this.moveUp();
                break;
            case DOWN:
                this.moveDown();
                break;
            case RIGHT:
                this.moveRight();
                break;
            default:
                break;
        }
        
        
    }

    /**
     * Chooses witch way avatar should go if pull option is on
     * @param direction String defining direction
    */
    public void moveWithPull(String direction){
        if(isRunning){
            return;
        }
        switch (direction) {
            case LEFT:
                this.moveLeftWithPull();
                break;
            case UP:
                this.moveUpWithPull();
                break;
            case DOWN:
                this.moveDownWithPull();
                break;
            case RIGHT:
                this.moveRightWithPull();
                break;
            default:
                break;
        }
        
    }
    
    
    
    /**
     * Used to move avatar up and creates animation Thread
     */
    public void moveUp(){
        
        if(this.upNeighbor instanceof Wall){
                
        }
        else if(this.upNeighbor instanceof Chest){
        upNeighbor.pushedUp(this);
        }
        else{
            float tempY=10*(y-1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeUp();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*y)==Math.round(tempY)){
                       y=Math.round(y);
                       break;
                    }
                    }
                    isRunning = false;
                } 
            });
           isRunning=true;
           animationThread.start();
        }
    }
    /**
     * Used to move avatar left and creates animation Thread
     */
    public void moveLeft(){
        if(this.leftNeighbor instanceof Wall){
                
        }
        else if(this.leftNeighbor instanceof Chest){
                leftNeighbor.pushedLeft(this);
        }
        else{           
            float tempX=10*(x-1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while(true){
                    changeLeft();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                
                    if(Math.round(10*x)==Math.round(tempX)){
                        x=Math.round(x);
                        break;
                    }
                }
                isRunning=false;
            } 
            });
           isRunning=true;
           animationThread.start();          
        }
    }
    /**
     * Used to move avatar down and creates animation Thread
     */
    public void moveDown(){
        if(this.downNeighbor instanceof Wall){
                
        }
        else if(this.downNeighbor instanceof Chest){
              downNeighbor.pushedDown(this);
        }
        else{
        float tempY=10*(y+1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeDown();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*y)==Math.round(tempY)){
                        y=Math.round(y);
                        break;
                    }
                    }
                 isRunning=false;
            } 
            });
           isRunning=true;
           animationThread.start();
        }
    }
    /**
     * Used to move avatar right and creates animation Thread
     */
    public void moveRight(){
        if(this.rightNeighbor instanceof Wall){
                
        }
        else if(this.rightNeighbor instanceof Chest){
                rightNeighbor.pushedRight(this);
        }
        else{
        float tempX=10*(x+1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeRight();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*x)==Math.round(tempX)){
                       x=Math.round(x);
                       break;
                    }
                    }
                isRunning=false;
            } 
            });
           isRunning=true;
           
           animationThread.start();
        }
    }
    
    
    /**
     * Used to move avatar up (with the pull option on) and creates animation Thread
     */
    public void moveUpWithPull(){
        if(this.upNeighbor instanceof Wall||this.upNeighbor instanceof Chest){       
        }
        else if(this.downNeighbor instanceof Chest){
                downNeighbor.pushedUp(this);
        }
        else{
            float tempY=10*(y-1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeUp();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*y)==Math.round(tempY)){
                       y=Math.round(y);
                       break;
                    }
                    }
                isRunning=false;
            } 
            });
           isRunning=true;
           
           animationThread.start();
        }
    }
    /**
     * Used to move avatar left (with the pull option on) and creates animation Thread
     */
    public void moveLeftWithPull(){
        if(this.leftNeighbor instanceof Wall||this.leftNeighbor instanceof Chest){
        }
        else if(this.rightNeighbor instanceof Chest){
                rightNeighbor.pushedLeft(this);
        }
        else{           
            float tempX=10*(x-1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while(true){
                    changeLeft();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                        } 
                    catch (Exception ex) {} 
                
                    if(Math.round(10*x)==Math.round(tempX)){
                        x=Math.round(x);
                        break;
                    }
                }
                isRunning=false;
            } 
            });
            isRunning=true;
            animationThread.start();          
        }
    }
    /**
     * Used to move avatar down (with the pull option on) and creates animation Thread
     */
    public void moveDownWithPull(){
        if(this.downNeighbor instanceof Wall||this.downNeighbor instanceof Chest){      
        }
        else if(this.upNeighbor instanceof Chest){
                upNeighbor.pushedDown(this);
        }
        else{
        float tempY=10*(y+1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeDown();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*y)==Math.round(tempY)){
                        y=Math.round(y);
                        break;
                    }
                    }
                isRunning=false;
            } 
            });
           isRunning=true;
           animationThread.start();
        }
    }
    /**
     * Used to move avatar right (with the pull option on) and creates animation Thread
     */
    public void moveRightWithPull(){
        if(this.rightNeighbor instanceof Wall||this.rightNeighbor instanceof Chest){
                
        }
        else if(this.leftNeighbor instanceof Chest){
                leftNeighbor.pushedRight(this);     
        }
        else{
        float tempX=10*(x+1); 
            Thread animationThread = new Thread(new Runnable() {
                public void run() {
                    while(true){
                    changeRight();
                    map.panel.repaint();
                    try {
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {} 
                    if(Math.round(10*x)==Math.round(tempX)){
                       x=Math.round(x);
                       break;
                    }
                    }
                isRunning=false;
            } 
            });
           isRunning=true;
           
           animationThread.start();
           
        }
    }
    
    
    
    /**
     * Changes x of the avatar
     */
    public void changeLeft(){
        x=x-0.1f; 
    }
    /**
     * Changes x of the avatar
     */
    public void changeRight(){
        x=x+0.1f;
    }
    /**
     * Changes y of the avatar
     */
    public void changeUp(){
        y=y-0.1f;
    }
    /**
     * Changes y of the avatar
     */
    public void changeDown(){
        y=y+0.1f;
    }
    
    /**
     * Function used to check if avatar has any neighbours
     * @param levelMap reference on current map.
     */
    public void setNeighbors(LevelMap levelMap){
        upNeighbor=null;
        downNeighbor=null;
        leftNeighbor=null;
        rightNeighbor=null;
        Chest tempChest;
        for(int i=0;i<levelMap.numOfChests;i++){
            tempChest=levelMap.chestList.get(i);
            if(tempChest.x==this.x){
                if(tempChest.y==this.y-1){
                    upNeighbor=tempChest;
                }
                else if(tempChest.y==this.y+1){
                    downNeighbor=tempChest;
                }
            }
            else if(tempChest.y==this.y){
                if(tempChest.x==this.x-1){
                    leftNeighbor=tempChest;
                }
                else if(tempChest.x==this.x+1){
                    rightNeighbor=tempChest;
                }
            }
        }
        Wall tempWall;
        for(int i=0;i<levelMap.numOfWalls;i++){
            tempWall=levelMap.wallList.get(i);
            if(tempWall.x==this.x){
                if(tempWall.y==this.y-1){
                    upNeighbor=tempWall;
                }
                else if(tempWall.y==this.y+1){
                    downNeighbor=tempWall;
                }
            }
            else if(tempWall.y==this.y){
                if(tempWall.x==this.x-1){
                    leftNeighbor=tempWall;
                }
                else if(tempWall.x==this.x+1){
                    rightNeighbor=tempWall;
                }
            }
        }
        if(this.x==1){
            leftNeighbor=new Wall(0,0,levelProperties);
        }
        if(this.y==1){
            upNeighbor=new Wall(0,0,levelProperties);
        }
        if(this.x==levelMap.mapWidth-2){
            rightNeighbor=new Wall(0,0,levelProperties);
        }
        if(this.y==levelMap.mapHeight-2){
            downNeighbor=new Wall(0,0,levelProperties);
        }
        
        
    }
    
    /**
     * Adds Image to the object
     */
    @Override
    public void addNotify() {
        super.addNotify();
        img = Toolkit.getDefaultToolkit().getImage(PICTURE);
        img=img.getScaledInstance(width, height, 0);
    }
    /**
     * Draws avatar
     * @param g graphical context
     */
    @Override
    public final void draw(Graphics g){
            g.drawImage(img,(int)Math.round(x*width), (int)Math.round(y*height), this);
    }
    /**
     * Changes Dimensions of the element
     * @param h new height
     * @param w new width 
     */
     public void resizeElement(int h, int w){
        height=h;
        width=w;
        img=img.getScaledInstance(width, height, 0);
     }
   
}