/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author roofis0
 */
public class SlowDownHandler implements ActionListener {

    public SlowDownHandler() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED SLOW DOWN BUTTON");
    }
    
}
