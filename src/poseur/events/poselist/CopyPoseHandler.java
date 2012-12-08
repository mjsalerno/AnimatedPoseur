/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import poseur.Poseur;
import poseur.gui.PoseurGUI;
import poseur.state.PoseurPose;
import sprite_renderer.AnimationState;

/**
 *
 * @author roofis0
 */
public class CopyPoseHandler implements ActionListener{

   
    @Override
    public void actionPerformed(ActionEvent ae) {
        Poseur p = Poseur.getPoseur();
        PoseurGUI gui = p.getGUI();
        if ( gui.getSelectedAnimationState() == null || p.getGUI().getSelectedPoseIndex() < 0) return; 
        DefaultListModel lm = p.getGUI().listModel;       
        int index = gui.getSelectedPoseIndex();
        AnimationState state = gui.getSelectedAnimationState();
        
        PoseurPose pose = (PoseurPose) p.getAnimatedSprite().getPose(state, index).clone();
        lm.add(index+1, pose.getIcon());
        p.getAnimatedSprite().addPoseAt(gui.getSelectedAnimationState(), pose, index+1);
    }
    
}
