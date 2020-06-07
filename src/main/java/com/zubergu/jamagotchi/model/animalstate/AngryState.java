package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Angry state.
*/
public class AngryState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  
  private AbstractAnimalModel model;
  
  private int angryTickCounter = 0;

  public AngryState(AbstractAnimalModel model) {
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
    angryTickCounter = 0;
    model.setState(State.IDLE);
  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    angryTickCounter++;
    if(angryTickCounter > 200) {
      angryTickCounter = 0;
      model.setState(State.SICK);
    }
  }
  
}
