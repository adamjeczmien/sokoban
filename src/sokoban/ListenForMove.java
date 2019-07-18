package sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
* Key listener for the moves in the game.
* @author jeczm
*/
    public class ListenForMove implements KeyListener{
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
        
        /**
         * Method called when level has ended
         */
        private void endLevel(){
            game.gameControl.levelFinished(game.points,game.lives,game.timer,game.lvlNum);
            game.tryToIncreaseLives();
            
            if(game.gameControl.nextLevel!=0){
                game.currentLevel++;
                game.createComponents(game.currentLevel);
                game.layoutCreator();
                game.setSize(game.windowDims);
            }
            else{
                endGame();
            }
        }
        /**
         * method called when game is over
         */
        private void endGame(){
            game.gameLayout.removeAll();
            game.gameControl.gameFinished(game.points,game);
            game.gameLayout.add(game.gameControl.end);
            game.setLocationRelativeTo(null);
        }
        
    }
