package com.zubergu.jamagotchi.model.animalmodel;


import com.zubergu.jamagotchi.model.modelinterfaces.LevelsObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.animalstate.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
*
*/
public abstract class AbstractAnimalModel implements Serializable {
  
  /* constants for model */
  private static final int MAX_LEVEL = 100;
  private static final int MIN_LEVEL = 0;
  
  /* current levels */
  private String name;
  private int hunger;
  private int health;
  private int dirtiness;
  private int energy;
  private int boredom;
  private int joy;
  private int anger;
  private HashMap<Level, Integer> levels = new HashMap<Level, Integer>();
  
  /* state of model */
  private AnimalStateInterface state;
  private HashMap<State, AnimalStateInterface> states = new HashMap<State, AnimalStateInterface>();
  
  /* observers */
  private transient ArrayList<LevelsObserver> levelsObservers = new ArrayList<LevelsObserver>();
  private transient ArrayList<StateObserver> stateObservers = new ArrayList<StateObserver>();
  
  public AbstractAnimalModel() {
    // serialization made me do it
  }
  
  /* constructor */
  public AbstractAnimalModel(String name) {
    this.name = name;
    
    levels.put(Level.HUNGER, MIN_LEVEL);
    levels.put(Level.HEALTH, MAX_LEVEL);
    levels.put(Level.DIRTINESS, MIN_LEVEL);
    levels.put(Level.ENERGY, MAX_LEVEL);
    levels.put(Level.BOREDOM, MIN_LEVEL);
    levels.put(Level.JOY, MAX_LEVEL);
    levels.put(Level.ANGER, MIN_LEVEL);
    
    states.put(State.TIRED, new TiredState(this));
    states.put(State.BORED, new BoredState(this));
    states.put(State.PLAYING, new PlayingState(this));
    states.put(State.IDLE, new IdleState(this));
    states.put(State.HUNGRY, new HungryState(this));
    states.put(State.DEAD, new DeadState(this));
    states.put(State.DIRTY, new DirtyState(this));
    states.put(State.SICK, new SickState(this));
    states.put(State.SLEEPING, new SleepingState(this));
    states.put(State.SEEKING_ATTENTION, new SeekingAttentionState(this));
    states.put(State.ANGRY, new AngryState(this));
    
    // set initial current state
    state = states.get(State.IDLE);
  }
  
  
  /* observer interface */
  public void registerObserver(StateObserver observer) {
    if(stateObservers == null) {
      System.out.println("stateObservers is null");
    }
    stateObservers.add(observer);
    notifyStateObservers();
  }
  
  public void registerObserver(LevelsObserver observer) {
      if(stateObservers == null) {
      System.out.println("levelsObservers is null");
    }
    levelsObservers.add(observer);
    notifyLevelsObservers();
  }
  
  public void removeObserver(StateObserver observer) {
    int index = stateObservers.indexOf(observer);
    if(index >= 0) {
      stateObservers.remove(index);
    }
  }
  
  public void removeObserver(LevelsObserver observer) {
    int index = levelsObservers.indexOf(observer);
    if(index >= 0) {
      levelsObservers.remove(index);
    }
  }
  
  private void notifyLevelsObservers() {
    hunger = getLevel(Level.HUNGER);
    health = getLevel(Level.HEALTH);
    dirtiness = getLevel(Level.DIRTINESS);
    energy = getLevel(Level.ENERGY);
    boredom = getLevel(Level.BOREDOM);
    joy = getLevel(Level.JOY);
    anger = getLevel(Level.ANGER);
    for(LevelsObserver ob: levelsObservers) {
      ob.updateOnChange(hunger, health, dirtiness, energy, boredom, joy, anger);
    }
  }
  
  private void notifyStateObservers() {
    for(StateObserver ob: stateObservers) {
      ob.updateOnStateChange(state);
    }
  }
  
  public void playWith() {
    state.playWith();
  }
  
  public void stopPlaying() {
    state.stopPlaying();
  }
  
  public void tick() {
    state.tick();
  }
  
  public void pet() {
    state.pet();
  }
  
  public void feed() {
    state.feed();
  }
  
  public void clean() {
    state.clean();
  }
  
  public void takeToVet() {
    state.takeToVet();
  }
  
  public void talkTo() {
    state.talkTo();
  }
  
  public void wakeUp() {
    state.wakeUp();
  }
  
  public void setState(State newState) {
    state = states.get(newState);
    notifyStateObservers();
  }
  
  public int getLevel(Level level) {
    return levels.get(level);
  }
  
  
  public void setLevel(Level level, int newValue) {
    if(newValue > MAX_LEVEL) {
      newValue = MAX_LEVEL;
    } else if(newValue < MIN_LEVEL) {
      newValue = MIN_LEVEL;
    }
    
    levels.put(level, newValue);
    
    notifyLevelsObservers();
  }
  
  public void increaseLevel(Level level, int value) {
    setLevel(level, levels.get(level) + value);
  }
  
  public void decreaseLevel(Level level, int value) {
    setLevel(level, levels.get(level) - value);
  }
  
  public AnimalStateInterface getState(State state) {
    return states.get(state);
  }
  
  public AnimalStateInterface getCurrentState() {
    return state;
  }
  
  public int getMaxLevel() {
    return MAX_LEVEL;
  }
  
  public int getMinLevel() {
    return MIN_LEVEL;
  }
  
  public String getName() {
    return name;
  }
  
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    levelsObservers = new ArrayList<LevelsObserver>();
    stateObservers = new ArrayList<StateObserver>();
}

}
