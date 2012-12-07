/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import poseur.state.PoseurPose;
import sprite_renderer.AnimationState;

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
        this.animationStates.put(to, list);
    }
    
    public ArrayList<PoseurPose> removeAnimationState(AnimationState name){
        return this.animationStates.remove(name);
    }
    
    public void removePose(AnimationState state, int index){
        if(!this.animationStates.containsKey(state) || this.animationStates.get(state) == null || this.animationStates.get(state).isEmpty()) return;
        this.animationStates.get(state).remove(index);
    }
    
    public boolean swapPose(AnimationState name, int from, int to){
        if(!this.animationStates.containsKey(name)) return false;
        ArrayList<PoseurPose> poseList = this.animationStates.get(name);
        if(poseList.size() >= from || from < 0) return false;
        if(poseList.size() >= to || to < 0) return false;
        if(from == to) return false;
        
        PoseurPose p1 = poseList.get(from);
        PoseurPose p2 = poseList.get(to);
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
    
    public ArrayList<PoseurPose> getPoseList(AnimationState name){
        return this.animationStates.get(name);
    }
    
    public PoseurPose getPose(AnimationState name, int index){
        return this.getPoseList(name).get(index);
    }
    
    
    public float setPosePause(AnimationState name, int index, float newPause){
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