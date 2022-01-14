package com.zubergu.jamagotchi.managers.creatures;

import com.zubergu.jamagotchi.model.Action;
import com.zubergu.jamagotchi.model.State;

import java.net.URL;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;

import java.util.HashMap;


public abstract class AbstractCreatureResourcesManager {
    
    public abstract BufferedImage[] getCreatureAnimationForState( State state );
    public abstract BufferedImage[] getCreatureAnimationForActionInState( State state, Action action );
    public abstract Clip getSoundForState( State state );
    public abstract Clip getSoundForActionInState( State state, Action action );
    
    private BufferedImage getSpriteForState( State state ) {
        BufferedImage result = null;
        
        /*
        switch( currentState ) {
            case IDLE: result = spriteSheet.getSubimage( 1070, 1440, 120, 120 ); break;
            case PLAYING: result = spriteSheet.getSubimage( 800, 1060, 120, 120 ); break;
            case SLEEPING: result = spriteSheet.getSubimage( 1600, 1400, 250, 250 ); break;
            case BORED: result = spriteSheet.getSubimage( 1200, 1470, 200, 200 ); break;
            case ANGRY: result = spriteSheet.getSubimage( 600, 1500, 150, 150 ); break;
            default: result = spriteSheet.getSubimage( 1070, 1440, 120, 120 ); break;
            
        }
        */
        
        return result;
    }
    
}
