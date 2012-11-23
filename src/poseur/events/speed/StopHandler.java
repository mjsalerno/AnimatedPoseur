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
public class StopHandler implements ActionListener {

    /**
     * when an animation is playing, this will stop it
     * @param ae the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED STOP BUTTON");
    }
    
}
