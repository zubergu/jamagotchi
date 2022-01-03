package com.zubergu.jamagotchi.audio;

import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.ActionObserver;
import com.zubergu.jamagotchi.model.animalmodel.Action;
import com.zubergu.jamagotchi.model.animalmodel.State;

import java.net.URL;
import javax.sound.sampled.*;

import java.util.Map;
import java.util.HashMap;

public class SoundController implements StateObserver, ActionObserver {

    private State state = null;
    private Action currentAction = null;
    private Clip currentClip = null;
    
    private Map<State, HashMap<Action,Clip>> clips = new HashMap<State, HashMap<Action,Clip>>();
    
    public SoundController() {
        URL url = null;
        Clip clip = null;
        AudioInputStream ais = null;
        
        // clip to play is combination of ( State, Action )
        for( State a : State.values() ) {
            clips.put( a, new HashMap<Action,Clip>() );
        }
        
        
        try {
            url = SoundController.class.getResource( "/YAWN.wav" );
            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
        } catch ( Exception ex ) {
            ex.printStackTrace();
            System.exit( 0 );
        }
        
        clips.get( State.IDLE ).put( Action.IDLE, clip );
        clips.get( State.TIRED ).put( Action.IDLE, clip );
        clips.get( State.BORED ).put( Action.IDLE, clip );
        clips.get( State.PLAYING ).put( Action.IDLE, clip );
        clips.get( State.HUNGRY ).put( Action.IDLE, clip );
        clips.get( State.DEAD ).put( Action.IDLE, clip );
        clips.get( State.DIRTY ).put( Action.IDLE, clip );
        clips.get( State.SICK ).put( Action.IDLE, clip );
        clips.get( State.SLEEPING ).put( Action.IDLE, clip );
        clips.get( State.SEEKING_ATTENTION ).put( Action.IDLE, clip );
        clips.get( State.ANGRY ).put( Action.IDLE, clip );

    }
    
    
    public void updateOnStateChange( State state ) {
        this.state = state;
        playSound( state, Action.IDLE );
    }
    
    public void notifyOfAction( Action action ) {
        // playSound( state, action );
    }
    
    public void playSound( State state, Action action ) {
        if( currentClip != null && currentClip.isRunning() ) {
            currentClip.stop();
        }
        
        currentClip = clips.get( state ).get( action );
        currentClip.setFramePosition( 0 );
        currentClip.start();
    }
    
}
