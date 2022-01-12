package com.zubergu.jamagotchi.model.dog;

import com.zubergu.jamagotchi.model.state.*;
import com.zubergu.jamagotchi.model.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class DogTest {

  private static final String DOG_NAME = "dogName";

  @Test
  public void dogInitializationTest() {
    AbstractCreatureModel dog = new Dog(DOG_NAME);
    
    assertEquals(dog.getLevel(Level.HUNGER), dog.getMinLevel());
    assertEquals(dog.getLevel(Level.ANGER), dog.getMinLevel());
    assertEquals(dog.getLevel(Level.DIRTINESS), dog.getMinLevel());
    assertEquals(dog.getLevel(Level.JOY), dog.getMaxLevel());
    assertEquals(dog.getLevel(Level.BOREDOM), dog.getMinLevel());
    assertEquals(dog.getLevel(Level.HEALTH), dog.getMaxLevel());
    assertEquals(dog.getLevel(Level.ENERGY), dog.getMaxLevel());
    assertEquals(dog.getName(), DOG_NAME);
    assertTrue(dog.getCurrentState() instanceof IdleState );
    
  }
  
  
  @Test
  public void setStateTest() {
    AbstractCreatureModel dog = new Dog(DOG_NAME);
    dog.setState(State.ANGRY);
    assertTrue(dog.getCurrentState() instanceof AngryState);
  }
  
  @Test
  public void setAndIncreaseLevelTest() {
    AbstractCreatureModel dog = new Dog(DOG_NAME);
    dog.setLevel(Level.HUNGER, 50);
    dog.increaseLevel(Level.HUNGER, 20);
    assertEquals(dog.getLevel(Level.HUNGER), 70);
  }
  
  @Test
  public void setAndDecreaseLevelTest() {
    AbstractCreatureModel dog = new Dog(DOG_NAME);
    dog.setLevel(Level.HUNGER, 50);
    dog.decreaseLevel(Level.HUNGER, 20);
    assertEquals(dog.getLevel(Level.HUNGER), 30);
  }
  
  @Test
  public void setLevelWorksWithinLimits() {
    AbstractCreatureModel dog = new Dog(DOG_NAME);
    dog.setLevel(Level.HUNGER, dog.getMaxLevel() + 20);
    dog.setLevel(Level.ANGER, dog.getMinLevel() - 20);
    
    assertEquals(dog.getLevel(Level.HUNGER), dog.getMaxLevel());
    assertEquals(dog.getLevel(Level.ANGER), dog.getMinLevel());
  }
  
  @Test
  public void testPlayWith() {

  }
  
  @Test
  public void testStopPlaying() {
  }
  
  @Test
  public void testTick() {
  }
  
  @Test
  public void testPet() {
  }
  
  @Test
  public void testFeed() {
  }
  
  @Test
  public void testClean() {
    
  }
  
  @Test
  public void testTakeToVet() {
  }
  
  @Test
  public void testTalkTo() {
  }
  
  @Test
  public void testWakeUp() {
  }

}
