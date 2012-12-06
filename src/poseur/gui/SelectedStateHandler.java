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
        if (state == null) return;
        ArrayList<PoseurPose> poseList = p.getAnimatedSPrite().getPoseList(state);
        if (poseList == null || poseList.isEmpty()) return;
        for(PoseurPose pose : poseList){
            p.getGUI().listModel.addElement(pose.getIcon());
        }
    }
    
}
