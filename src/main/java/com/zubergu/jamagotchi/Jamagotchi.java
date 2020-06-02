package com.zubergu.jamagotchi;

/**
* Starter class
*/
public class Jamagotchi {
  
  public static void main(String[] args) {
    AbstactAnimalModel model;
    
    /* retrieve saved animal if possible here */
    SwingUtilities.invokeAndWait( new Runnable() {
      public void run() {
        StartScreen firstScreen = new StartScreen(model);
        firstScreen.start();
      }
    });
    
    /* all startup code goes to event dispatch thread */
    SwingUtilities.invokeLater(new Runnable()  {
      public void run() {
        MainView view = new MainView();
        ControllerInterface controller = new AnimalController(model, view);
        mainView.start();
      }
  
    });
  }

}
