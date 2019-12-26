// Title: Badgers Settle Down
// Files: Badger.java Sett.java P9Tests.java BadgersSettleDown.java
// Course: COMP SCI 300, Fall 2018
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Yixing Tu
// Partner Email: ytu26@wisc.edu
// Partner Lecturer's Name: Gary Dahl
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

/**
 * This class represents a Badger object, contains the left and right neighbor and its own size
 * @author Aaron Zhang, Yixing Tu
 */
public class Badger {
  private Badger leftLowerNeighbor; // The left lower neighbor in binary tree
  private Badger rightLowerNeighbor; // The right lower neighbor in binary tree
  private final int size; // The size of this Badger object
  
  /**
   * Constructor creates a new Badger with specified size.
   * @param size is the specified size of new Badger
   */
  public Badger (int size) {
    this.size = size;
  }
  
  /**
   * Retrieves neighboring badger that is smaller than this one
   * @return leftLowerNeighbor is the left lower neighbor of current badger
   */
  public Badger getLeftLowerNeighbor() {
    return leftLowerNeighbor;
  }
  
  /**
   * Retrieves neighboring badger that is larger than this one
   * @return rightLowerNeighbor is the right lower neighbor of current badger
   */
  public Badger getRightLowerNeighbor() {
    return rightLowerNeighbor;
  }
  
  /**
   * Retrieves the size of this badger.
   * @return size of current badger
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Changes this badger's lower left neighbor
   * @param badger is the new left lower neighbor of current badger
   */
  public void setLeftLowerNeighbor(Badger badger) {
    leftLowerNeighbor = badger;
  }
  
  /**
   * Changes this badger's lower right neighbor
   * @param badger is the new right lower neighbor of current badger
   */
  public void setRightLowerNeighbor(Badger badger) {
    rightLowerNeighbor = badger;
  }
}
