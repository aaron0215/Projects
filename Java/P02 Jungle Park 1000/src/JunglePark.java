// Title: Jungle Park 1000
// Files: JunglePark.java
// Course: COMP SCI 300, Fall 2018
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
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
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

import java.util.Random;

public class JunglePark {
  private static PApplet processing; // PApplet object that represents the graphic
                                     // interface of the JunglePark application

  private static PImage backgroundImage; // PImage object that represents the
                                         // background image

  private static Tiger[] tigers; // array storing the current tigers present
                                 // in the Jungle Park

  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    // Auto-generated method stub
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {

    processing = processingObj; // initialize the processing field to the one passed into
                                // the input argument parameter

    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    tigers = new Tiger[8]; // initialize a array of tigers with capacity 8
    randGen = new Random(); // create a Random object and store its reference in randGen

  }

  /**
   * Defines the callback update method of the application Initialize background and draw all
   * existed Tiger objects if created
   */
  public static void update() {

    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color

    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    // Go through the tigers array and draw all of them if existed
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        tigers[i].draw();
      }
    }
  }

  /**
   * Defines the isMouseOver method
   * 
   * @param tiger represents a Tiger object
   * @return the boolean result if the mouse is over the selected object
   */
  public static boolean isMouseOver(Tiger tiger) {

    // return variable
    boolean result = false;

    // Image (object) size
    int width = tiger.getImage().width;
    int height = tiger.getImage().height;

    // Mouse coordinate
    float centerX = tiger.getPositionX();
    float centerY = tiger.getPositionY();

    // Determination
    if (processing.mouseX >= centerX - width / 2 && processing.mouseX <= centerX + width / 2) {
      if (processing.mouseY >= centerY - height / 2 && processing.mouseY <= centerY + height / 2) {
        result = true;
      }
    } else {
      result = false;
    }
    return result;
  }

  /**
   * Defines the mouseDown method Determine which tiger object that the mouse is over when clicked
   * and enable user to drag it
   */
  public static void mouseDown() {
    int draggingCount = 0; // Prevent program from dragging two tigers

    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) { // Avoid null pointer error
        if (isMouseOver(tigers[i]) && draggingCount == 0) {
          tigers[i].setDragging(true);
          draggingCount += 1;
        }
      }
    }
  }

  /**
   * Defines the mouseUp method Determine which tiger object that the mouse is over when clicked and
   * disable user to drag it
   */
  public static void mouseUp() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) { // Avoid null pointer error
        if (isMouseOver(tigers[i])) {
          tigers[i].setDragging(false);
        }
      }
    }
  }

  /**
   * Defines the keyPressed method Will be called when user press on certain keys and enable program
   * to add or remove certain objects from the interface
   */
  public static void keyPressed() {
    int emptyIndex = -1;
    int pressCount = 0; // Prevent the program from adding or removing more than one tiger in each
                        // time call

    if (processing.key == 'T' || processing.key == 't') {
      for (int index = 0; index < tigers.length; index++) {
        if (pressCount == 0 && tigers[index] == null) { // Add new object only if the array has
                                                        // space
          emptyIndex = index;
          pressCount += 1;
          // Test
          System.out.println("One tiger is added at index " + index + " of the array");
        }
      }
      float width = (float) randGen.nextInt(processing.width);
      float height = (float) randGen.nextInt(processing.height);
      if (emptyIndex != -1) {
        tigers[emptyIndex] = new Tiger(processing, width / 2, height / 2);
      }
    }

    else if (processing.key == 'R' || processing.key == 'r') {
      for (int index = 0; index < tigers.length; index++) {
        if (pressCount == 0 && tigers[index] != null) { // Remove object only if the object is
                                                        // existed
          if (isMouseOver(tigers[index])) {
            tigers[index] = null; // Remove existed item
            pressCount += 1;
            // Test
            System.out.println("One tiger is removed from index " + index + " of the array");
          }
        }
      }
    } else {
      // Do nothing
    }
  }

}
