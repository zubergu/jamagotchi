package com.zubergu.jamagotchi.model.animalstate;

/**
* Implementation of animal behaviour in Angry state.
*/
public class AngryState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  
  private AbstractAnimalModel model;

  public AngryState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    model.decreaseLevel(Level.ANGER, ANGER_CHANGE * 50);
    if( model.getLevel(Level.ANGER) < (model.getMaxLevel()/5) ) {
      model.setState(State.IDLE);
    }
  }
  
  @Override
  public void feed() {
    //
  }
  
  @Override
  public void takeToVet() {
    //
  }
  
  @Override
  public void playWith() {
    //
  }
  
  @Override
  public void stopPlaying() {
    //
  }
  
  @Override
  public void clean() {
    //
  }
  
  @Override
  public void talkTo() {
    model.decreaseLeve(Level.ANGER, ANGER_CHANGE * 20);
    if( model.getLevel(Level.ANGER) < (model.getMaxLevel()/5) ) {
      model.setState(State.IDLE);
    }
  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    model.decreaseLevel(Level.ENERGY, ENERGY_CHANGE);
    model.decreaseLevel(Level.JOY, JOY_CHANGE);
    
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.SICK);
    }
    
  }
  
}
