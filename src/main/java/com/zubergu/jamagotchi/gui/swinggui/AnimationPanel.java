package com.zubergu.jamagotchi.gui.swinggui;


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;


import java.util.Random;

public class AnimationPanel extends JPanel {
    
    private BufferedImage frame;
    
    private Random generator = new Random();
    
    public AnimationPanel() {
        super();
        this.setOpaque( true );
    }
    
    public void setupNextFrame( BufferedImage frame ) {
        this.frame = frame;
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        g.drawImage( frame, 0, 0, null );
        g.dispose();
    }
}
