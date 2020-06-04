package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;

/**
*  Thread for displaying start screen.
*/
public class StartScreenRunner implements Runnable {

  private AbstractAnimalModel model;
  
  public void run() {
    StartScreen firstScreen = new StartScreen();
    firstScreen.start();
    model = firstScreen.getModel();
  }
  
  public AbstractAnimalModel getModel() {
    return model;
  }

}
