package com.zubergu.jamagotchi.model.modelinterfaces;


import com.zubergu.jamagotchi.model.State;
/**
*
*/
public interface StateObserver {
  public void updateOnStateChange( State state );
}
