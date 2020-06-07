package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Sick state.
*/
public class SickState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  private static final int HEALTH_CHANGE = 1;
  
  private AbstractAnimalModel model;

  public SickState(AbstractAnimalModel model) {
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
    model.setLevel(Level.DIRTINESS, model.getMinLevel());
    model.setLevel(Level.ENERGY, model.getMaxLevel());
    model.setLevel(Level.JOY, model.getMinLevel());
    model.setLevel(Level.HEALTH, model.getMaxLevel());
    model.setLevel(Level.HUNGER, model.getMinLevel());
    model.setLevel(Level.JOY, model.getMinLevel());
    
    model.setState(State.IDLE);
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
    // nothing
  }
  
  @Override
  public void tick() {
    model.decreaseLevel(Level.HEALTH, HEALTH_CHANGE);
    if(model.getLevel(Level.HEALTH) <= model.getMinLevel()) {
      model.setState(State.DEAD);
    }
  }
  
}
