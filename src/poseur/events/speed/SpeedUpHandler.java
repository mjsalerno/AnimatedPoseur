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
public class SpeedUpHandler implements ActionListener {

    public SpeedUpHandler() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("---PRESSED THE SPEED UP BUTTON");
    }
    
}
