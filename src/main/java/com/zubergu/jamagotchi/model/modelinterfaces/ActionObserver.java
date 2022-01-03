package com.zubergu.jamagotchi.model.modelinterfaces;

import com.zubergu.jamagotchi.model.animalmodel.Action;

/**
*
*/
public interface ActionObserver {
    public void notifyOfAction( Action action );
}
