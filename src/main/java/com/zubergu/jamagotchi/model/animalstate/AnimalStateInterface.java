package com.zubergu.jamagotchi.model.animalstate;

import java.io.Serializable;

/**
* Interface for animal state.
*/
public interface AnimalStateInterface extends Serializable {

  public void pet();
  public void feed();
  public void takeToVet();
  public void playWith();
  public void stopPlaying();
  public void clean();
  public void talkTo();
  public void wakeUp();
  public void tick();
  
}
