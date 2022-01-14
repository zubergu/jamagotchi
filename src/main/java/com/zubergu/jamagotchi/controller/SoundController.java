package com.zubergu.jamagotchi.controller;

import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.ActionObserver;
import com.zubergu.jamagotchi.model.Action;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.managers.creatures.AbstractCreatureResourcesManager;
import com.zubergu.jamagotchi.managers.surroundings.AbstractSurroundingsResourcesManager;

import java.net.URL;
import javax.sound.sampled.*;

import java.util.Map;
import java.util.HashMap;

public class SoundController implements StateObserver, ActionObserver {

    private State state = null;
    private Action currentAction = null;
    private Clip currentSurroundingsStateClip = null;
    private Clip currentSurroundingsActionClip = null;
    private Clip currentCreatureStateClip = null;
    private Clip currentCreatureActionClip = null;
    
    private AbstractSurroundingsResourcesManager surroundingsResourcesManager;
    private AbstractCreatureResourcesManager creatureResourcesManager;
        
    public SoundController( AbstractSurroundingsResourcesManager surroundingsResourcesManager,
                            AbstractCreatureResourcesManager creatureResourcesManager ) {
    
        this.surroundingsResourcesManager = surroundingsResourcesManager;
        this.creatureResourcesManager = creatureResourcesManager;
    }
    
    
    public void updateOnStateChange( State state ) {
        this.state = state;
        stopSounds();
        currentCreatureStateClip = creatureResourcesManager.getSoundForState( state );
        currentSurroundingsStateClip = surroundingsResourcesManager.getSoundForState( state );
        playSounds();
    }
    
    public void notifyOfAction( Action action ) {
        // playSound( state, action );
    }
    
    private void playSounds( ) {
        if( currentCreatureStateClip != null ) {
            currentCreatureStateClip.setFramePosition( 0 );
            currentCreatureStateClip.start();
        }
        
        if( currentCreatureActionClip != null ) {
            currentCreatureActionClip.setFramePosition( 0 );
            currentCreatureActionClip.start();
        }
                
        if( currentSurroundingsStateClip != null ) {
            currentSurroundingsStateClip.setFramePosition( 0 );
            currentSurroundingsStateClip.start();
        }
                
        if( currentSurroundingsActionClip != null ) {
            currentSurroundingsActionClip.setFramePosition( 0 );
            currentSurroundingsActionClip.start();
        }
    }
    
    private void stopSounds() {
        if( currentCreatureStateClip != null && currentCreatureStateClip.isRunning() ) {
            currentCreatureStateClip.stop();
        }
        
        if( currentCreatureActionClip != null && currentCreatureActionClip.isRunning() ) {
            currentCreatureActionClip.stop();
        }
                
        if( currentSurroundingsStateClip != null && currentSurroundingsStateClip.isRunning() ) {
            currentSurroundingsStateClip.stop();
        }
                
        if( currentSurroundingsActionClip != null && currentSurroundingsActionClip.isRunning() ) {
            currentSurroundingsActionClip.stop();
        }
    }
    
}
