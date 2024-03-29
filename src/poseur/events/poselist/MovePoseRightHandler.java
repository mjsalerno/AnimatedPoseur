/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import poseur.Poseur;
import poseur.gui.PoseurGUI;
import poseur.sprite.AnimatedSprite;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael
 */
public class MovePoseRightHandler implements ActionListener {

    DefaultListModel<ImageIcon> lm;
    PoseurGUI gui;
    
    public MovePoseRightHandler(PoseurGUI gui, DefaultListModel<ImageIcon> lm){
        this.gui = gui;
        this.lm = lm;
    }
    
    
    
    /**
     * when a pose is selected,it will be 
     * moved to the right in the pose list
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {        
        
        if(gui.getSelectedPoseIndex() >= 0 && gui.getSelectedPoseIndex() < lm.getSize()-1){
            int first = gui.getSelectedPoseIndex();
            Poseur p = Poseur.getPoseur();
            AnimatedSprite as = p.getAnimatedSprite();
            AnimationState selectedAnimation = (AnimationState)p.getGUI().animationStatesModel.getSelectedItem();
            as.movePoseRight(selectedAnimation, first);
            int second = first + 1;

            ImageIcon i1 = lm.get(first);
            ImageIcon i2 = lm.get(second);

            lm.set(first, i2);
            lm.set(second, i1);

            gui.setSelectedPoseIndex(second);   
            if(!p.getGUI().spriteList.isEmpty()){
                gui.updateSprite();
            }
        }
    }
    
}
