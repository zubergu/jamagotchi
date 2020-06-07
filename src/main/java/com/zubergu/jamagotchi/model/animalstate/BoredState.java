package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Bored state.
*/
public class BoredState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  private static final int BOREDOM_CHANGE = 1;

  private AbstractAnimalModel model;

  public BoredState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    model.decreaseLevel(Level.ANGER, ANGER_CHANGE * 50);
    model.decreaseLevel(Level.BOREDOM, BOREDOM_CHANGE * 10);
  }
  
  @Override
  public void feed() {
    model.decreaseLevel(Level.HUNGER, HUNGER_CHANGE);
  }
  
  @Override
  public void takeToVet() {
    //
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
    //
  }
  
  @Override
  public void talkTo() {
    model.decreaseLevel(Level.ANGER, ANGER_CHANGE * 20);
    model.decreaseLevel(Level.BOREDOM, BOREDOM_CHANGE * 10);
    if( model.getLevel(Level.BOREDOM) < (model.getMaxLevel()/4) ) {
      model.setState(State.IDLE);
    }
  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    model.increaseLevel(Level.HUNGER, HUNGER_CHANGE);
    model.decreaseLevel(Level.ENERGY, ENERGY_CHANGE);
    model.increaseLevel(Level.BOREDOM, BOREDOM_CHANGE);
    
    if(model.getLevel(Level.BOREDOM) >= model.getMaxLevel()) {
      model.setState(State.SEEKING_ATTENTION);
    }
    
  }
  
}
