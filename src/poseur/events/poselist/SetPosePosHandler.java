/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class SetPosePosHandler implements ActionListener {

    /**
     * when a pose is selected in the list, this will ask the user
     * where they would like to put it.
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---PRESSED SET POS BUTTON");
    }
    
}
