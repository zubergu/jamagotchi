package com.zubergu.jamagotchi.model.modelinterfaces;

import com.zubergu.jamagotchi.model.Action;

/**
*
*/
public interface ActionObserver {
    public void notifyOfAction( Action action );
}
