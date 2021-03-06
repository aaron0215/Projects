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
 * This is a sub class represents an AddAnimalButton object with a string type
 * This class extends Button class
 * 
 * @author Mouna Kacem, Aaron Zhang
 */
public class AddAnimalButton extends Button{
  private String type; // type of the animal to add
  
  /**
   * Constructor. Create a new add animal button with given animal type and position
   * @param type is the animal type that you want to create
   * @param x is the x-coordinate
   * @param y is the y-coordinate
   * @param park is the current GUI
   */
  public AddAnimalButton(String type, float x, float y, JunglePark park) {
      super(x, y, park);
      this.type = type.toLowerCase();
      this.label = "Add " + type;
      processing = park;
  }

  /**
   * This method detects if this button is pressed
   * 
   * @Override the mousePressed() method in super class
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {    // mouse is over the button
      switch (type) {
        case "tiger":
          //create a new Tiger and add it to the JunglePark and release the mouse
          processing.listGUI.add(new Tiger(processing));
          processing.mouseReleased();
          break;
        case "deer":
          //create a new Deer and add it to the JunglePark and release the mouse
          processing.listGUI.add(new Deer(processing));
          processing.mouseReleased();
          break;
      }
    }
  }
}
