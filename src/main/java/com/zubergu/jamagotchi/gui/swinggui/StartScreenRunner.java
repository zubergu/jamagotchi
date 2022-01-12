package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.AbstractCreatureModel;

/**
*  Thread for displaying start screen.
*/
public class StartScreenRunner implements Runnable {

  private AbstractCreatureModel model;
  
  public void run() {
    StartScreen firstScreen = new StartScreen();
    firstScreen.start();
    model = firstScreen.getModel();
  }
  
  public AbstractCreatureModel getModel() {
    return model;
  }

}
