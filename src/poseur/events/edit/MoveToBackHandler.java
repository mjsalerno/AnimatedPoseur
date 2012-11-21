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

/**
 *
 * @author Michael
 */
public class MoveToBackHandler implements ActionListener {

    /**
     * is called when the user 
     * wants to move a selected shape
     * to the back of all of the other shapes
     * @param e The event object for this button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Poseur singleton = Poseur.getPoseur();
        int shapeIndex;
        
        PoseurShape selectedShape = singleton.getStateManager().getSelectedShape();
        LinkedList<PoseurShape> shapeList = singleton.getStateManager().getPose().getShapesList();
        shapeIndex = shapeList.indexOf(selectedShape);
        //remove the shape from the list
        //and add it to the front so it gets drawn first
        //making the shape appear in the back of the rest of the shapes
        shapeList.remove(shapeIndex);
        shapeList.add(0, selectedShape);
        singleton.getStateManager().refreshState();
    }
    
}
