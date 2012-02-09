package week4.bounce;

import java.util.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 * P2 prac wk4.
 * BallPanel a special JPanel for drawing balls on.
 * Used with TimedBouncer.
 * @author  Rieks op den Akker, Arend Rensink en Theo Ruys
 * @version 2005.02.22
 */
public class BallPanel extends JPanel {
    private List<Ball>  	balls;    // @invariant balls != null
    private javax.swing.Timer   timer;    // de timer die tikt

    public BallPanel(){
        balls = new java.util.ArrayList<Ball>();
    }

    public void animate() {
        try {
            while(true) {
                Thread.sleep(5);
                moveBalls();
                repaint();
            }
        } catch (InterruptedException exc) {}
    }

    /** Add a new ball to the ball list and start the timer if not yet running */
    public synchronized void addNewBall(){
        balls.add(new Ball(this));
    }

    /**
     * Move all balls 
     * BEWARE: collision effects are not respecting Snellius' law. 
     */
    public synchronized void moveBalls(){
        for (Ball b: balls) 
            b.move();
            
        // collision detection
        ListIterator<Ball> ix = balls.listIterator();
        while (ix.hasNext()) {
            Ball b = ix.next();
            ListIterator<Ball> jx = balls.listIterator(ix.nextIndex());
            while (jx.hasNext()) {
                Ball other = jx.next();
                b.collide(other);
            }
        }
    }
    
    /**
     * Overrides paintComponent in JPanel.
     * Is called if repaint is called.
     * Paints all elements of balls.
     */
    public synchronized void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Ball b: balls) 
            b.draw(g);
    }
}
