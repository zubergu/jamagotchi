package com.zubergu.jamagotchi;

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
    SwingUtilities.invokeAndWait(ssRunner);
    
    // at this point either correct model is read by ssRunner or program quit
    model = ssRunner.getModel();
    
    /* all startup code goes to event dispatch thread */
    mvRunner.setModel(model);
    SwingUtilities.invokeLater(mvRunner);
  }

}
