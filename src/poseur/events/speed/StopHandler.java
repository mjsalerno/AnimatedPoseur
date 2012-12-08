/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import sprite_renderer.SceneRenderer;

/**
 *
 * @author Michael Salerno
 */
public class StopHandler implements ActionListener {

    /**
     * when an animation is playing, this will stop it
     * @param ae the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        SceneRenderer renderer = Poseur.getPoseur().getGUI().sceneRenderingPanel;
        renderer.pauseScene();
    }
    
}
