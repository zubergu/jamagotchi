package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.modelinterfaces.LevelsObserver;
import com.zubergu.jamagotchi.model.AbstractCreatureModel;
import com.zubergu.jamagotchi.controller.ICreatureController;
import com.zubergu.jamagotchi.model.State;
import com.zubergu.jamagotchi.gui.animation.AnimationPanel;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
* Main application window.
*/
public class MainView implements LevelsObserver {

  private static final int SCREEN_SIZE_X = 800;
  private static final int SCREEN_SIZE_Y = 600;

  private ICreatureController creatureController;
  private AbstractCreatureModel model;
  
  private JFrame mainFrame;
  
  private JLabel currentStateLabel;
  private JButton playButton;
  private JButton feedButton;
  private JButton petButton;
  private JButton cleanButton;
  private JButton takeToVetButton;
  private JButton talkToButton;
  private JButton wakeUpButton;
  
  private LevelBar healthBar;
  private LevelBar hungerBar;
  private LevelBar joyBar;
  private LevelBar energyBar;
  private LevelBar dirtinessBar;
  private LevelBar angerBar;
  private LevelBar boredomBar;
  
  private JPanel buttonsPanel;
  
  private JPanel statsPanel;
  
  private AnimationPanel animationPanel;

  public MainView() {
    mainFrame = new JFrame("Jamagotchi");
    playButton = new JButton("Play with");
    feedButton = new JButton("Feed");
    petButton = new JButton("Pet");
    cleanButton = new JButton("Clean");
    takeToVetButton = new JButton("Take to vet");
    talkToButton = new JButton("Talk to");
    wakeUpButton = new JButton("Wake up");
    
    currentStateLabel = new JLabel("IDLE");
    
    healthBar = new LevelBar("Health");
    hungerBar = new LevelBar("Hunger");
    joyBar = new LevelBar("Joy");
    energyBar = new LevelBar("Energy");
    dirtinessBar  = new LevelBar("Dirty");
    boredomBar  = new LevelBar("Bored");
    angerBar = new LevelBar("Anger");
    
    buttonsPanel = new JPanel(new GridLayout(0,1));
    statsPanel  = new JPanel(new GridLayout(1,0));
    animationPanel = new AnimationPanel();
  }
  
  /*
  * gui initialization and start point
  */
  public void start() {
  
    /* put buttons in correct order here */
    /* top to bottom */
    buttonsPanel.add(playButton);
    buttonsPanel.add(petButton);
    buttonsPanel.add(talkToButton);
    buttonsPanel.add(feedButton);
    buttonsPanel.add(cleanButton);
    buttonsPanel.add(wakeUpButton);
    buttonsPanel.add(takeToVetButton);
    
    
    /* put level bars in correct order here */
    /* left to right */
    
    statsPanel.add(currentStateLabel);
    statsPanel.add(healthBar);
    statsPanel.add(hungerBar);
    statsPanel.add(energyBar);
    statsPanel.add(joyBar);
    statsPanel.add(boredomBar);
    statsPanel.add(dirtinessBar);
    statsPanel.add(angerBar);
    
    
    /* put panels in main frame */
    mainFrame.getContentPane().add( buttonsPanel, BorderLayout.WEST );
    mainFrame.getContentPane().add( statsPanel, BorderLayout.SOUTH );
    mainFrame.getContentPane().add( animationPanel, BorderLayout.CENTER );
    
    
    
    /* set actions for components */
    playButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.playWith();
      }
    });
    
    feedButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.feed();
      }
    });
    
    petButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.pet();
      }
    });
    
    cleanButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.clean();
      }
    });
    
    takeToVetButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.takeToVet();
      }
    });
    
    talkToButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.talkTo();
      }
    });
        
    wakeUpButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        creatureController.wakeUp();
      }
    });
      
      
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        creatureController.shutdownActions();
      }
    });
    
    
    
    
    /* try to save model when closing */
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    mainFrame.setSize(SCREEN_SIZE_X, SCREEN_SIZE_Y);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
  }
  
  @Override
  public void updateOnChange(int hunger, int health, int dirtiness, int energy, int boredom, int joy, int anger) {
    hungerBar.setValue(hunger);
    healthBar.setValue(health);
    dirtinessBar.setValue(dirtiness);
    energyBar.setValue(energy);
    boredomBar.setValue(boredom);
    joyBar.setValue(joy);
    angerBar.setValue(anger);
  }
  
  public void setCreatureController( ICreatureController controller) {
    this.creatureController = controller;
  }
  
  public void setStateLabel(String newState) {
    currentStateLabel.setText(newState);
  }
  
  public AnimationPanel getAnimationPanel() {
      return animationPanel;
  }

}
