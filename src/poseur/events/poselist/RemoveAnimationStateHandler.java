/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import sprite_renderer.AnimationState;

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
        AnimationState state = p.getGUI().getSelectedAnimationState();
        if(state == null)return;
        p.getAnimatedSPrite().removeAnimationState(state);
        p.getGUI().animationStatesModel.removeElement(state);
    }
    
}
