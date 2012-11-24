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
public class RemoveAnimationStateHandler implements ActionListener {

    /**
     * called when the user removes an animation state
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---CLICKED REMOVE ANIMATION STATE");
    }
    
}
