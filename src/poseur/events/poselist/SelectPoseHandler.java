/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import poseur.Poseur;
import poseur.shapes.PoseurRectangle;
import poseur.shapes.PoseurShape;
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
            PoseurPose pose = p.getAnimatedSPrite().getPose(state, index);
            
            p.getStateManager().getPose().loadPoseData(pose);
            p.getStateManager().setState(PoseurState.SELECT_SHAPE_STATE);
//            p.getStateManager().unselectSelectedShape();
            
            p.getAnimatedSPrite().getPose(state, index).updateIcon();
            p.getGUI().listModel.setElementAt(p.getAnimatedSPrite().getPose(state, index).getIcon(), index);
            
            
            
            
        }
    }

    
}
