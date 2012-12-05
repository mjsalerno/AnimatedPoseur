/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;

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
        Poseur p = Poseur.getPoseur();
        p.getAnimatedSPrite().removeAnimationState(p.getGUI().getSelectedAnimationState());
        p.getGUI().animationStatesModel.removeElement(p.getGUI().getSelectedAnimationState());
    }
    
}
