package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.AbstractCreatureModel;
import com.zubergu.jamagotchi.gui.swinggui.MainView;
import com.zubergu.jamagotchi.controller.ICreatureController;
import com.zubergu.jamagotchi.controller.CreatureController;
import com.zubergu.jamagotchi.controller.SoundController;
import com.zubergu.jamagotchi.controller.AnimationController;

/**
* Thread from which main application will be started.
*/
public class MainViewRunner implements Runnable {

  private AbstractCreatureModel model;  
  
  public void run() {
    MainView view = new MainView();
    SoundController soundController = new SoundController();
    AnimationController animationController = new AnimationController();
    ICreatureController controller = new CreatureController( model, view, soundController, animationController );
    view.setCreatureController( controller );
    view.start();
    controller.startTicking();
  }
  
  public void setModel( AbstractCreatureModel model ) {
    this.model = model;
  }

}
