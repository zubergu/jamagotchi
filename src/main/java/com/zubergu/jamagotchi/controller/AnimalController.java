package com.zubergu.jamagotchi.controller;

/**
*
*/
public class AnimalController implements ControllerInterface {

  private AbstractAnimalModel animal;
  private MainView view;

  public AnimalController(AbstractAnimalModel model, MainView view) {
    this.animal = model;
    this.view = view;
    
    /* set up observers for model */
    animal.registerStateObserver(this);
    animal.registerLevelsObserver(view);
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
  public void startTicking() {
    
  }
  
  /* when state changes there should be update in view done from here */
  public void updateOnChangeState(AnimalStateInterface state) {
  
  }
  
  /* actions to be taken when user closes the application */
  public void shutdownOperations() {
    // save the model
    
  }

}
