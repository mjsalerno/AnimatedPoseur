/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.edit;

import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.shapes.PoseurShape;

/**
 *
 * @author Michael Salerno
 */
public class LineStrokeHandler implements ActionListener {
    
    /**
     * called when the user tries to change the thickness of a shape
     * @param ae The event object for this button press.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Poseur singleton = Poseur.getPoseur();
        
        //only try to change the thickness if there is a shape selected
        if (singleton.getStateManager().isShapeSelected()) {
            
            PoseurShape selectedShape = singleton.getStateManager().getSelectedShape();
            BasicStroke newStroke = new BasicStroke(singleton.getGUI().getLineThickness());

            selectedShape.setOutlineThickness(newStroke);
            singleton.getStateManager().refreshState();
        }
        
    }
}
