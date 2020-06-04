package com.zubergu.jamagotchi.model.modelinterfaces;


import com.zubergu.jamagotchi.model.animalstate.AnimalStateInterface;
/**
*
*/
public interface StateObserver {
  public void updateOnStateChange(AnimalStateInterface state);
}
