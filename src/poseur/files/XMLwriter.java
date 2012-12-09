/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import poseur.sprite.AnimatedSprite;
import poseur.state.PoseurPose;
import sprite_renderer.AnimationState;

/**
 *
 * @author roofis0
 */
public class XMLwriter {
    
    private AnimatedSprite as;
    private File file;
    private FileWriter fw;
    private File dir;
    
    public XMLwriter(AnimatedSprite as, File file, File dir) throws IOException{
        this.as = as;
        this.file = file;
        fw = new FileWriter(this.file);
        this.dir = dir;
    }
    
    
    public void writeFile() throws IOException{
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
        + "<sprite_type>\n"
        + "<width>"+this.as.getWidth()+"</width>\n"
        + "<height>"+this.as.getHeight()+"</height>\n"
        + "<images_list>\n");
        
        int id = 1;  
        int index = 0;
        for(AnimationState state : this.as.getAnimationStates()){            
            index = 0;
            for(PoseurPose pose : this.as.getPoseList(state)){   
                addImage(id++, index++, state);
            }
        }
        fw.write("</images_list>\n" +
        " <animations_list>\n");
        
        id = 1;
        for(AnimationState state : this.as.getAnimationStates()){            
            fw.write("<animation_state>\n");
            fw.write("<state>"+state.toString()+"</state>\n");
            fw.write("<animation_sequence>\n");
            
            for(PoseurPose pose : this.as.getPoseList(state)){
                fw.write("<pose image_id=\""+ id++ +"\" duration=\""+ pose.getPause() +"\"/>\n");
            }
            fw.write("</animation_sequence>\n" +
            "  </animation_state>\n");
            
        }
        
        fw.write("</animations_list>\n"
                + "</sprite_type>");
        fw.close();
        
    }
    
    private void addImage(int id, int index, AnimationState state) throws IOException{
        String fileName = this.as.getName() + "_" + state + "_" + id + ".png";
        File f = new File(dir, fileName);
        f.createNewFile();
        
        ImageIO.write(this.as.getPose(state, index).getImage(),"png",f);
        fw.write("<image_file id=\"" + id + "\" file_name=\"" + fileName + "\"/>\n");
    }
}
