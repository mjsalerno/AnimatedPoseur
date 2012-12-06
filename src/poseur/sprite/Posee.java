/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import poseur.shapes.PoseurShape;

/**
 *
 * @author Michael Salerno
 */
public class Posee implements Serializable{    
    //TODO: start pose
    
    private float pause;
    private ArrayList<PoseurShape> shapeList;
    private ImageIcon icon;
    
    public Posee(float pause, ArrayList<PoseurShape> shapeList){
        this.pause = pause;
        this.shapeList = shapeList;

        Image img = null;

        try {
            img = ImageIO.read(new File("testt.png"));
        } catch (IOException e) {
        }
        ImageIcon ii = new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        
        this.icon = ii;

    }
    
    public Posee(){
        this(0.5F, new ArrayList<PoseurShape>());
    }

    public float getPause() {
        return this.pause;
    }

    public void setPause(float newPause) {
        this.pause = newPause;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ArrayList<PoseurShape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ArrayList<PoseurShape> shapeList) {
        this.shapeList = shapeList;
    }
    
    
    
    
}
