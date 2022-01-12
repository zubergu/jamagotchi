package com.zubergu.jamagotchi.model.dog;

import java.io.Serializable;

import com.zubergu.jamagotchi.model.AbstractCreatureModel;

/**
*  Implementation of a dog.
*/
public class Dog extends AbstractCreatureModel implements Serializable {

  
  public Dog( String name ) {
    super(name);
  }
  
}
