package com.zubergu.jamagotchi.model.modelinterfaces;

/**
*  Interface for observers of model levels change.
*/
public interface LevelsObserver {

  public void updateOnChange(int hunger, int health, int dirtiness, int energy, int boredom, int joy, int anger);
  
}
