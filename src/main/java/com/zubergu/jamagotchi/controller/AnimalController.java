package com.zubergu.jamagotchi.controller;


import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;
import com.zubergu.jamagotchi.gui.swinggui.MainView;
import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.animalstate.AnimalStateInterface;

import java.util.Timer;
import java.util.TimerTask;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
*
*/
public class AnimalController implements ControllerInterface, StateObserver {

  private AbstractAnimalModel animal;
  private MainView view;

  public AnimalController(AbstractAnimalModel model, MainView view) {
    this.animal = model;
    this.view = view;
    
    /* set up observers for model */
    animal.registerObserver(this);
    animal.registerObserver(view);
  }
  
  public void playWith() {
    animal.playWith();
  }
  
  public void stopPlaying() {
    animal.stopPlaying();
  }
  
  public void pet() {
    animal.pet();
  }
  
  public void feed() {
    animal.feed();
  }
  
  public void clean() {
    animal.clean();
  }
  
  public void takeToVet() {
    animal.takeToVet();
  }
  
  public void talkTo() {
    animal.talkTo();
  }
  
  public void wakeUp() {
    animal.wakeUp();
  }
  
  /* set timer for updating model statistics:
    call animal.tick() periodically */
  @Override
  public void startTicking() {
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        animal.tick();
      }
    }, 0, 1000);
  }
  
  /* when state changes there should be update in view done from here */
  public void updateOnStateChange(AnimalStateInterface state) {
    view.setStateLabel(state.getClass().getSimpleName());
  }
  
  /* actions to be taken when user closes the application */
  public void shutdownActions() {
    // save the model and 
    String fileName = animal.getName() + ".jmg";
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
      oos.writeObject(animal);
      oos.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    System.exit(0);
  }

}
