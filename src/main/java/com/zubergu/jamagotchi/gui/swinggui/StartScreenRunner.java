package com.zubergu.jamagotchi.gui.swinggui;

public class StartScreenRunner implements Runnable {

  private AbstractAnimalModel model;
  
  public void run() {
    StartScreen firstScreen = new StartScreen(model);
    firstScreen.start();
    model = firstScreen.getModel();
  }
  
  public AbstractAnimalModel getModel() {
    return model;
  }

}
