package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Properties;

/**
 * This class creates and paints moveable chests which will be pushed by the player 
 * @author jeczm
 */
public class Chest extends GameObject{
    private static final String PICTURE = "chest.png";
    private static final String PICTURE2 = "chest2.png";
    private GameObject upNeighbor;
    private GameObject downNeighbor;
    private GameObject leftNeighbor;
    private GameObject rightNeighbor;
    private LevelMap map;
    public boolean isOnEndpoint;
    Image img, imgEnd;
    
    /**
     * Constructor assing placement of the chest
     * @param someX position on X axis, given by config file.
     * @param someY position on Y axis, given by config file.
     * @param m reference on current map.
     * @param p properties of current level.
     */
    public Chest(int someX, int someY, LevelMap m, Properties p){
        super(p);
        x=someX;
        y=someY;
        map=m;
        this.addNotify();  
    }
    
    /**
     * Checks if the chest is currently on the ending point.
     * @param levelMap reference on current map.
     */
    public void checkIfOnEndpoint(LevelMap levelMap){
        Endpoint tempEnd;
        for(int i=0;i<levelMap.numOfChests;i++){
            tempEnd=levelMap.endpointList.get(i);
            if(this.x==tempEnd.x)
            {
                if(this.y==tempEnd.y){
                    tempEnd.setOccupied(true);
                    this.isOnEndpoint=true;
                    break;
               } 
            }
            else {
               tempEnd.setOccupied(false); 
               this.isOnEndpoint=false;
            }
        }
        levelMap.checkLevelFinished();
    }
    /**
     * Function used to check if chest has any neighbours
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
     * Function defining action if pushed left. Creates the animation Thread.
     * @param p reference on player Avatar
     */
    @Override
    public void pushedLeft(Avatar p){
       if(this.leftNeighbor instanceof Wall){
                
       }
       else if(this.leftNeighbor instanceof Chest){
                
       }
       else{
           float tempX=10*(x-1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
            while(true){
                p.changeLeft();
                changeLeft();
                map.panel.repaint();
                try {
                    Thread.sleep(10);
                    } 
                catch (Exception ex) {} 
                if(Math.round(10*x)==Math.round(tempX)){
                    x=Math.round(x);
                    p.x=Math.round(p.x);
                    checkIfOnEndpoint(map);
                    break;
                }
            }
        } 
    });
           animationThread.start();  
       }
    }
    /**
     * Function defining action if pushed up. Creates the animation Thread.
     * @param p reference on player Avatar
     */
    @Override
    public void pushedUp(Avatar p){
       if(this.upNeighbor instanceof Wall){       
       }
       else if(this.upNeighbor instanceof Chest){        
       }
       else{
           float tempY=10*(y-1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
            while(true){
                p.changeUp();
                changeUp();
                map.panel.repaint();
                try {
                    Thread.sleep(10);
                    } 
                catch (Exception ex) {} 
                if(Math.round(10*y)==Math.round(tempY)){
                    y=Math.round(y);
                    p.y=Math.round(p.y);
                    checkIfOnEndpoint(map);
                    break;
                }
            }
        } 
    });
           animationThread.start();
       }
    }
    /**
     * Function defining action if pushed right. Creates the animation Thread.
     * @param p reference on player Avatar
     */
    @Override
    public void pushedRight(Avatar p){
       if(this.rightNeighbor instanceof Wall){        
       }
       else if(this.rightNeighbor instanceof Chest){      
       }
       else{
          float tempX=10*(x+1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
            while(true){
                p.changeRight();
                changeRight();
                map.panel.repaint();
                try {
                    Thread.sleep(10);
                    } 
                catch (Exception ex) {} 
                if(Math.round(10*x)==Math.round(tempX)){
                    x=Math.round(x);
                    p.x=Math.round(p.x);
                    checkIfOnEndpoint(map);
                    break;
                }
            }
        } 
    });
           animationThread.start(); 
       }
    }
    /**
     * Function defining action if pushed down. Creates the animation Thread.
     * @param p reference on player Avatar
     */
    @Override
    public void pushedDown(Avatar p){
       if(this.downNeighbor instanceof Wall){           
       }
       else if(this.downNeighbor instanceof Chest){   
       }
       else{
           float tempY=10*(y+1); 
 
            Thread animationThread = new Thread(new Runnable() {
            public void run() {
            while(true){
                p.changeDown();
                changeDown();
                map.panel.repaint();
                try {
                    Thread.sleep(10);
                    } 
                catch (Exception ex) {} 
                if(Math.round(10*y)==Math.round(tempY)){
                    y=Math.round(y);
                    p.y=Math.round(p.y);
                    checkIfOnEndpoint(map);
                    break;
                }
            }
        } 
    });
           animationThread.start();
       }
    }
    
    /**
     * Changes x of the chest
     */
    public void changeLeft(){
        x=x-0.1f; 
    }
    /**
     * Changes x of the chest
     */
    public void changeRight(){
        x=x+0.1f;
    }
    /**
     * Changes y of the chest
     */
    public void changeUp(){
        y=y-0.1f;
    }
    /**
     * Changes y of the chest
     */
    public void changeDown(){
        y=y+0.1f;
    }
    
     /**
     * Adds Image to the object
     */
    @Override
    public void addNotify() {
        super.addNotify();
        img = Toolkit.getDefaultToolkit().getImage(PICTURE);
        imgEnd= Toolkit.getDefaultToolkit().getImage(PICTURE2);
        img=img.getScaledInstance(width, height, 0);
        imgEnd=imgEnd.getScaledInstance(width, height, 0);
    }
     /**
     * Draws chest
     * @param g graphical context
     */
    @Override
    public final void draw(Graphics g){
            if(this.isOnEndpoint){
            g.drawImage(imgEnd, (int)Math.round(x*width), (int)Math.round(y*height), this);
            g.setColor(Color.BLACK);
            g.drawRect((int)Math.round(x*width), (int)Math.round(y*height), width, height);  
            }
            else{
            g.drawImage(img, (int)Math.round(x*width), (int)Math.round(y*height), this);
            g.setColor(Color.BLACK);
            g.drawRect((int)Math.round(x*width), (int)Math.round(y*height), width, height);
            }
    }
     public void resizeElement(int h, int w){
        height=h;
        width=w;
        img=img.getScaledInstance(width, height, 0);
        imgEnd=imgEnd.getScaledInstance(width, height, 0);
     }
 }
