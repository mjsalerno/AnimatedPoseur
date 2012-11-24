/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class CreateNewAnimationStateHandler implements ActionListener {

    /**
     * called when the user creates
     * a new animation state
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---CLICKED CREATE NEW ANIMATION STATE");
    }
    
}

