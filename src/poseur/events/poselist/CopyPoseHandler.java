/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.poselist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author roofis0
 */
public class CopyPoseHandler implements ActionListener{

   
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED COPY POSE");
    }
    
}
