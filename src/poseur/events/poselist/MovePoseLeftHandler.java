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
        //FIXME: still needs to move them to the actualobject
        
        if(gui.getSelectedPoseIndex() > 0){
            int first = gui.getSelectedPoseIndex() -1;
             //TODO:move on selected state
            AnimatedSprite as = Poseur.getPoseur().getAnimatedSPrite();
            as.movePoseLeft(AnimationState.IDLE, first);
            int second =  gui.getSelectedPoseIndex();


            ImageIcon i1 = lm.get(first);
            ImageIcon i2 = lm.get(second);

            lm.set(first, i2);
            lm.set(second, i1);

            gui.setSelectedPoseIndex(first);       
        }
    }
    
}
