/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import poseur.Poseur;
import poseur.gui.PoseurGUI;
import poseur.state.PoseurPose;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael
 */
public class SetPosePauseHandler implements ActionListener {

    /**
     * when a pose is selected, this will ask the user 
     * how long the pose should pause for.
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Poseur p = Poseur.getPoseur();
        PoseurGUI gui = p.getGUI();        
        AnimationState state = p.getGUI().getSelectedAnimationState();
        
        int index = gui.getSelectedPoseIndex();
        PoseurPose pose = p.getAnimatedSprite().getPose(state, index);
        String newPause = JOptionPane.showInputDialog("Enter a new pause value\npause: " + pose.getPause());
        if(newPause == null) return;
        if(newPause.contains("-")){
            JOptionPane.showMessageDialog(null,"Sorry but \"" + newPause + "\" is not a valid number");
            return;
        }
        try{
            pose.setPause(Integer.parseInt(newPause));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Sorry but \"" + newPause + "\" is not a number");
        }
    }
    
}
