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
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael
 */
public class RemovePoseHandler implements ActionListener {

    /**
     * when a pose is selected, it will be deleted
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Poseur p = Poseur.getPoseur();
        PoseurGUI gui = p.getGUI();
        if ( gui.getSelectedAnimationState() == null || p.getGUI().getSelectedPoseIndex() < 0) return; 
        DefaultListModel lm = p.getGUI().listModel;
        int index = gui.getSelectedPoseIndex();
        lm.remove(index);
        if(!lm.isEmpty())gui.scrollPaneList.setSelectedIndex(0);
        p.getAnimatedSPrite().removePose(gui.getSelectedAnimationState(), index);
    }
    
}
