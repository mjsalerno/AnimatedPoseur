package poseur.events.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.files.PoseurFileManager;
import sprite_renderer.AnimationState;

/**
 * This handler responds to when the user wants to open an existing Pose
 * file. It will have to make sure any file being edited is not
 * accidentally lost.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 * @version 1.0
 */
public class OpenPoseHandler implements ActionListener
{
    /**
     * Called when the user requests to exit via the
     * exit button, this will make sure no work is lost
     * and then close the application.
     * 
     * @param ae The Event Object.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // FORWARD THE REQUEST TO THE FILE MANAGER
        Poseur singleton = Poseur.getPoseur();
        PoseurFileManager poseurFileManager = singleton.getFileManager();
        poseurFileManager.requestOpenPose();
        
        singleton.getGUI().animationStatesModel.removeAllElements();
        for(AnimationState state : singleton.getAnimatedSprite().getAnimationStates()){
            singleton.getGUI().animationStatesModel.addElement(state);
        }
        
//        singleton.getGUI().
//        for(AnimationState state : singleton.getAnimatedSprite().getAnimationStates()){
//            singleton.getGUI().animationStatesModel.addElement(state);
//        }
    }
}