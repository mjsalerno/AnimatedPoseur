/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.zoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.gui.PoseurGUI;
import poseur.state.PoseCanvasState;
import poseur.state.PoseurStateManager;

/**
 *
 * @author Michael Salerno
 */
public class ZoomOutHandler implements ActionListener {


    /**
     * This method responds by updating the zoom level 
     * accordingly and repainting the view.
     * 
     * @param ae The Event Object.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // RELAY THE REQUEST TO THE STATE MANAGER
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager poseurStateManager = singleton.getStateManager();
        PoseCanvasState poseCanvasState = poseurStateManager.getZoomableCanvasState();
        poseCanvasState.zoomOut();
        
        // AND MAKE SURE THE ZOOM LABEL REFLECTS THE CHANGE
        PoseurGUI gui = singleton.getGUI();
        gui.updateZoomLabel();
    }
    
}
