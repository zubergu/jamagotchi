package com.zubergu.jamagotchi.gui.swinggui;

public class MainViewRunner implements Runnable {

  private AbstractAnimalModel model;
  
  
  public void run() {
    MainView view = new MainView();
    ControllerInterface controller = new AnimalController(model, view);
    mainView.start();
  }
  
  public void setModel(AbstractAnimalModel model) {
    this.model = model;
  }

}
