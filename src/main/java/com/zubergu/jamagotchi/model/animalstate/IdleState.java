package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Idle state.
*/
public class IdleState implements AnimalStateInterface {

  private static final int ANGER_CHANGE  = 10;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int BOREDOM_CHANGE = 3;

  
  private AbstractAnimalModel model;

  public IdleState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    model.increaseLevel(Level.JOY, 20);
    model.decreaseLevel(Level.ANGER, 20);
    model.decreaseLevel(Level.BOREDOM, 5);
  }
  
  @Override
  public void feed() {
    model.decreaseLevel(Level.HUNGER, 20);
    model.increaseLevel(Level.DIRTINESS, 20);
    if(model.getLevel(Level.DIRTINESS) >= 100 ) {
      model.setState(State.DIRTY);
    }
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
    model.setState(State.PLAYING);
  }
  
  @Override
  public void stopPlaying() {
    //
  }
  
  @Override
  public void clean() {
    model.setLevel(Level.DIRTINESS, model.getMinLevel());
  }
  
  @Override
  public void talkTo() {
    model.increaseLevel(Level.JOY, 5);
    model.decreaseLevel(Level.ANGER, 5);
    model.decreaseLevel(Level.BOREDOM, 3);
  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    model.decreaseLevel(Level.ENERGY, ENERGY_CHANGE);
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    model.increaseLevel(Level.BOREDOM, BOREDOM_CHANGE);
    
    // change state conditions priority
    if(model.getLevel(Level.HUNGER) >= model.getMaxLevel()) {
      model.setState(State.HUNGRY);
    } else if (model.getLevel(Level.ENERGY) <= model.getMinLevel()/2) {
      model.setState(State.TIRED);
    } else if (model.getLevel(Level.BOREDOM) > model.getMaxLevel()/2) {
      model.setState(State.BORED);
    }
  }
  
}
