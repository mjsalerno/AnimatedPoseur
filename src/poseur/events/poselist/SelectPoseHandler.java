/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import poseur.Poseur;
import poseur.state.PoseurPose;
import poseur.state.PoseurState;
import sprite_renderer.AnimationState;

/**
 *
 * @author roofis0
 */
public class SelectPoseHandler implements ListSelectionListener {
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getValueIsAdjusting()){
            Poseur p = Poseur.getPoseur();
            JList list = (JList) lse.getSource();
            int index = list.getSelectedIndex();
            if (index < 0) return;
            p.getStateManager().getPose().updateIcon();
            AnimationState state = p.getGUI().getSelectedAnimationState();
            PoseurPose pose = p.getAnimatedSprite().getPose(state, index);
            
            p.getStateManager().getPose().loadPoseData(pose);
            p.getStateManager().setState(PoseurState.SELECT_SHAPE_STATE);
            p.getStateManager().unselectSelectedShape();
            
            p.getAnimatedSprite().getPose(state, index).updateIcon();
            p.getGUI().listModel.setElementAt(p.getAnimatedSprite().getPose(state, index).getIcon(), index);
            
            
            
            
        }
    }

    
}
