package com.zubergu.jamagotchi.controller;


import com.zubergu.jamagotchi.model.AbstractCreatureModel;
import com.zubergu.jamagotchi.gui.swinggui.MainView;
import com.zubergu.jamagotchi.model.modelinterfaces.StateObserver;
import com.zubergu.jamagotchi.model.State;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
*
*/
public class CreatureController implements ICreatureController, StateObserver {

  private AbstractCreatureModel animal;
  private MainView view;
  private SoundController soundController;
  private AnimationController animationController;

  public CreatureController( AbstractCreatureModel model, MainView view, SoundController soundController, AnimationController animationController ) {
    this.animal = model;
    this.view = view;
    this.soundController = soundController;
    this.animationController = animationController;
    this.animationController.setAnimationPanel( view.getAnimationPanel() );

    
    /* set up observers for model */
    animal.registerStateObserver( this );    
    animal.registerLevelsObserver( view );
    animal.registerActionObserver( soundController );
    animal.registerStateObserver( soundController );
    animal.registerStateObserver( animationController );
    animal.registerActionObserver( animationController );
  }
  
  public void playWith() {
    animal.playWith();
  }
  
  public void stopPlaying() {
    animal.stopPlaying();
  }
  
  public void pet() {
    animal.pet();
  }
  
  public void feed() {
    animal.feed();
  }
  
  public void clean() {
    animal.clean();
  }
  
  public void takeToVet() {
    animal.takeToVet();
  }
  
  public void talkTo() {
    animal.talkTo();
  }
  
  public void wakeUp() {
    animal.wakeUp();
  }
  
  /* set timer for updating model statistics:
    call animal.tick() periodically */
  @Override
  public void startTicking() {
      Timer timer = new Timer( 1000, new ActionListener() {
              
          @Override
          public void actionPerformed( ActionEvent ev ) {
              animal.tick();
          }
      });
      
      timer.start();
  }
  
  /* when state changes there should be update in view done from here */
  public void updateOnStateChange( State state ) {
    view.setStateLabel( state.toString() );
  }
  
  /* actions to be taken when user closes the application */
  public void shutdownActions() {
    // save the model and 
    String fileName = animal.getName() + ".jmg";
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
      oos.writeObject(animal);
      oos.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    System.exit(0);
  }

}
