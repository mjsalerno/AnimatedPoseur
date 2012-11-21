/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.colors;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import poseur.Poseur;

/**
 *
 * @author Michael
 */
public class AlphaChangeListener implements ChangeListener {

    public AlphaChangeListener() {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Poseur singleton = Poseur.getPoseur();
        JSlider alphaSlider = (JSlider) e.getSource();

        if (singleton.getStateManager().isShapeSelected()) {
            singleton.getStateManager().getSelectedShape().setAlpha(alphaSlider.getValue());
            singleton.getStateManager().refreshState();
        }
    }
    
}
