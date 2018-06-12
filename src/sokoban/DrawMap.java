package sokoban;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Class used to draw game objects and map.
 * @author jeczm
 */
public class DrawMap extends JPanel{
        LevelMap map;
        
        /**
         * Creates map
         * @param lm reference to current level map.
         */
        public DrawMap(LevelMap lm){
            map=lm;
            this.setFocusable(true);
            this.setPreferredSize(new Dimension(map.mapWidth*map.elementHeight,map.mapHeight*map.elementHeight));
        }
        
        /**
        * Calls draw methods of components
        * @param g
        */
        @Override
        public void paintComponent(Graphics g){
            map.bg.draw(g);
            map.drawWalls(g);
            map.drawEndpoints(g);
            map.drawChests(g);
            map.drawAvatar(g);
            if(!map.isPaused)
                this.repaint();
        }
}