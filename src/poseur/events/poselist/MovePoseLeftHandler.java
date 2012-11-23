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
public class MovePoseLeftHandler implements ActionListener {

    /**
     * when a pose is selected,it will be 
     * moved to the left in the pose list
     * @param e the event being thrown
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---CLICKED MOVE POSE LEFT BUTTON");
    }
    
}
