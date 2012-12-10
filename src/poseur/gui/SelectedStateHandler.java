/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import poseur.Poseur;
import poseur.state.PoseurPose;
import poseur.state.PoseurState;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael Salerno
 */
class SelectedStateHandler implements ItemListener {


    @Override
    public void itemStateChanged(ItemEvent ie) {
        Poseur p = Poseur.getPoseur();
        p.getGUI().listModel.clear();
        JComboBox box = (JComboBox) ie.getSource();
        AnimationState state = (AnimationState) box.getSelectedItem();
        
        p.getStateManager().getPose().reset();
        p.getStateManager().setState(PoseurState.SELECT_SHAPE_STATE);
        p.getStateManager().unselectSelectedShape();
        
        if (state == null) return;
        ArrayList<PoseurPose> poseList = p.getAnimatedSprite().getPoseList(state);
        if (poseList == null || poseList.isEmpty()) return;
        for(PoseurPose pose : poseList){
            p.getGUI().listModel.addElement(pose.getIcon());
        }
        p.getGUI().updateMode();
        
        p.getGUI().sceneRenderingPanel.pauseScene();
        p.getGUI().spriteList.clear();
    }
    
}
