package com.zubergu.jamagotchi.gui.swinggui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Window;

import com.zubergu.jamagotchi.model.animalmodel.Dog;
import com.zubergu.jamagotchi.model.animalmodel.AbstractAnimalModel;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

/**
* Screen for loading saved model or creating new one. 
* Extends JDialog to be modal( wait for closing before proceeding).
*/
public class StartScreen extends JDialog {
  private AbstractAnimalModel model;
  
  public StartScreen() {
    super((Window) null);
    setModalityType(ModalityType.APPLICATION_MODAL);
    setModal(true);
  }
  
  
  public void start() {
    JFileChooser fileChooser = new JFileChooser();
    JPanel mainPanel = new JPanel( new GridLayout(0,2));
    JLabel nameLabel = new JLabel("Name");
    JTextField textField = new JTextField("");
    JButton newButton = new JButton("New");
    JButton loadButton = new JButton("Load");
    JButton quitButton = new JButton("Quit");
    
    loadButton.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        int returnVal = fileChooser.showOpenDialog(StartScreen.this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            File file = fileChooser.getSelectedFile();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object ob = ois.readObject();
            if(ob instanceof AbstractAnimalModel) {
              model = (AbstractAnimalModel) ob;
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
          
        }
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
        System.exit(0);
      }
    });
  
  }
  
  public AbstractAnimalModel getModel() {
    return model;
  }
  
}
