package com.zubergu.jamagotchi;

import javax.swing.SwingUtilities;

import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;
import com.zubergu.jamagotchi.gui.swinggui.StartScreenRunner;
import com.zubergu.jamagotchi.gui.swinggui.MainViewRunner;

/**
* Starter class.
*/
public class Jamagotchi {
  
  public static void main(String[] args) {
    AbstractAnimalModel model;
    
    // gui threads 
    StartScreenRunner ssRunner = new StartScreenRunner();
    MainViewRunner mvRunner = new MainViewRunner();
    
    /* retrieve saved animal if possible here */
    try {
      SwingUtilities.invokeAndWait(ssRunner);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(0);
    }
    
    // at this point either correct model is read by ssRunner or program quit
    model = ssRunner.getModel();
    
    /* all startup code goes to event dispatch thread */
    mvRunner.setModel(model);
    
    
    SwingUtilities.invokeLater(mvRunner);

  }

}
