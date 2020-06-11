package com.zubergu.jamagotchi.gui.swinggui;

import com.zubergu.jamagotchi.model.modelinterfaces.LevelsObserver;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;
import com.zubergu.jamagotchi.controller.ControllerInterface;
import com.zubergu.jamagotchi.model.animalmodel.State;

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

  private ControllerInterface controller;
  private AbstractAnimalModel model;
  
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
  
  private JPanel imagePanel;

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
    imagePanel = new JPanel();
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
    mainFrame.getContentPane().add(buttonsPanel, BorderLayout.WEST);
    mainFrame.getContentPane().add(statsPanel, BorderLayout.SOUTH);
    mainFrame.getContentPane().add(imagePanel, BorderLayout.CENTER);
    
    
    
    /* set actions for components */
    playButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.playWith();
      }
    });
    
    feedButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.feed();
      }
    });
    
    petButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.pet();
      }
    });
    
    cleanButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.clean();
      }
    });
    
    takeToVetButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.takeToVet();
      }
    });
    
    talkToButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.talkTo();
      }
    });
        
    wakeUpButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        controller.wakeUp();
      }
    });
      
      
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        controller.shutdownActions();
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
  
  public void setController(ControllerInterface controller) {
    this.controller = controller;
  }
  
  public void setStateLabel(String newState) {
    currentStateLabel.setText(newState);
  }

}
