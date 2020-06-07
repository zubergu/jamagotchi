package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Sleeping state.
*/
public class SleepingState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 1;
  private static final int ENERGY_CHANGE = 20;
  private static final int JOY_CHANGE = 10;
  
  private AbstractAnimalModel model;

  public SleepingState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    //
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
    //
  }
  
  @Override
  public void wakeUp() {
    if(model.getLevel(Level.ENERGY) < model.getMaxLevel()/2 ) {
      model.decreaseLevel(Level.JOY, JOY_CHANGE);
    }
    
    model.setState(State.IDLE);
  }
  
  @Override
  public void tick() {
    model.increaseLevel(Level.ENERGY, ENERGY_CHANGE);
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    
    if(model.getLevel(Level.ENERGY) >= model.getMaxLevel()) {
      model.setState(State.IDLE);
    }
  }
  
}
