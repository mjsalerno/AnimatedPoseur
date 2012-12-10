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
        if (newName==null) return;
        AnimationState newState;
        
        try{
            newState = AnimationState.valueOf(newName.toUpperCase());
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Not a valid animation state.", "Error", 0, null);
            return;
        }
        
        if(p.getAnimatedSprite().containsState(newState)){
            JOptionPane.showMessageDialog(null, "You can't have two states with the same name.", "Error", 0, null);
            return;
        }
        
        p.getAnimatedSprite().copyState(oldState, newState);
        p.getGUI().animationStatesModel.addElement(newState);
    }
    
}
