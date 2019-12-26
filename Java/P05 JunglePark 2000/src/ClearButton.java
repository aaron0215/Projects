// Title: JunglePark 2000
// Files: AddAnimalButton.java Animal.java Button.java ClearButton.java Deer.java JunglePark.java
//        JunglePartTest.java ParkGUI.java Tiger.java
// Course: CS300 Fall 2018
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE

/**
 * This is a sub class represents ClearButton with inherited variables from super class
 * ClearButton extends Button class
 * 
 * @author Aaron Zhang
 */
public class ClearButton extends Button {
  
  /**
   * Constructor. Create a new clear button object with given position and GUI
   * @param x is the x-coordinate
   * @param y is the y-coordinate
   * @param park is the current GUI
   */
  public ClearButton(float x, float y, JunglePark park) {
    super(x, y, park);
    this.label = "Clear";
    processing = park;
  }

  /**
   * This method determines if the button is clicked
   * If so, clear all objects and release the mouse after finishing it
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      processing.clear();
      processing.mouseReleased();
    }
  }
}
