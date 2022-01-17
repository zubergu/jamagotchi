package com.zubergu.jamagotchi.managers.creatures.dog;

import com.zubergu.jamagotchi.managers.creatures.AbstractCreatureResourcesManager;
import com.zubergu.jamagotchi.model.*;
import com.zubergu.jamagotchi.gui.animation.AnimationElement;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class DogResourcesManager extends AbstractCreatureResourcesManager {
    
    private enum AnimationParameters {
        
        /* TODO: Fill other states with 1 frame still images at first */
        //TIRED( State.TIRED, null, 1, ),
        BORED( State.BORED, null, 4, 1100, 1200, 120, 120 ),
        PLAYING( State.PLAYING, null, 5, 90, 1060, 120, 120 ),
        IDLE( State.IDLE, null, 8, 70, 70, 190, 190 ),       
        //HUNGRY( State.HUNGRY, null, 1, ),         
        //DEAD( State.DEAD, null, 1, ),
        //DIRTY( State.DIRTY, null, 1,  ),
        //SICK( State.SICK, null, 1,  ),
        SLEEPING( State.SLEEPING, null, 2, 1400, 1400, 230, 250 ),
        //SEEKING_ATTENTION( State.SEEKING_ATTENTION, null, 1, ),
        ANGRY( State.ANGRY, null, 5, 600, 1500, 150, 150 ); 
        
        
        private static final HashMap<State, HashMap<Action, AnimationParameters>> mapStateAndActionToParams = new HashMap<State, HashMap<Action, AnimationParameters>>();
        
        static {
            
            for( AnimationParameters e : values() ) {
                if( mapStateAndActionToParams.get( e.state ) == null ) {
                    mapStateAndActionToParams.put( e.state, new HashMap<Action, AnimationParameters>() );
                }
                
                mapStateAndActionToParams.get( e.state ).put( e.action, e );
            }
            
        }
        
        
        
        State state;
        Action action;
        int numberOfFrames;
        int startX;
        int startY;
        int width;
        int height;
        
        private AnimationParameters( State state, Action action, int numberOfFrames, int startX, int startY, int width, int height ) {
            this.state = state;
            this.action = action;
            this.numberOfFrames = numberOfFrames;
            this.startX = startX;
            this.startY = startY;
            this.width = width;
            this.height = height;
        }
        
        static AnimationParameters getParamsForStateAndAction( State state, Action action ) {
            // is requested state is invali or requested state is not handled
            // return IDLE animation for no action
            if( state == null || mapStateAndActionToParams.get( state ) == null ) {
                return mapStateAndActionToParams.get( State.IDLE ).get( null );
            }
            
            return mapStateAndActionToParams.get( state ).get( action );
        }
        
        
    }

    private static final String DOG_SPRITE_IMAGE_PATH = "/resources/creatures/dog/graphics/puppy_sprites.png";
    private static final String IDLE_AUDIO_PATH = "/resources/creatures/dog/audio/YAWN.wav";
    
    private BufferedImage spriteSheet = null;
    
    public DogResourcesManager() {
        this.spriteSheet = openDogSprite();
    }   
    
    @Override
    public AnimationElement[] createStateAnimation( State state ) {
        AnimationElement[] stateAnimation = null;
        AnimationParameters animationParams = AnimationParameters.getParamsForStateAndAction( state, null );
        
        stateAnimation = new AnimationElement[ animationParams.numberOfFrames ];
          
        /* TODO */
        for( int i = 0; i < stateAnimation.length; i++ ) {
            stateAnimation[i] = new AnimationElement( getSpriteSubimage( animationParams, i ), 0, 0 );
        }
        
        return stateAnimation;
    }
    
    @Override
    public AnimationElement[] createActionStateAnimation( State state, Action action ) {
        AnimationElement[] stateActionAnimation = null;
        AnimationParameters animationParams = AnimationParameters.getParamsForStateAndAction( state, action );
   
        for( int i = 0; i < stateActionAnimation.length; i++ ) {
            stateActionAnimation[i] = new AnimationElement( getSpriteSubimage( animationParams, i ), 0, 0 );
        }
        
        return stateActionAnimation;
    }
    
    
    public Clip createStateSound( State state ) {
        Clip result = null;  
        String path = "";
        
        switch( state ) {
            case IDLE: 
            case HUNGRY:
            case BORED:
            default:
                path = IDLE_AUDIO_PATH;
                break;
        }
        
        try {
            URL url = DogResourcesManager.class.getResource( path );
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
        
        
        try {
            URL url = DogResourcesManager.class.getResource( path );
            result = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            result.open(ais);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        
        
        return result;
    }
    
    private BufferedImage openDogSprite() {
        BufferedImage result = null;
  
        try {
            result = ImageIO.read( DogResourcesManager.class.getResource( DOG_SPRITE_IMAGE_PATH ) );
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
        
        return result;

    }
    
    private BufferedImage getSpriteSubimage( AnimationParameters params, int frame ) {
                 
        return spriteSheet.getSubimage( params.startX + params.width*frame, 
                                        params.startY, 
                                        params.width,
                                        params.height );
        
    }
    
}
