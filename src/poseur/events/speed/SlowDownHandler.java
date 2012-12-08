/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.speed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael Salerno
 */
public class SlowDownHandler implements ActionListener {


    /**
     * When a pose is being played, this will slow it down.
     * @param ae an event that will be thrown to this method 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {        
        System.out.println("---PRESSED SLOW DOWN BUTTON");
    }
    
}
