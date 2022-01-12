package com.zubergu.jamagotchi.model.state;

import com.zubergu.jamagotchi.model.Level;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.AbstractCreatureModel;


/**
* Implementation of animal behaviour in Playing state.
*/
public class PlayingState implements ICreatureState {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  private static final int BOREDOM_CHANGE = 3;
  private AbstractCreatureModel model;

  public PlayingState(AbstractCreatureModel model) {
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
    model.setState(State.IDLE);
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
    model.decreaseLevel(Level.ENERGY, ENERGY_CHANGE);
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    model.decreaseLevel(Level.BOREDOM, BOREDOM_CHANGE);
    model.increaseLevel(Level.JOY, JOY_CHANGE);
    if(model.getLevel(Level.ENERGY) <= model.getMaxLevel()/4) {
      model.setState(State.IDLE);
    }
  }
  
}
