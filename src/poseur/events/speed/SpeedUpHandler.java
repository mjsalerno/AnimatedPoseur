/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael Salerno
 */
public class SpeedUpHandler implements ActionListener {

    /**
     * When a pose is being played, this will speed up the animation
     * @param ae the even being thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED THE SPEED UP BUTTON");
    }
    
}
