/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.gui.PoseurGUI;

/**
 *
 * @author Michael Salerno
 */
public class StartHandler implements ActionListener {

    /**
     * when an pose is loaded, this will start the animation
     * @param ae the even being thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED START BUTTON");     
        PoseurGUI gui = Poseur.getPoseur().getGUI();
        gui.spriteList.clear();
        gui.sceneRenderingPanel.startScene();
        gui.spriteList.add(Poseur.getPoseur().getAnimatedSprite().convertToSprite(gui.getSelectedAnimationState()));
        
        gui.sceneRenderingPanel.unpauseScene();
    }
    
}
