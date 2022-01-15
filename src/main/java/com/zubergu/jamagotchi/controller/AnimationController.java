package com.zubergu.jamagotchi.controller;

import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.ActionObserver;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.Action;
import com.zubergu.jamagotchi.gui.swinggui.AnimationPanel;
import com.zubergu.jamagotchi.managers.creatures.AbstractCreatureResourcesManager;
import com.zubergu.jamagotchi.managers.surroundings.AbstractSurroundingsResourcesManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.Timer;


public class AnimationController implements StateObserver, ActionObserver, ActionListener {
    
    private AnimationPanel panel;
    private AbstractSurroundingsResourcesManager surroundingsResourcesManager;
    private AbstractCreatureResourcesManager creatureResourcesManager;
    
    private BufferedImage[] currentBackgroundAnimation;
    private BufferedImage[] currentCreatureAnimation;
    private int nextBackgroundAnimationFrame = 0;
    private int nextCreatureAnimationFrame = 0;
    private int backgroundAnimationDirection = 1;
    private int creatureAnimationDirection = 1;
    
    private int xCreaturePosition = 200;
    private int yCreaturePosition = 200; 
    
    private BufferedImage frame;
    private State currentState;

    public AnimationController( AbstractSurroundingsResourcesManager surroundingsResourcesManager,
                                AbstractCreatureResourcesManager creatureResourcesManager ) {
    
        this.surroundingsResourcesManager = surroundingsResourcesManager;
        this.creatureResourcesManager = creatureResourcesManager;
    }
    
    public void updateOnStateChange( State state ) {
        this.currentState = state;
        currentBackgroundAnimation = surroundingsResourcesManager.getAnimationForState( state );
        currentCreatureAnimation = creatureResourcesManager.getAnimationForState( state );
        
        // prevent reading outside of animation array if animation has only 1 frame
        if( currentBackgroundAnimation.length < 2 ) {
            backgroundAnimationDirection = 0;
        }
        if( currentCreatureAnimation.length < 2 ) {
            creatureAnimationDirection = 0;
        }
        // which frame number should be displayed
        nextBackgroundAnimationFrame = 0;
        nextCreatureAnimationFrame = 0;
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
        panel.setupNextFrame( frame );
    }
    
    @Override
    public void actionPerformed( ActionEvent ev ) {
        buildNextFrame();
        panel.setupNextFrame( frame );
        panel.revalidate();
        panel.repaint();
    }
    
    
    private void buildNextFrame() {
        
        frame = currentBackgroundAnimation[nextBackgroundAnimationFrame];
        
        Graphics g = frame.getGraphics();
        // g.drawImage( backgroundImage, 0, 0, null );
        
        /* TODO: draw also action images here layered bgd->bgdAction->creature->creatureAction */
        g.drawImage( currentCreatureAnimation[nextCreatureAnimationFrame], xCreaturePosition, yCreaturePosition, null );
        g.dispose();

        
        if( nextBackgroundAnimationFrame + backgroundAnimationDirection >= currentBackgroundAnimation.length
            ||
            nextBackgroundAnimationFrame + backgroundAnimationDirection < 0 ) {
        
            backgroundAnimationDirection = -backgroundAnimationDirection;
            
        }
        
        if( nextCreatureAnimationFrame + creatureAnimationDirection >= currentCreatureAnimation.length
            ||
            nextCreatureAnimationFrame + creatureAnimationDirection < 0 ) {
        
            creatureAnimationDirection = -creatureAnimationDirection;
            
        }
        
        nextBackgroundAnimationFrame += backgroundAnimationDirection;
        nextCreatureAnimationFrame += creatureAnimationDirection;
    }
    
    public void startTicking() {
        Timer timer = new Timer( 1000, this );
        timer.start();
    }
 
}
