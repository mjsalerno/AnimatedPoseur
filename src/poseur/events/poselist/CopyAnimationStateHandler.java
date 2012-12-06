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
 * @author Michael Salerno
 */
public class CopyAnimationStateHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        Poseur p = Poseur.getPoseur();
        AnimationState oldState = p.getGUI().getSelectedAnimationState();
        if (oldState==null) return;
        String newName = JOptionPane.showInputDialog("Enter a new state name for the copy");
        AnimationState newState = AnimationState.valueOf(newName.toUpperCase());
        p.getAnimatedSPrite().copyState(oldState, newState);
        p.getGUI().animationStatesModel.addElement(newState);
    }
    
}
