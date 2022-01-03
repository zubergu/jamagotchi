package com.zubergu.jamagotchi.model.modelinterfaces;


import com.zubergu.jamagotchi.model.animalmodel.State;
/**
*
*/
public interface StateObserver {
  public void updateOnStateChange( State state );
}
