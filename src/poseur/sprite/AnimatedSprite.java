/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import sprite_renderer.AnimationState;

/**
 *
 * @author Michael Salerno
 */
public class AnimatedSprite implements Serializable{
    private int width;
    private int height;
    private String name;
    private EnumMap<AnimationState, ArrayList<Pose>> animationStates;
    
    public AnimatedSprite(String name, int height, int width, EnumMap<AnimationState, ArrayList<Pose>> animationStates){
        this.name = name;
        this.height = height;
        this.width = width;
        this.animationStates = animationStates;
    }
    
    public AnimatedSprite(String name){
        this(name, 64,64, new EnumMap<AnimationState, ArrayList<Pose>>(AnimationState.class));
    }
    
    public AnimatedSprite(){
        this("AnimatedSprite", 64,64, new EnumMap<AnimationState, ArrayList<Pose>>(AnimationState.class));
    }
    
    public Object[] getAnimationStates(){
        return this.animationStates.keySet().toArray();
    }
    
    public ArrayList<Pose> addAnimationState(AnimationState name, ArrayList<Pose> poseList){
        return this.animationStates.put(name, poseList);        
    }
    
    public ArrayList<Pose> addAnimationState(AnimationState name){
        if(name == null) throw new IllegalArgumentException("no such animation state null");
        return this.animationStates.put(name, new ArrayList<Pose>());        
    }
    
    public boolean addPose(AnimationState name, Pose pose){
        if(!this.animationStates.containsKey(name)){
            this.addAnimationState(name);
        }
        return this.animationStates.get(name).add(pose);
    }
    
    public void addPoseAt(AnimationState name, Pose pose, int index){
        if(!this.animationStates.containsKey(name)){
            this.addAnimationState(name);
        }
        this.animationStates.get(name).add(index, pose);
    }
    
    public void copyState(AnimationState from, AnimationState to){
        ArrayList<Pose> list = this.animationStates.get(from);
        this.animationStates.put(to, list);
    }
    
    public ArrayList<Pose> removeAnimationState(AnimationState name){
        return this.animationStates.remove(name);
    }
    
    public void removePose(AnimationState state, int index){
        if(!this.animationStates.containsKey(state) || this.animationStates.get(state) == null || this.animationStates.get(state).isEmpty()) return;
        this.animationStates.get(state).remove(index);
    }
    
    public boolean swapPose(AnimationState name, int from, int to){
        if(!this.animationStates.containsKey(name)) return false;
        ArrayList<Pose> poseList = this.animationStates.get(name);
        if(poseList.size() >= from || from < 0) return false;
        if(poseList.size() >= to || to < 0) return false;
        if(from == to) return false;
        
        Pose p1 = poseList.get(from);
        Pose p2 = poseList.get(to);
        poseList.set(from, p2);
        poseList.set(to, p1);
        
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
    
    public ArrayList<Pose> getPoseList(AnimationState name){
        return this.animationStates.get(name);
    }
    
    public Pose getPose(AnimationState name, int index){
        return this.getPoseList(name).get(index);
    }
    
    
    public float setPosePause(AnimationState name, int index, float newPause){
        float oldPause = -1;
        Pose p = this.getPose(name, index);
        oldPause = p.getPause();
        p.setPause(newPause);
        return oldPause;
    }
    
    public boolean renameAnimationState(AnimationState oldS, AnimationState newS){
        if(!this.animationStates.containsKey(oldS)) return false;
        ArrayList<Pose> postList = this.animationStates.get(oldS);
        this.animationStates.remove(oldS);
        this.animationStates.put(newS, postList);
        return true;
    }
    
    //TODO: finish animatedsprite

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
    
    
    
}