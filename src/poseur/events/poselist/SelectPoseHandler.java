/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author roofis0
 */
public class SelectPoseHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getValueIsAdjusting()){
            System.out.println("---PRESSED COPY POSE BUTTON");
        }
    }

    
}
