package com.zubergu.jamagotchi.controller;

import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.ActionObserver;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.Action;
import com.zubergu.jamagotchi.gui.swinggui.AnimationPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.io.IOException;
import javax.imageio.ImageIO;


public class AnimationController implements StateObserver, ActionObserver, ActionListener {
    
    private AnimationPanel panel;
    
    private BufferedImage backgroundImage;
    private BufferedImage frame;
    private BufferedImage spriteSheet;
    private State currentState;

    public AnimationController() {
        try {
            backgroundImage = ImageIO.read( AnimationController.class.getResource( "/room_background.png" ) );
            spriteSheet = ImageIO.read( AnimationController.class.getResource( "/puppy_sprites.png" ) );
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }
    
    public void updateOnStateChange( State state ) {
        this.currentState = state;
        buildNextFrame();
        panel.setupNextFrame( frame );
                panel.revalidate();
        panel.repaint();
    }
    
    public void notifyOfAction( Action action ) {
        // buildNextFrame();
        // panel.setupNextFrame( frame );
        panel.revalidate();
        panel.repaint();
    }
    
    public void setAnimationPanel( AnimationPanel panel ) {
        this.panel = panel;
        panel.setupNextFrame( backgroundImage );
    }
    
    @Override
    public void actionPerformed( ActionEvent ev ) {
        // do some animation magic here on animation panel
        panel.revalidate();
        panel.repaint();
    }
    
    
    private void buildNextFrame() {
        ColorModel cm = backgroundImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = backgroundImage.copyData(null);
        frame = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        
        Graphics imgGrapgics = frame.getGraphics();
        imgGrapgics.drawImage( getSpriteForState(), 200, 200, null );
        imgGrapgics.dispose();
    }
    
    private BufferedImage getSpriteForState() {
        BufferedImage result = null;
        
        switch( currentState ) {
            case IDLE: result = spriteSheet.getSubimage( 1070, 1440, 120, 120 ); break;
            case PLAYING: result = spriteSheet.getSubimage( 800, 1060, 120, 120 ); break;
            case SLEEPING: result = spriteSheet.getSubimage( 1600, 1400, 250, 250 ); break;
            case BORED: result = spriteSheet.getSubimage( 1200, 1470, 200, 200 ); break;
            case ANGRY: result = spriteSheet.getSubimage( 600, 1500, 150, 150 ); break;
            default: result = spriteSheet.getSubimage( 1070, 1440, 120, 120 ); break;
            
        }
        
        return result;
    }
}
