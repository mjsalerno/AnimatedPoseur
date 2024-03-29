package poseur.events.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.files.PoseurFileManager;
import poseur.gui.PoseurGUI;

/**
 * This handler responds to when the user wants to make a new Pose
 * file. It will have to make sure any file being edited is not
 * accidentally lost.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 * @version 1.0
 */
public class NewPoseHandler implements ActionListener
{
    /**
     * Called when the user requests to make a new pose.
     * 
     * @param ae The Event Object.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // FORWARD THE REQUEST TO THE FILE MANAGER
        Poseur singleton = Poseur.getPoseur();
        PoseurGUI gui = singleton.getGUI();
        PoseurFileManager poseurFileManager = singleton.getFileManager();
        poseurFileManager.requestNewPose();
        gui.animationStatesModel.removeAllElements();
        gui.listModel.removeAllElements();
        gui.spriteList.clear();
        gui.sceneRenderingPanel.unpauseScene();
        gui.sceneRenderingPanel.pauseScene();
        gui.enableStateControls(true);
        
    }
}