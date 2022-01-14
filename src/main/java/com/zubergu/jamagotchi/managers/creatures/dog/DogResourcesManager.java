package com.zubergu.jamagotchi.managers.creatures.dog;

import com.zubergu.jamagotchi.managers.creatures.AbstractCreatureResourcesManager;
import com.zubergu.jamagotchi.model.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.sound.sampled.*;

public class DogResourcesManager extends AbstractCreatureResourcesManager {

    public BufferedImage[] getCreatureAnimationForState( State state ) { return null;}
    public BufferedImage[] getCreatureAnimationForActionInState( State state, Action action ){return null;}
    public Clip getSoundForState( State state ){return null;}
    public Clip getSoundForActionInState( State state, Action action ){return null;}
}
