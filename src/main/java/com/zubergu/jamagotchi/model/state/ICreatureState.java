package com.zubergu.jamagotchi.model.state;

import java.io.Serializable;

/**
* Interface for creature state.
*/
public interface ICreatureState extends Serializable {

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
