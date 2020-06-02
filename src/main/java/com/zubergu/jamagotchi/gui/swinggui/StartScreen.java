package com.zubergu.jamagotchi.gui.swinggui;

/**
* Screen for loading saved model or creating new one. 
* Extends JDialog to be modal( wait for closing before proceeding).
*/
public class StartScreen extends JDialog {
  private AbstractAnimalModel model;
  
  public StartScreen(AbstractAnimalModel model) {
    super((Window) null);
    this.model = model;
    setModalityType(ModalityType.APPLICATION_MODAL);
    setModal(true);
  }
  
  
  public void start() {
  
    JPanel mainPanel = new JPanel( new GridLayout(0,2));
    JLabel nameLabel = new JLabel("Name");
    JTextField textField = new JTextField("");
    JButton newButton = new JButton("New");
    JButton loadButton = new JButton("Load");
    JButton quitButton = new JButton("Quit");
    
    loadButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        // open new dialog here
      }
    });
    
    newButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        // creating new animal model
        if(!textField.getText().equals("")) {
          model = new Dog(textField.getText());
          setVisible(false);
          dispose();
        }
      }
    });
    
    quitButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        setVisible(false);
        dispose();
        System.exit(0);
      }
    });
  
  }
  
}
