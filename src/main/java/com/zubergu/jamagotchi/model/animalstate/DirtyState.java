package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Dirty state.
*/
public class DirtyState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  private static final int DIRTINESS_CHANGE = 1;
  
  private AbstractAnimalModel model;

  public DirtyState(AbstractAnimalModel model) {
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
    model.setLevel(Level.DIRTINESS, model.getMinLevel());
    model.setState(State.IDLE);
  }
  
  @Override
  public void talkTo() {

  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    model.increaseLevel(Level.DIRTINESS, DIRTINESS_CHANGE);
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    if(model.getLevel(Level.DIRTINESS) >= model.getMaxLevel()) {
      model.setState(State.SICK);
    }
  }
  
}
