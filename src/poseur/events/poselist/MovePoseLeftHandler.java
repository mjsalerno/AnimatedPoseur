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
public class MovePoseLeftHandler implements ActionListener {

    DefaultListModel<ImageIcon> lm;
    PoseurGUI gui;
    
    public MovePoseLeftHandler(PoseurGUI gui, DefaultListModel<ImageIcon> lm){
        this.gui = gui;
        this.lm = lm;
    }
    
    
    
    /**
     * when a pose is selected,it will be 
     * moved to the left in the pose list
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {        
        
        if(gui.getSelectedPoseIndex() > 0){
            int first = gui.getSelectedPoseIndex() -1;
            Poseur p = Poseur.getPoseur();
            AnimatedSprite as = p.getAnimatedSPrite();
            AnimationState selectedAnimation = (AnimationState)p.getGUI().animationStatesModel.getSelectedItem();
            int second =  gui.getSelectedPoseIndex();
            as.movePoseLeft(selectedAnimation, second);


            ImageIcon i1 = lm.get(first);
            ImageIcon i2 = lm.get(second);

            lm.set(first, i2);
            lm.set(second, i1);

            gui.setSelectedPoseIndex(first);       
        }
    }
    
}
