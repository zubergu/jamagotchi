package com.zubergu.jamagotchi.model;


import com.zubergu.jamagotchi.model.modelinterfaces.LevelsObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.modelinterfaces.ActionObserver;
import com.zubergu.jamagotchi.model.state.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
*
*/
public abstract class AbstractCreatureModel implements Serializable {
  
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
  private ICreatureState state;
  private HashMap<State, ICreatureState> states = new HashMap<State, ICreatureState>();
  private HashMap<ICreatureState, State> reverseStates = new HashMap<ICreatureState, State>();
  
  /* observers */
  private transient ArrayList<LevelsObserver> levelsObservers = new ArrayList<LevelsObserver>();
  private transient ArrayList<StateObserver> stateObservers = new ArrayList<StateObserver>();
  private transient ArrayList<ActionObserver> actionObservers = new ArrayList<ActionObserver>();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
  
  public AbstractCreatureModel() {
    // serialization made me do it
  }
  
  /* constructor */
  public AbstractCreatureModel(String name) {
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
    
    reverseStates.put( states.get(State.TIRED), State.TIRED );
    reverseStates.put( states.get(State.BORED), State.BORED );
    reverseStates.put( states.get(State.PLAYING), State.PLAYING );
    reverseStates.put( states.get(State.IDLE), State.IDLE );
    reverseStates.put( states.get(State.HUNGRY), State.HUNGRY );
    reverseStates.put( states.get(State.DEAD), State.DEAD );
    reverseStates.put( states.get(State.DIRTY), State.DIRTY );
    reverseStates.put( states.get(State.SICK), State.SICK );
    reverseStates.put( states.get(State.SLEEPING), State.SLEEPING );
    reverseStates.put( states.get(State.SEEKING_ATTENTION), State.SEEKING_ATTENTION );
    reverseStates.put( states.get(State.ANGRY), State.ANGRY );
    
    // set initial current state
    state = states.get(State.IDLE);
  }
  
  
  /* observer interface */
  public void registerStateObserver(StateObserver observer) {
    if(stateObservers == null) {
      System.out.println("stateObservers is null");
    }
    stateObservers.add(observer);
    notifyStateObservers();
  }
  
  public void registerLevelsObserver(LevelsObserver observer) {
    if(stateObservers == null) {
      System.out.println("levelsObservers is null");
    }
    levelsObservers.add(observer);
    notifyLevelsObservers();
  }
  
  public void registerActionObserver( ActionObserver observer ) {
    if(actionObservers == null) {
      System.out.println( "actionObservers is null");
    }
    actionObservers.add( observer );
  }
  
  public void removeStateObserver(StateObserver observer) {
    int index = stateObservers.indexOf(observer);
    if(index >= 0) {
      stateObservers.remove(index);
    }
  }
  
  public void removeLevelsObserver(LevelsObserver observer) {
    int index = levelsObservers.indexOf(observer);
    if(index >= 0) {
      levelsObservers.remove(index);
    }
  }

  public void removeActionObserver(ActionObserver observer) {
    int index = actionObservers.indexOf(observer);
    if(index >= 0) {
      actionObservers.remove(index);
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
      ob.updateOnStateChange( reverseStates.get( state ) );
    }
  }
  
  private void notifyActionObservers( Action action ) {
      for( ActionObserver ob: actionObservers ) {
          ob.notifyOfAction( action );
      }
  }
  
  public void playWith() {
    notifyActionObservers( Action.PLAY );
    state.playWith();
  }
  
  public void stopPlaying() {
    state.stopPlaying();
  }
  
  public void tick() {
    state.tick();
  }
  
  public void pet() {
    notifyActionObservers( Action.PET );
    state.pet();
  }
  
  public void feed() {
    notifyActionObservers( Action.FEED );   
    state.feed();
  }
  
  public void clean() {
    notifyActionObservers( Action.CLEAN );      
    state.clean();
  }
  
  public void takeToVet() {
    notifyActionObservers( Action.TAKE_TO_VET );
      
    state.takeToVet();
  }
  
  public void talkTo() {
    notifyActionObservers( Action.TALK_TO );
     
    state.talkTo();
  }
  
  public void wakeUp() {
    notifyActionObservers( Action.WAKE_UP );
    state.wakeUp();
  }
  
  public void setState( State newState ) {
    state = states.get(newState);
    notifyStateObservers();
  }
  
  public int getLevel( Level level ) {
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
  
  public ICreatureState getState(State state) {
    return states.get(state);
  }
  
  public ICreatureState getCurrentState() {
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
    actionObservers = new ArrayList<ActionObserver>();
  }

}
