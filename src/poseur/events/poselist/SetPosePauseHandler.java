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
public class SetPosePauseHandler implements ActionListener {

    /**
     * when a pose is selected, this will ask the user 
     * how long the pose should pause for.
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---PRESSED SET PAUSE BUTTON");
    }
    
}
