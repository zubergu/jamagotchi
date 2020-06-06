package com.zubergu.jamagotchi.gui.swinggui;

import javax.swing.JProgressBar;



public class LevelBar extends JProgressBar {
  
  private String title;

  public LevelBar(String title) {
    this.title = title;
    setMaximum(100);
    setStringPainted(true);
    setString(title);
  }

}
