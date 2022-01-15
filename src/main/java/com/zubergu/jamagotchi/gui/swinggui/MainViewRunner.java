package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.AbstractCreatureModel;
import com.zubergu.jamagotchi.gui.swinggui.MainView;
import com.zubergu.jamagotchi.controller.ICreatureController;
import com.zubergu.jamagotchi.controller.CreatureController;
import com.zubergu.jamagotchi.controller.SoundController;
import com.zubergu.jamagotchi.controller.AnimationController;
import com.zubergu.jamagotchi.managers.creatures.AbstractCreatureResourcesManager;
import com.zubergu.jamagotchi.managers.surroundings.AbstractSurroundingsResourcesManager;
import com.zubergu.jamagotchi.managers.creatures.dog.DogResourcesManager;
import com.zubergu.jamagotchi.managers.surroundings.basic.BasicSurroundingsResourcesManager;

/**
* Thread from which main application will be started.
*/
public class MainViewRunner implements Runnable {

  private AbstractCreatureModel model;  
  
  public void run() {
      
    MainView view = new MainView();
    AbstractCreatureResourcesManager creatureResourcesManager = new DogResourcesManager();
    AbstractSurroundingsResourcesManager surroundingsResourcesManager = new BasicSurroundingsResourcesManager();
    SoundController soundController = new SoundController( surroundingsResourcesManager, creatureResourcesManager);
    AnimationController animationController = new AnimationController( surroundingsResourcesManager, creatureResourcesManager );
    ICreatureController creatureController = new CreatureController( model, view, soundController, animationController );
    view.setCreatureController( creatureController );
    view.start();
    creatureController.startTicking();
    animationController.startTicking();
    
  }
  
  public void setModel( AbstractCreatureModel model ) {
    this.model = model;
  }

}
