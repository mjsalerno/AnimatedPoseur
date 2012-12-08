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
public class SlowDownHandler implements ActionListener {


    /**
     * When a pose is being played, this will slow it down.
     * @param ae an event that will be thrown to this method 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {        
        SceneRenderer renderer = Poseur.getPoseur().getGUI().sceneRenderingPanel;
        renderer.setTimeScaler(renderer.getTimeScaler() + 0.1f);
    }
    
}
