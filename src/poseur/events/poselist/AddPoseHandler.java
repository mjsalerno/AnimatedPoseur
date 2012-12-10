/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.sprite.AnimatedSprite;
import poseur.state.PoseurPose;
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
        if ( p.getGUI().getSelectedAnimationState() == null ) return; 
        AnimatedSprite sprite = p.getAnimatedSprite();
        PoseurPose pose = new PoseurPose(sprite.getWidth(), sprite.getHeight());
        AnimationState selectedAnimation = (AnimationState)p.getGUI().animationStatesModel.getSelectedItem();
        p.getAnimatedSprite().addPose(selectedAnimation, pose);
        p.getGUI().listModel.addElement(pose.getIcon());
        p.getStateManager().markPoseChanged();
        p.getGUI().updateMode();
    }
    
}
