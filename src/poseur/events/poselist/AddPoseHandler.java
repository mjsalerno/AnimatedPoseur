/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.sprite.Pose;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael
 */
public class AddPoseHandler implements ActionListener {

    /**
     * when a pose is being drawn, it will
     * be added to the pose list
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Poseur p = Poseur.getPoseur();
        //TODO:add to selected state
        Pose pose = new Pose();
        p.getAnimatedSPrite().addPose(AnimationState.IDLE, pose);
        p.getGUI().listModel.addElement(pose.getIcon());
    }
    
}
