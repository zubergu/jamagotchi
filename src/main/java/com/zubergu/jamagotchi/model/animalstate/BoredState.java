package com.zubergu.jamagotchi.model.animalstate;

/**
* Implementation of animal behaviour in Bored state.
*/
public class BoredState implements AnimalStateInterface {

  private AbstractAnimalModel model;

  public BoredState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    model.decreaseLevel(Level.ANGER, ANGER_CHANGE * 50);
    model.decreaseLevel()
  }
  
  @Override
  public void feed() {
    
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
