/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import javax.swing.ImageIcon;
import poseur.state.PoseurPose;
import sprite_renderer.AnimationState;
import sprite_renderer.Pose;
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
        this(name, 64,64, new EnumMap<AnimationState, ArrayList<PoseurPose>>(AnimationState.class));
    }
    
    public AnimatedSprite(){
        this("AnimatedSprite", 64,64, new EnumMap<AnimationState, ArrayList<PoseurPose>>(AnimationState.class));
    }
    
    public Object[] getAnimationStates(){
        return this.animationStates.keySet().toArray();
    }
    
    public ArrayList<PoseurPose> addAnimationState(AnimationState name, ArrayList<PoseurPose> poseList){
        if(this.animationStates.containsKey(name)) this.animationStates.remove(name);
        return this.animationStates.put(name, poseList);        
    }
    
    public void replaceShapesList(AnimationState state, int i, Collection c){
        this.animationStates.get(state).get(i).getShapesList().clear();
        this.animationStates.get(state).get(i).getShapesList().addAll(c);
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
            System.out.println("no key named" + name);
            return false;
        }
        ArrayList<PoseurPose> poseList = this.animationStates.get(name);
        if(poseList.size() <= from || from < 0){
            System.out.println("list size: " + poseList.size() + " from: " + from);
            return false;
        }
        if(poseList.size() <= to || to < 0){
            System.out.println("list size: " + poseList.size() + " to: " + to);
            return false;
        }
        if(from == to){
            System.out.println("to = from");
            return false;
        }
        
        PoseurPose tmp = new PoseurPose();
        tmp.loadPoseData(this.getPose(name, from));
        this.getPose(name, from).loadPoseData(this.getPose(name, to));
        this.getPose(name, to).loadPoseData(tmp);
        System.out.println("SWAPED");
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
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Sprite convertToSprite(){
        SpriteType st = new SpriteType();
        int temp = 0;
        PoseList tempPoseList;
//        Pose p = new Pose;
        for(AnimationState state : this.animationStates.keySet()){
            tempPoseList = st.addPoseList(state);
            
            for(PoseurPose pose : this.getPoseList(state)){
                st.addImage(temp, pose.getImage());                
                tempPoseList.addPose(temp++, pose.getPause());
            }
        }
        
        Sprite s = new Sprite(st, (AnimationState)this.animationStates.keySet().toArray()[0]);
        s.update(.5F);
        return s;
    }
    
}