package sokoban;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.*;
/**
 * This is the class that controls the game layout.
 * @author jeczm
 */
public class Game extends JFrame{
    public boolean preformingAction, pullOn;
    Dimension windowDims=new Dimension(700,700);
    boolean isPaused;
    Counter timer;
    Lives lives;
    Points points;
    LevelLabel lvlNum;
    GameControler gameControl;
    JButton tryAgain,pause,resume;
    JMenuBar menuBar;
    JPanel gameLayout=new JPanel();
    ListenForMove lForMove;
    ListenForKeys lForActions;
    int startLevel,currentLevel;
    
    /**
     * Constructor only calls other class functions 
     */
    public Game(){
        isPaused=false;
        createWindow();
        createComponents();
        layoutCreator();
        makeVisible();
        
    }
    /**
     * Creates game window
     */
    private void createWindow(){
        this.setSize(windowDims);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sokoban");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
    }
    /**
     * Creates components of game window
     */
    private void createComponents(){  
        tryAgain=new JButton("Try\n Again");
        pause=new JButton("Pause");
        resume=new JButton("Resume");
        gameControl=new GameControler();
        currentLevel=gameControl.startLevelNumber;
        timer=new Counter(gameControl.levelMap.gameOptions,gameControl.levelMap.levelProperties);
        lives=new Lives(gameControl.levelMap.gameOptions);
        points=new Points(gameControl.levelMap.gameOptions);
        lvlNum=new LevelLabel(gameControl.levelMap.levelProperties);
        menuCreator();
        ListenForButtons lForButtons=new ListenForButtons(this);
        
        pause.addActionListener(lForButtons);
        tryAgain.addActionListener(lForButtons);
        resume.addActionListener(lForButtons);
        lForMove=new ListenForMove(gameControl.levelMap,this);
        lForActions=new ListenForKeys(this);
        pause.addKeyListener(lForMove);
        tryAgain.addKeyListener(lForMove);
        resume.addKeyListener(lForMove);
        gameControl.levelMap.panel.addKeyListener(lForMove);
        pause.addKeyListener(lForActions);
        tryAgain.addKeyListener(lForActions);
        resume.addKeyListener(lForActions);
        gameControl.levelMap.panel.addKeyListener(lForActions);
        
    }
    
    /**
     * Creates components of game window, of the level i
     * @param i number of level.
     */
    private void createComponents(int i){  
        gameLayout.removeAll();
        gameControl=new GameControler(i);
        timer=new Counter(gameControl.levelMap.gameOptions,gameControl.levelMap.levelProperties);
        lives=new Lives(gameControl.levelMap.gameOptions);
        lvlNum=new LevelLabel(gameControl.levelMap.levelProperties);
        lForMove.setNewMap(gameControl.levelMap);
    }
    
    /**
     * Creates layout of window
     */
    private void layoutCreator(){
        JPanel upper=new JPanel();
        JPanel lower=new JPanel();
        JPanel center=new JPanel();
        JPanel buttons = new JPanel();
        gameLayout.setLayout(new BorderLayout());
        upper.setLayout(new BorderLayout());
        lower.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        buttons.setLayout(new BorderLayout());
        buttons.add(pause, BorderLayout.NORTH);
        buttons.add(tryAgain);
        buttons.add(resume, BorderLayout.SOUTH);
        
        upper.add(lvlNum.panel, BorderLayout.WEST);
        upper.add(points.panel, BorderLayout.EAST);
        
        lower.add(lives.panel, BorderLayout.WEST);
        lower.add(timer.panel, BorderLayout.EAST);
        
        center.add(gameControl.levelMap.panel, BorderLayout.CENTER);
        center.add(buttons,BorderLayout.EAST);
        
        gameLayout.add(upper, BorderLayout.NORTH);
        gameLayout.add(lower, BorderLayout.SOUTH);
        gameLayout.add(center, BorderLayout.CENTER);
        this.setJMenuBar(menuBar);
        this.add(gameLayout);

        points.grabFocus();
        lvlNum.grabFocus();
        gameControl.levelMap.panel.grabFocus();
        pause.grabFocus();
        tryAgain.grabFocus();
        this.pack();
        ListenForWindow lForWindow= new ListenForWindow(this);
        this.addComponentListener(lForWindow);
    }
    
    /**
     * Creates menu bar in the game
     */
    private void menuCreator(){
        menuBar=new JMenuBar();
        JMenu menu=new JMenu("Menu");
        JMenu help=new JMenu("Help");
        menuBar.add(menu);
        menuBar.add(help);
                
                
        JMenuItem rst= new JMenuItem("Reset");
        JMenuItem back=new JMenuItem("Return to main menu");
        JMenuItem manual=new JMenuItem("How to Play?");
        JMenuItem rules=new JMenuItem("Rules");
        
        /**
         * Listiner for menu actions
         */
        class ListenForMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource().equals(rst)){
                closeGame();
                new Game();
            }
            else if(ae.getSource().equals(back)){
                closeGame();
                new MainMenu();
            }
            else if(ae.getSource().equals(rules)){
                new Rules();
            }
            else if(ae.getSource().equals(manual)){
                new Manual();
            }
           }
         }
        
        menu.add(rst);
        menu.add(back);
        help.add(manual);
        help.add(rules);
        ListenForMenu listener = new ListenForMenu();
        rst.addActionListener(listener);
        back.addActionListener(listener);
        rules.addActionListener(listener);
        manual.addActionListener(listener);
    }
    
    /**
     * Simple function to make window visible, and keep constructor clean.
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
     * Closes game window when "Return to main menu" option were chosen.
     */
    public void closeGame(){
        this.dispose();
    }
    
    public void tryToResize(){
            Dimension dim=this.getSize();
            float height=dim.height-121;
            float width = dim.width-103;
            height=height/this.gameControl.levelMap.mapHeight;
            width=width/this.gameControl.levelMap.mapWidth;
            int newElementHeight=Math.round(height);
            int newElementWidth=Math.round(width);
            this.gameControl.levelMap.bg.resizeBackground(newElementHeight,newElementWidth);
            this.gameControl.levelMap.player.resizeElement(newElementHeight, newElementWidth);
            Chest tempChest;
            for(int i=0;i<this.gameControl.levelMap.numOfChests;i++){
                tempChest=this.gameControl.levelMap.chestList.get(i);
                tempChest.resizeElement(newElementHeight,newElementWidth);
            }
            Wall tempWall;
            for(int i=0;i<this.gameControl.levelMap.numOfWalls;i++){
                tempWall=this.gameControl.levelMap.wallList.get(i);
                tempWall.resizeElement(newElementHeight,newElementWidth);
            }
            Endpoint tempEnd;
            for(int i=0;i<this.gameControl.levelMap.numOfChests;i++){
                tempEnd=this.gameControl.levelMap.endpointList.get(i);
                tempEnd.resizeElement(newElementHeight,newElementWidth);
            }
        }
  
    
    
    
    /**
     * Action listener for the buttons of the game screen.
     */
    private class ListenForButtons implements ActionListener{
        private Game game;
        public ListenForButtons(Game gm){
            game=gm;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==pause){ 
                   game.isPaused=true;
                   game.timer.stop();
                   game.gameControl.levelMap.pause();
            }
 
            else if(ae.getSource()==tryAgain){
                game.gameControl.levelMap.restartMap();
                game.lives.liveLoss();
                if(game.lives.isGameOver){
                    gameLayout.removeAll();
                    gameControl.gameFinished(points,game);
                    gameLayout.add(gameControl.end);
                    game.setLocationRelativeTo(null);
                }
                game.lives.panel.repaint();

            }
            else if(ae.getSource()==resume){
                game.isPaused=false;
                game.timer.continueCounting();
                game.gameControl.levelMap.continuePlaying();
                game.gameControl.levelMap.panel.repaint();
            }
        }
        
       
    }
    
    /**
     * Key listener for the moves in the game.
     */
    private class ListenForMove implements KeyListener{
        
        LevelMap map;
        Game game;
                
         public ListenForMove(LevelMap m,Game g){
             map=m;
             game=g;
         }   
        @Override
        public void keyTyped(KeyEvent ke) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent ke) {
            if(!game.isPaused){
            if(game.pullOn){
                if(ke.getKeyCode()==KeyEvent.VK_DOWN){
                    map.moveAvatarWithPull("down");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                         endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_UP){
                    map.moveAvatarWithPull("up");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                        endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_LEFT){
                    map.moveAvatarWithPull("left");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                         endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
                    map.moveAvatarWithPull("right");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                        endLevel();
                    }
                }
                else{
                }
            }
            else{
                if(ke.getKeyCode()==KeyEvent.VK_DOWN){
                    map.moveAvatar("down");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                         endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_UP){
                    map.moveAvatar("up");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                        endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_LEFT){
                    map.moveAvatar("left");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                         endLevel();
                    }
                }
                else if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
                    map.moveAvatar("right");
                    map.checkLevelFinished();
                    if(map.levelFinished){
                        endLevel();
                    }
                }
                else{
                }
            }
        }
        }
        
        @Override
        public void keyReleased(KeyEvent ke) {
        }
        
        public void setNewMap(LevelMap m){
            map=m;
        }
        private void endLevel(){
            gameControl.levelFinished(points,lives,timer,lvlNum);
                    if(gameControl.nextLevel!=0){
                        currentLevel++;
                        createComponents(currentLevel);
                        layoutCreator();
                        }
                    else{
                        endGame();
                    }
        }
        
        private void endGame(){
            gameLayout.removeAll();
            gameControl.gameFinished(points,game);
            gameLayout.add(gameControl.end);
            game.setLocationRelativeTo(null);
        }
        
    }
    
    /**
     * Key listiner implementing addicional functionality
     */
    private class ListenForKeys implements KeyListener{
        Game game;
                
         public ListenForKeys(Game g){
             game=g;
         }  
        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            game.pullOn=true;
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            game.pullOn=false;
        } 
    }
    
    private class ListenForWindow implements ComponentListener{
        Game game;
        
        public ListenForWindow(Game g){
            game = g;
        }
        @Override
        public void componentResized(ComponentEvent ce) {
            game.tryToResize();
        }

        @Override
        public void componentMoved(ComponentEvent ce) {
        }

        @Override
        public void componentShown(ComponentEvent ce) {
        }

        @Override
        public void componentHidden(ComponentEvent ce) {
        }
        
    }
}
