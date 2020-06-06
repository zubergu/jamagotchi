package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;
import com.zubergu.jamagotchi.gui.swinggui.MainView;
import com.zubergu.jamagotchi.controller.ControllerInterface;
import com.zubergu.jamagotchi.controller.AnimalController;

/**
* Thread from which main application will be started.
*/
public class MainViewRunner implements Runnable {

  private AbstractAnimalModel model;  
  
  public void run() {
    MainView view = new MainView();
    ControllerInterface controller = new AnimalController(model, view);
    view.setController(controller);
    view.start();
    controller.startTicking();
  }
  
  public void setModel(AbstractAnimalModel model) {
    this.model = model;
  }

}
