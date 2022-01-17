package com.zubergu.jamagotchi.gui.animation;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


public class AnimationElement {
    private int deltaX;
    private int deltaY;
    
    private BufferedImage element;
    private BufferedImage flippedElement;
    
    private AffineTransform mirrorTransform;
    private AffineTransformOp mirrorOp;

    
    public AnimationElement( BufferedImage element, int deltaX, int deltaY ) {
        this.element = element;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        
       mirrorTransform = AffineTransform.getScaleInstance( -1, 1 );
       mirrorTransform.translate( -element.getWidth(), 0 );
       mirrorOp = new AffineTransformOp( mirrorTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR );
    }
    
    public BufferedImage getImage( boolean flipped ) {
        if( flipped ) {
            if( flippedElement == null ) {
                flippedElement = mirrorOp.filter( element, null );
            }
            
            return flippedElement;
        }

        
        return element;
    }
    
    public int getDeltaX( boolean flipped ) {
        return flipped ? -deltaX : deltaX;
    }
    
    public int getDeltaY( boolean flipped ) {
        return flipped ? -deltaY: deltaY;
    }

    
}
