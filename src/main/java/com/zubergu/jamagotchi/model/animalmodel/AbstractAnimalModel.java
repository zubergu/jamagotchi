package com.zubergu.jamagotchi.model.animalmodel;

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
  private HashMap<Level, Integer> levels;
  
  /* state of model */
  private AnimalStateInterface state;
  private HashMap<State, AnimalStateInterface> states;
  
  /* observers */
  private ArrayList<LevelsObserver> levelsObservers = new ArrayList<LevelsObserver>();
  private ArrayList<StateObserver> stateObservers  = new ArrayList<StateObserver>();
  
  
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
    states.put(State.ANGRY, new AngryState());
    
    // set initial current state
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
      ob.updateOnChangeState(state);
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
  
  public void setState(State newState) {
    state = states.get(newState);
    notifyStateObservers(state);
  }
  
  public int getLevel(Level level) {
    return levels.get(level);
  }
  
  
  public void setLevel(Level level, int newValue) {
    if(newValue > MAX_LEVEL) {
      newValue = MAX_LEVEL;
    }else if(newValue < MIN_LEVEL) {
      newValue = MIN_LEVEL;
    }
    
    levels.put(level, newValue);
    
    notifyLevelsObservers( levels.get(Level.HUNGER),
                           levels.get(Level.HEALTH),
                           levels.get(Level.DIRTINESS),
                           levels.get(Level.ENERGY),
                           levels.get(Level.BOREDOM),
                           levels.get(Level.JOY),
                           levels.get(Level.ANGER)
                           );
                            
    )
  }
  
  public void increaseLevel(Level level, value) {
    setLevel(level, levels.get(level) + value);
  }
  
  public void decreaseLevel(Level level, value) {
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

}
