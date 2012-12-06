/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import poseur.Poseur;
import sprite_renderer.AnimationState;

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
        //TODO: make add animation states actually work.
        Poseur p = Poseur.getPoseur();
        String input = JOptionPane.showInputDialog("Enter a state");
        if(input==null) return;
        AnimationState state = AnimationState.valueOf(input.toUpperCase());
        if(p.getAnimatedSPrite().containsState(state)){
            JOptionPane.showMessageDialog(null, "You can't have twos tates with the same name.", "Error", 0, null);
            return;
        }
        p.getGUI().animationStatesModel.addElement(state);
    }
    
}

