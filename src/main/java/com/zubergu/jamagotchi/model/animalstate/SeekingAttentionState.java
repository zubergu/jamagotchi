package com.zubergu.jamagotchi.model.animalstate;

import com.zubergu.jamagotchi.model.animalmodel.Level;
import com.zubergu.jamagotchi.model.animalmodel.State;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;


/**
* Implementation of animal behaviour in Seeking attention state.
*/
public class SeekingAttentionState implements AnimalStateInterface {

  private static final int ANGER_CHANGE = 1;
  private static final int HUNGER_CHANGE = 2;
  private static final int ENERGY_CHANGE = 1;
  private static final int JOY_CHANGE = 3;
  
  
  
  private AbstractAnimalModel model;
  private int seekingAttentionTickCounter = 0;
  
  public SeekingAttentionState(AbstractAnimalModel model) {
    this.model = model;
  }

  @Override
  public void pet() {
    decreaseSeekAttCounter(20);
    
    if(seekingAttentionTickCounter == 0) {
      model.setState(State.IDLE);
    }
    
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
    seekingAttentionTickCounter = 0;
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
    decreaseSeekAttCounter(20);
    if(seekingAttentionTickCounter == 0) {
      model.setState(State.IDLE);
    }
  }
  
  @Override
  public void wakeUp() {
    // nothing
  }
  
  @Override
  public void tick() {
    seekingAttentionTickCounter++;
    
    if(seekingAttentionTickCounter > 200) {
      seekingAttentionTickCounter = 0;
      model.setState(State.SICK);
    }
    
  }
  
  
  private void decreaseSeekAttCounter(int value) {
    seekingAttentionTickCounter -= value;
    if(seekingAttentionTickCounter < 0 ) {
      seekingAttentionTickCounter = 0;
    }
  }
  
}
