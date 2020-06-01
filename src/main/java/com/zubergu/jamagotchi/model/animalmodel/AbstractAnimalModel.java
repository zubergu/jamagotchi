package com.zubergu.jamagotchi.model.animalmodel;

/**
*
*/
public abstract class AbstractAnimalModel {
  
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
  
  /* state of model */
  private AnimalStateInterface state;
  private HashMap<State, AnimalStateInterface> states;
  
  /* observers */
  private ArrayList<LevelsObserver> levelsObservers = new ArrayList<LevelsObserver>();
  private ArrayList<StateObserver> stateObservers  = new ArrayList<StateObserver>();
  
  
  /* constructor */
  public AbstractAnimalModel(String name) {
    this.name = name;
    hunger = MIN_LEVEL;
    health = MAX_LEVEL;
    dirtiness = MIN_LEVEL;
    energy = MAX_LEVEL;
    boredom = MIN_LEVEL;
    joy = MAX_LEVEL;
    anger = MIN_LEVEL;
    
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
    states.put(State.ANGRY, new AngryState());
    
    state = state.get(State.IDLE);
  }
  
  
  /* observer interface */
  public void registerObserver(StateObserver observer) {
    stateObservers.add(observer);
  }
  
  public void registerObserver(LevelsObserver observer) {
    levelsObservers.add(observer);
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
    for(LevelsObserver ob: levelsObservers) {
      ob.updateOnChange(hunger, health, dirtiness,energy,boredom,joy, anger);
    }
  }
  
  private void notifyStateObservers() {
    for(StateObserver ob: stateObservers) {
      ob.updateOnChangeState();
    }
  }
  
  public void playWith() {
    state.playWith();
    notifyLevelsObservers();
  }
  
  public void stopPlaying() {
    state.stopPlaying();
  }
  
  public void tick() {
    state.tick();
    notifyLevelsObservers();
  }
  
  public void pet() {
    state.pet();
    notifyLevelsObservers();
  }
  
  public void feed() {
    state.feed();
    notifyLevelsObservers();
  }
  
  public void clean() {
    state.clean();
    notifyLevelsObservers();
  }
  
  public void takeToVet() {
    state.takeToVet();
    notifyLevelsObservers();
  }
  
  public void talkTo() {
    state.talkTo();
    notifyLevelsObservers();
  }
  
  public void wakeUp() {
    state.wakeUp();
    notifyLevelsObservers();
  }
  

}
