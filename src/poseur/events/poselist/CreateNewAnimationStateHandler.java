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
        Poseur p = Poseur.getPoseur();
        String input = JOptionPane.showInputDialog("Enter a state");
        if(input==null) return;
        
        AnimationState state;
        
        try{
            state = AnimationState.valueOf(input.toUpperCase());
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "Not a valid animation state.", "Error", 0, null);
            return;
        }
        
        if(p.getAnimatedSprite().containsState(state)){
            JOptionPane.showMessageDialog(null, "You can't have two states with the same name.", "Error", 0, null);
            return;
        }
        p.getGUI().animationStatesModel.addElement(state);
        p.getAnimatedSprite().addAnimationState(state);
        p.getGUI().enableAddPoseControls(true);
    }
    
}

