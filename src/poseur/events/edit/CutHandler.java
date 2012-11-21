/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import poseur.Poseur;
import poseur.shapes.PoseurShape;
import poseur.state.PoseurStateManager;

/**
 *
 * This event handler responds to when the user has selected an item
 * on the canvas and has asked to cut it, which should place
 * it on the clipboard.
 * 
 * @author Michael Salerno
 */
public class CutHandler implements ActionListener {

    /**
     * This method relays this event to the state manager, which
     * will update the clipboard accordingly.
     * 
     * @param ae The event object for this button press.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager poseurStateManager = singleton.getStateManager();
        poseurStateManager.copySelectedItem();
        PoseurShape selectedShape = singleton.getStateManager().getSelectedShape();
        LinkedList<PoseurShape> shapeList = singleton.getStateManager().getPose().getShapesList();
        int shapeIndex = shapeList.indexOf(selectedShape);
        shapeList.remove(shapeIndex);        
        singleton.getStateManager().refreshState();
    }
    
}
