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


public class AnimationController implements StateObserver, ActionObserver, ActionListener {
    
    private AnimationPanel panel;
    private AbstractSurroundingsResourcesManager surroundingsResourcesController;
    private AbstractCreatureResourcesManager creatureResourcesManager;
    
    private BufferedImage[] currentBackgroundAnimation;
    private BufferedImage[] currentCreatureAnimation;
    
    private BufferedImage frame;
    private State currentState;

    public AnimationController( AbstractSurroundingsResourcesManager surroundingsResourcesController,
                                AbstractCreatureResourcesManager creatureResourcesManager ) {
    
        this.surroundingsResourcesController = surroundingsResourcesController;
        this.creatureResourcesManager = creatureResourcesManager;
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
        panel.setupNextFrame( frame );
    }
    
    @Override
    public void actionPerformed( ActionEvent ev ) {
        // do some animation magic here on animation panel
        panel.revalidate();
        panel.repaint();
    }
    
    
    private void buildNextFrame() {
        /*
        ColorModel cm = backgroundImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = backgroundImage.copyData(null);
        frame = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        
        Graphics imgGrapgics = frame.getGraphics();
        imgGrapgics.drawImage( getSpriteForState(), 200, 200, null );
        imgGrapgics.dispose();
        */
    }
 
}
