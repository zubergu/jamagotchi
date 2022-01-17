package com.zubergu.jamagotchi.model.state.basic;

import com.zubergu.jamagotchi.model.Level;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.model.AbstractCreatureModel;


/**
* Implementation of animal behaviour in Dead state.
*/
public class DeadState implements ICreatureState {

  private AbstractCreatureModel model;

  public DeadState( AbstractCreatureModel model ) {
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
    // nothing
  }
  
  @Override
  public void tick() {
    //
  }
  
}
