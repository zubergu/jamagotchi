package com.zubergu.jamagotchi.managers.surroundings.basic;

import com.zubergu.jamagotchi.managers.surroundings.AbstractSurroundingsResourcesManager;
import com.zubergu.jamagotchi.model.*;
import com.zubergu.jamagotchi.gui.animation.AnimationElement;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BasicSurroundingsResourcesManager extends AbstractSurroundingsResourcesManager {
    
    private static final int NUM_OF_FRAMES_BGD_STATE = 10;
    private static final int NUM_OF_FRAMES_ACTION_STATE = 10;
    private static final String BGD_IMAGE_PATH = "/resources/surroundings/basic/graphics/room_background.png";
    private static final String IDLE_AUDIO_PATH = "/resources/surroundings/basic/audio/YAWN.wav";
    
    @Override
    public AnimationElement[] createStateAnimation( State state ) {
        AnimationElement[] stateAnimation = new AnimationElement[ NUM_OF_FRAMES_BGD_STATE ];
        BufferedImage backgroundImage = openImageForState( state );
        
        // actual animation will go here when backgound changes will be implemented
        for( int i = 0; i < stateAnimation.length; i++ ) {
            stateAnimation[i] = new AnimationElement( backgroundImage, 0, 0 );
        }
        
        return stateAnimation;
    }
    
    @Override
    public AnimationElement[] createActionStateAnimation( State state, Action action ) {
        AnimationElement[] actionStateAnimation = new AnimationElement[ NUM_OF_FRAMES_ACTION_STATE ];
        
        // action animation should go here when implementing
        BufferedImage frame = openImageForState( state );
        
        // actual animation will go here when backgound changes will be implemented
        for( int i = 0; i < actionStateAnimation.length; i++ ) {
            actionStateAnimation[i] = new AnimationElement( frame, 0, 0 );
        }
        
        return actionStateAnimation;
    }
    
    private BufferedImage openImageForState( State state ) {
        BufferedImage result = null;
        String path = "";
        

        switch( state ) {
            case IDLE:
            case HUNGRY:
            default:
                path = BGD_IMAGE_PATH;
                break;
        }

        
        try {
            result = ImageIO.read( BasicSurroundingsResourcesManager.class.getResource( path ) );
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
        
        return result;

    }
    
    
    public Clip createStateSound( State state ) {
        Clip result = null;  
        String path = "";
        
        switch( state ) {
            case IDLE: 
            case HUNGRY:
            default:
                path = IDLE_AUDIO_PATH;
                break;
        }
        
        
        try {
            URL url = BasicSurroundingsResourcesManager.class.getResource( path );
            result = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            result.open(ais);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        
        
        return result;

    }
    
    
    
    public Clip createActionStateSound( State state, Action action ) {
        Clip result = null;  
        String path = "";
        
        switch( state ) {
            case IDLE: 
            case HUNGRY:
            default:
                path = IDLE_AUDIO_PATH;
                break;
        }
        
        /*
        try {
            Url url = SoundController.class.getResource( path );
            result = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            result.open(ais);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        */
        
        return result;
    }
    
}
