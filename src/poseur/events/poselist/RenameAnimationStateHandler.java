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
public class RenameAnimationStateHandler implements ActionListener {
    
    /**
     * called when the user
     * renames an animation state
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Poseur p = Poseur.getPoseur();
        AnimationState as = p.getGUI().getSelectedAnimationState();
        if (as==null) return;
        String newName = JOptionPane.showInputDialog("Enter a new state name");
        if (newName==null) return;
        
        AnimationState newAS;
        try{
            newAS = AnimationState.valueOf(newName.toUpperCase());
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "That was not a valid Animation State.", "Error", 0, null);
            return;
        }
        
        if(p.getAnimatedSprite().containsState(newAS)){
            JOptionPane.showMessageDialog(null, "You can't have two states with the same name.", "Error", 0, null);
            return;
        }
        p.getAnimatedSprite().renameAnimationState(as, newAS);
        p.getGUI().animationStatesModel.removeElement(as);
        p.getGUI().animationStatesModel.addElement(newAS);
    }
    
}
