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
 * This class represents a Tiger in the JunglePark application
 *
 */
public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for
                                             // food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger
  private int deerEatenCount; // The number of deers that this tiger has eaten so far


  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
  }

  /**
   * Returns the number of deers that this tiger has eaten when called
   * 
   * @return deerEatenCount which represents the number mentioned above
   */
  public int getDeerEatenCount() {
    return deerEatenCount;
  }

  /**
   * This method enables the tiger to hop on deers nearby
   * 
   * @param food represents the deer nearby
   */
  public void hop(Deer food) {
    // Take the position of the food straight away
    this.setPositionX(food.getPositionX());
    this.setPositionY(food.getPositionY());
    // Locate and remove the food object from the ArrayList and GUI
    for (int i = 0; i < processing.listGUI.size(); i++) {
      if (processing.listGUI.get(i) instanceof Deer) {
        // Find the index of the food object in the ArrayList by comparing label
        if (((Deer) processing.listGUI.get(i)).getLabel().equals(food.getLabel())) {
          processing.listGUI.remove(i);
        }
      }
    }
    // Eaten count +1
    deerEatenCount++;
    // Release the mouse
    if(this.isDragging()) { // No need to call mouseReleased() if not dragging. Save some time
      processing.mouseReleased();
    }
  }

  /**
   * Tiger's behavior in the Jungle Park Scans for food at the neighborhood of the current tiger. If
   * the Tiger founds any deer at its proximity, it hops on it, and eats it
   */
  @Override
  public void action() {
    for (int i = 0; i < processing.listGUI.size(); i++) {
      if (processing.listGUI.get(i) instanceof Deer // Check if the object refers to a Deer
          && super.isClose((Animal) processing.listGUI.get(i), SCAN_RANGE)) { // Downcast
        // Compare the Euclidean distance with SCAN_RANGE
        // Downcast ParkGUI object to Deer object
        hop((Deer) processing.listGUI.get(i)); // Hop on the deer if it is close
      }
    }
    if (deerEatenCount > 0)
      displayDeerEatenCount(); // display deerEatenCount
  }
  
  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   */
  public void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(),
        this.getPositionY() - this.image.height / 2 - 4);
  }


}
