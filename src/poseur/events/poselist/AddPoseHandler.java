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
public class AddPoseHandler implements ActionListener {

    public AddPoseHandler() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---CLICKED ADD POSE");;
    }
    
}
