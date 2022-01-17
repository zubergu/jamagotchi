package com.zubergu.jamagotchi.model.state.basic;

import com.zubergu.jamagotchi.model.Level;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.AbstractCreatureModel;


/**
* Implementation of animal behaviour in Hungry state.
*/
public class HungryState implements ICreatureState {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  
  private AbstractCreatureModel model;
  
  private int hungryTickCounter = 0;

  public HungryState( AbstractCreatureModel model ) {
    this.model = model;
  }

  @Override
  public void pet() {
    //
  }
  
  @Override
  public void feed() {
    hungryTickCounter = 0;
    model.setLevel(Level.HUNGER, model.getMinLevel());
    model.increaseLevel(Level.DIRTINESS, 20);
    model.setState(State.IDLE);
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

  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    hungryTickCounter++;
    
    if(hungryTickCounter > 200) {
      hungryTickCounter = 0;
      model.setState(State.SICK);
    }
  }
  
}
