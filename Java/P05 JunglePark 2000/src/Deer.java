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
 * This sub class is for any Deer objects which contains fixed SCAN_RANGE, IMAGE_FILE_NAME
 * and non-fixed fieds nextID, TYPE and id which determine the type of the object
 * Deer extends Animal class
 * 
 * @author Mouna Kacem, Aaron Zhang
 */
public class Deer extends Animal {
  private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the
                                             // neighborhood
  private static final String IMAGE_FILE_NAME = "images/deer.png";
  private static int nextID = 1; // class variable that represents the identifier of the next deer
                                 // to be created

  private static final String TYPE = "DR"; // A String that represents the deer type
  private final int id; // Deer's id: positive number that represents the order of the deer

  /**
   * Constructor that creates a new Deer object positioned at a random position of the display
   * window. It calls the constructor of Animal class and initialize fileds
   * 
   * @param processing
   */
  public Deer(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
  }

  /**
   * Checks if there is a threat (a Tiger for instance) at the neighborhood
   * 
   * @param scanRange is the integer that refers to the range of area to be scanned around it
   * @return foundThreat which represents if there is threat found nearby
   */
  public boolean scanForThreat(int scanRange) {
    boolean foundThreat = false;
    for (int i = 0; i < processing.listGUI.size(); i++) {
      if (processing.listGUI.get(i) instanceof Tiger) { // Determine if it is a Tiger object
        // Compare the Euclidean distance with scanRange
        // Downcast ParkGUI object to Animal object
        if(super.isClose((Animal) processing.listGUI.get(i), scanRange)) {
          foundThreat = true;
          break;
        }
      }
    }
    return foundThreat;
  }

  /**
   * Defines the behavior of a Deer object in the Jungle park
   */
  @Override
  public void action() {
    if(this.scanForThreat(SCAN_RANGE)) {
      this.processing.fill(0); // specify font color: black
      this.processing.text("THREAT!", this.getPositionX(), 
          this.getPositionY() - this.image.height / 2 - 4);
    }
  }
}
