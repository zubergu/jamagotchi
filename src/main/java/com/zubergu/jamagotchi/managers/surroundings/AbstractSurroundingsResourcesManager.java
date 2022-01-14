package com.zubergu.jamagotchi.managers.surroundings;

import java.awt.image.BufferedImage;

import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.Action;

import java.net.URL;
import javax.sound.sampled.*;

import java.util.HashMap;


public abstract class AbstractSurroundingsResourcesManager {
    
    private HashMap<State, BufferedImage[]> stateAnimation;
    private HashMap<State, HashMap<Action, BufferedImage[]>> actionStateAnimation;
    private HashMap<State, Clip> stateSound;
    private HashMap<State, HashMap<Action, Clip>> actionStateSound;
    
    public AbstractSurroundingsResourcesManager() {
        this.stateAnimation = new HashMap<State, BufferedImage[]>();
        this.actionStateAnimation = new HashMap<State, HashMap<Action, BufferedImage[]>>();
        this.stateSound = new HashMap<State, Clip>();
        this.actionStateSound = new HashMap<State, HashMap<Action, Clip>>();   
        
        for( State s : State.values() ) {
            actionStateAnimation.put( s, new HashMap<Action, BufferedImage[]>() );
            actionStateSound.put( s, new HashMap<Action, Clip>() );
        }
    }
    
    public BufferedImage[] getBackgroundAnimationForState( State state ) {
        BufferedImage[] result = stateAnimation.get( state );
        
        if( result == null ) {
            stateAnimation.put( state, createStateAnimation( state ) );
            result = stateAnimation.get( state );
        }
        
        return result;
    }
    
    public BufferedImage[] getBackgroundAnimationForActionInState( State state, Action action ) {
        BufferedImage[] result = actionStateAnimation.get( state ).get( action );
        
        if( result == null ) {
            actionStateAnimation.get( state ).put( action, createActionStateAnimation( state, action ) );
            result = actionStateAnimation.get( state ).get( action );
        }
        
        return result;
        
    }
    
    public Clip getSoundForState( State state ) {
        Clip result = stateSound.get( state );
        
        if( result == null ) {
            stateSound.put( state, createStateSound( state ) );
            result = stateSound.get( state );
        }
        
        return result;
    }
    
    public Clip getSoundForActionInState( State state, Action action ) {
        Clip result = actionStateSound.get( state ).get( action );
        
        if( result == null ) {
            actionStateSound.get( state ).put( action, createActionStateSound( state, action ) );
            result = actionStateSound.get( state ).get( action );
        }
        
        return result;
    }
    
    
    
    protected abstract BufferedImage[] createStateAnimation( State state );
    protected abstract BufferedImage[] createActionStateAnimation( State state, Action action );
    protected abstract Clip createStateSound( State state );
    protected abstract Clip createActionStateSound( State state, Action action );
    
}
