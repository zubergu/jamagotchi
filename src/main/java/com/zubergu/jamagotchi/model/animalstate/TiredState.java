package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;

/**
* Implementation of animal behaviour in Tired state.
*/
public class TiredState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  
  private AbstractAnimalModel model;
  
  private int tiredTickCounter = 0;

  public TiredState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void feed() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void takeToVet() {
    //
  }
  
  @Override
  public void playWith() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void stopPlaying() {
    //
  }
  
  @Override
  public void clean() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void talkTo() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void wakeUp() {
    model.increaseLevel(Level.ANGER, ANGER_CHANGE);
    if(model.getLevel(Level.ANGER) >= model.getMaxLevel()) {
      model.setState(State.ANGRY);
    }
  }
  
  @Override
  public void tick() {
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    model.decreaseLevel(Level.ENERGY, ENERGY_CHANGE);
    
    
    if(model.getLevel(Level.ENERGY) <= model.getMinLevel()) {
      model.setState(State.SLEEPING);
    }
    
  }
  
}
