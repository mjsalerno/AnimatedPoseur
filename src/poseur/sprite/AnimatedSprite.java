/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import poseur.Poseur;
import static poseur.PoseurSettings.*;
import poseur.gui.PoseurGUI;
import poseur.shapes.PoseurShape;
import poseur.state.PoseurPose;
import poseur.state.PoseurStateManager;
import sprite_renderer.AnimationState;
import sprite_renderer.PoseList;
import sprite_renderer.Sprite;
import sprite_renderer.SpriteType;

/**
 *
 * @author Michael Salerno
 */
public class AnimatedSprite implements Serializable{
    private int width;
    private int height;
    private String name;
    private EnumMap<AnimationState, ArrayList<PoseurPose>> animationStates;
    
    public AnimatedSprite(String name, int height, int width, EnumMap<AnimationState, ArrayList<PoseurPose>> animationStates){
        this.name = name;
        this.height = height;
        this.width = width;
        this.animationStates = animationStates;
    }
    
    public AnimatedSprite(String name){
        this(name, DEFAULT_POSE_HEIGHT,DEFAULT_POSE_WIDTH, new EnumMap<AnimationState, ArrayList<PoseurPose>>(AnimationState.class));
    }
    
    public AnimatedSprite(){
        this("AnimatedSprite", DEFAULT_POSE_HEIGHT,DEFAULT_POSE_WIDTH, new EnumMap<AnimationState, ArrayList<PoseurPose>>(AnimationState.class));
    }
    
    public Set<AnimationState> getAnimationStates(){
        return this.animationStates.keySet();
    }
    
    public ArrayList<PoseurPose> addAnimationState(AnimationState name, ArrayList<PoseurPose> poseList){
        if(this.animationStates.containsKey(name)) this.animationStates.remove(name);
        return this.animationStates.put(name, poseList);        
    }
    
    public void replaceShapesList(AnimationState state, int poseIndex, Collection c){
        this.animationStates.get(state).get(poseIndex).getShapesList().clear();
        this.animationStates.get(state).get(poseIndex).getShapesList().addAll(c);
    }
    
    public ArrayList<PoseurPose> addAnimationState(AnimationState name){
        if(name == null) throw new IllegalArgumentException("no such animation state null");
        return this.animationStates.put(name, new ArrayList<PoseurPose>());        
    }
    
    public boolean addPose(AnimationState name, PoseurPose pose){
        if(!this.animationStates.containsKey(name)){
            this.addAnimationState(name);
        }
        return this.animationStates.get(name).add(pose);
    }
    
    public void addPoseAt(AnimationState name, PoseurPose pose, int index){
        if(!this.animationStates.containsKey(name)){
            this.addAnimationState(name);
        }
        this.animationStates.get(name).add(index, pose);
    }
    
    public void copyState(AnimationState from, AnimationState to){
        ArrayList<PoseurPose> list = this.animationStates.get(from);
        if(list == null) {
            return;
        }
        ArrayList<PoseurPose> temp = new ArrayList<>();
        for(PoseurPose pose: list){
            temp.add((PoseurPose)pose.clone());
        }
        this.animationStates.put(to, temp);
    }
    
    public ImageIcon[] getImageIcons(AnimationState state){
        ArrayList<PoseurPose> poses = this.animationStates.get(state);
        ImageIcon[] iia = new ImageIcon[poses.size()];
        for(int i = 0; i < iia.length; i++){
            iia[i] = poses.get(i).getIcon();
        }
        return iia;
    }
    
    public ArrayList<PoseurPose> removeAnimationState(AnimationState name){
        return this.animationStates.remove(name);
    }
    
    public void removePose(AnimationState state, int index){
        if(!this.animationStates.containsKey(state) || this.animationStates.get(state) == null || this.animationStates.get(state).isEmpty()) return;
        this.animationStates.get(state).remove(index);
    }
    
    public boolean swapPose(AnimationState name, int from, int to){
        if(!this.animationStates.containsKey(name)){
            return false;
        }
        ArrayList<PoseurPose> poseList = this.animationStates.get(name);
        if(poseList.size() <= from || from < 0){
            return false;
        }
        if(poseList.size() <= to || to < 0){
            return false;
        }
        if(from == to){
            return false;
        }
        
        PoseurPose tmp = new PoseurPose();
        tmp.loadPoseData(this.getPose(name, from));
        this.getPose(name, from).loadPoseData(this.getPose(name, to));
        this.getPose(name, to).loadPoseData(tmp);
        return true;
    }
   
    public boolean containsState(AnimationState state){
        return this.animationStates.containsKey(state);
    }
    
    public boolean movePoseRight(AnimationState name, int index){
        return this.swapPose(name, index, index+1);
    }
    
    public boolean movePoseLeft(AnimationState name, int index){
        return this.swapPose(name, index, index-1);
    }
    
    public ArrayList<PoseurPose> getPoseList(AnimationState name){
        return this.animationStates.get(name);
    }
    
    public PoseurPose getPose(AnimationState name, int index){
        return this.getPoseList(name).get(index);
    }
    
    
    public float setPosePause(AnimationState name, int index, int newPause){
        float oldPause = -1;
        PoseurPose p = this.getPose(name, index);
        oldPause = p.getPause();
        p.setPause(newPause);
        return oldPause;
    }
    
    public boolean renameAnimationState(AnimationState oldS, AnimationState newS){
        if(!this.animationStates.containsKey(oldS)) return false;
        ArrayList<PoseurPose> postList = this.animationStates.get(oldS);
        this.animationStates.remove(oldS);
        this.animationStates.put(newS, postList);
        return true;
    }
    

    @Override
    public String toString() {
        return "AnimatedSprite{" + "width=" + width + ", height=" + height 
                + ", name=" + name + ", animationStates=" + animationStates + '}';
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.setDimentions(width, this.height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.setDimentions(this.width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Sprite convertToSprite(AnimationState state){
        SpriteType st = new SpriteType();
        int temp = 1;
        PoseList tempPoseList;
        
            tempPoseList = st.addPoseList(state);
            for(PoseurPose pose : this.getPoseList(state)){
                st.addImage(temp, pose.getImage());                
                tempPoseList.addPose(temp++, pose.getPause());
            }            
            
            st.setHeight(height);
            st.setHeight(width);
        
        
        Sprite s = new Sprite(st, state);
        PoseurGUI gui = Poseur.getPoseur().getGUI();
        s.setPositionX((gui.sceneRenderingPanel.getWidth()/2) - ((this.width)/2));
        s.setPositionY((gui.sceneRenderingPanel.getHeight()/2) - ((this.height)/2));
        return s;
    }
    
    
    public void saveToFile(File file) throws FileNotFoundException{
        
        for(AnimationState state : this.animationStates.keySet()){
            for(PoseurPose pose : this.getPoseList(state)){
                pose.saveShapeThicknesses();
            }
        }
        
        try {
            ObjectOutput os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(this);
        } catch (IOException ex) {
            Logger.getLogger(AnimatedSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fixShapeThickness(){
        for(AnimationState state : this.animationStates.keySet()){
            for(PoseurPose pose : this.getPoseList(state)){
                pose.loadShapeThicknesses();
            }
        }
    }
    
    public static AnimatedSprite loadFromFile(File file) throws FileNotFoundException{
        //throw new UnsupportedOperationException();
        try {
            ObjectInput is = new ObjectInputStream(new FileInputStream(file));
            AnimatedSprite as = (AnimatedSprite) is.readObject();
            as.fixShapeThickness();
            return as;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AnimatedSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void loadAnimatedSpriteData(AnimatedSprite that) {
        //throw new UnsupportedOperationException("Not yet implemented");
        this.animationStates = that.animationStates;
        this.height = that.height;
        this.name = that.name;
        this.width = that.width;
        Poseur.getPoseur().getStateManager().setPoseDimensions(width, height);
        
    }

    public void setDimentions(int poseWidth, int poseHeight) {
        this.width = poseWidth;
        this.height = poseHeight;
        
        for(AnimationState state : this.getAnimationStates()){
            for(PoseurPose pose : this.getPoseList(state)){
                pose.setPoseHeight(poseHeight);
                pose.setPoseWidth(poseWidth);
            }
        }
    }

    public void addShape(PoseurShape shapeToPaste, AnimationState selectedAnimationState, int selectedPoseIndex) {
        this.animationStates.get(selectedAnimationState).get(selectedPoseIndex).addShape(shapeToPaste);
    }

    public void clear(String newName) {
        this.animationStates.clear();
        this.height = DEFAULT_POSE_HEIGHT;
        this.width = DEFAULT_POSE_WIDTH;
        this.name = newName;
    }
    
    
    
    
}