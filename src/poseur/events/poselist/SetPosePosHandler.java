/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import poseur.Poseur;
import poseur.gui.PoseurGUI;
import poseur.sprite.AnimatedSprite;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael
 */
public class SetPosePosHandler implements ActionListener {
    
    DefaultListModel<ImageIcon> lm;
    PoseurGUI gui;
    
    public SetPosePosHandler(PoseurGUI gui, DefaultListModel<ImageIcon> lm){
        this.gui = gui;
        this.lm = lm;
    }

    /**
     * when a pose is selected in the list, this will ask the user
     * where they would like to put it.
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {        
        
        if(gui.getSelectedPoseIndex() >= 0){
            int first = gui.getSelectedPoseIndex();
            String input = JOptionPane.showInputDialog ( "Enter a New Position, \nYou Are At Position " + gui.getSelectedPoseIndex() );
            if (input == null) return;
            int second =  Integer.parseInt(input);
            if (second < 0 || second > lm.getSize()-1){
                JOptionPane.showMessageDialog(null, "Position must be [0, "+ (lm.getSize()-1) + "] : " + second, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Poseur p = Poseur.getPoseur();
            AnimatedSprite as = p.getAnimatedSprite();
            AnimationState selectedAnimation = (AnimationState)p.getGUI().animationStatesModel.getSelectedItem();
            as.swapPose(selectedAnimation, first, second);

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
