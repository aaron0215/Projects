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
 * This class is a sett of badgers contains a few of badgers as user inputs
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class Sett {
  private Badger topBadger; // The object at the top of the binary tree (first added Badger)

  /**
   * Constructor creates an empty Sett by setting top object to be null
   */
  public Sett() {
    topBadger = null;
  }

  /**
   * Empties this Sett, to no longer contain any Badgers
   */
  public void clear() {
    // Since Java will collect garbage automatically, we just need to set root node to be null
    topBadger = null;
  }

  /**
   * Counts how many Badgers live in this Sett
   * 
   * @return The number of Badgers living in this Sett
   */
  public int countBadger() {
    return countHelper(topBadger); // call the recursive helper
  }

  /**
   * This recursive helper method is used to help count the number of Badgers in this Sett.
   * 
   * @return the number of Badgers in this sett
   */
  private int countHelper(Badger current) {
    int count = 0;
    if (current == null) { // base case: reaches the end of the binary tree
      return count; // returns the number and stops recursion
    } else {
      count++; // Goes to the next and count plus one
      // Goes to the two paths separately
      if (current.getLeftLowerNeighbor() != null) {
        count += countHelper(current.getLeftLowerNeighbor()); // Keep adding the number in each path
      }
      if (current.getRightLowerNeighbor() != null) {
        count += countHelper(current.getRightLowerNeighbor());
      }
      return count; // returns the counting number while the whole recursion ends
    }
  }

  /**
   * Finds a Badger of a specified size in this Sett.
   * 
   * @param size is the specified size of the Badger we want to find
   * @return the object if found
   * @throws NoSuchElementException while the object does not exist
   */
  public Badger findBadger(int size) throws java.util.NoSuchElementException {
    return findHelper(topBadger, size); // call the recursive helper
  }

  /**
   * This recursive helper method is used to find the Badger of specified size in this Sett.
   * 
   * @param current is the Badger object that the search starts from
   * @param size is the specified size of the Badger we want to find
   * @return the object if found
   * @throws NoSuchElementException while the object does not exist
   */
  private Badger findHelper(Badger current, int size) {
    Badger foundBadger;
    // base case: reaches the end or finds the object
    if (current == null || current.getSize() == size) {
      foundBadger = current;
    } else if (size < current.getSize()) { // determines the search direction
      foundBadger = findHelper(current.getLeftLowerNeighbor(), size);
    } else {
      foundBadger = findHelper(current.getRightLowerNeighbor(), size);
    }

    // Throws exception while not found
    if (foundBadger == null) {
      throw new java.util.NoSuchElementException(
          "WARNING: failed to find a badger with size " + size + " in the sett");
    }
    return foundBadger;
  }

  /**
   * Gets all Badgers living in the Sett as a list in ascending order of their size: smallest one
   * at the front (at index zero), through the largest one at the end (at index size-1).
   * 
   * @return the list of all badgers in this sett
   */
  public java.util.List<Badger> getAllBadgers() {
    // ArrayList is the easiest list to manipulate
    java.util.List<Badger> allBadgers = new java.util.ArrayList<Badger>(); // Cast to ArrayList
    getAllHelper(topBadger, allBadgers);
    return allBadgers;
  }

  /**
   * This recursive helper method is used to sort all Badgers in this Sett into a list.
   * 
   * @param current is the Badger object that the sorting starts from
   * @param allBadgers is the list stores all Badgers
   */
  private void getAllHelper(Badger current, java.util.List<Badger> allBadgers) {
    // base case is current == null. Since we do nothing in that case so we don't implement it
    if (current != null) {
      if (allBadgers.size() == 0) { // the current object is the first element in this case
        allBadgers.add(current);
      }
      // Left lower neighbor is smaller so add in front of the current object
      if (current.getLeftLowerNeighbor() != null) {
        allBadgers.add(allBadgers.indexOf(current), current.getLeftLowerNeighbor());
        getAllHelper(current.getLeftLowerNeighbor(), allBadgers);
      }
      // Right lower neighbor is larger so add after the current object
      if (current.getRightLowerNeighbor() != null) {
        allBadgers.add(allBadgers.indexOf(current) + 1, current.getRightLowerNeighbor());
        getAllHelper(current.getRightLowerNeighbor(), allBadgers);
      }
    }
  }

  /**
   * Computes the height of the Sett binary tree
   * 
   * @return the depth of this Sett.
   */
  public int getHeight() {
    return getHeightHelper(topBadger);
  }

  /**
   * This recursive helper method is used to help compute the height of this Sett.
   * 
   * @param current is the Badger object that the calculation starts from
   * @return the height of the (sub) tree that we are calculating.
   */
  private int getHeightHelper(Badger current) {
    int leftHeight;
    int rightHeight;
    if (current == null) {
      return 0; // In this project, no node means 0 not -1
    }
    // Need to compare two sides to find larger depth which represents the height
    leftHeight = getHeightHelper(current.getLeftLowerNeighbor());
    rightHeight = getHeightHelper(current.getRightLowerNeighbor());
    if (leftHeight > rightHeight) {
      return leftHeight + 1; // Since it doesn't start from zero (or -1), we need to add one here
    } else {
      return rightHeight + 1;
    }
  }

  /**
   * Retrieves the largest Badger living in this Sett.
   * 
   * @return The largest Badger living in this Sett.
   */
  public Badger getLargestBadger() {
    if (!isEmpty()) {
      Badger largest = topBadger;
      // Largest badger will only go to the right path
      while (largest.getRightLowerNeighbor() != null) {
        largest = largest.getRightLowerNeighbor();
      }
      return largest;
    } else {
      return null;
    }
  }

  /**
   * Retrieve the top Badger within this Sett (the one that was settled first).
   * 
   * @return the topBadger which is the one was settled first
   */
  public Badger getTopBadger() {
    return topBadger;
  }

  /**
   * Checks whether this Sett is empty.
   * 
   * @return true if this Sett is empty, false otherwise.
   */
  public boolean isEmpty() {
    return topBadger == null;
  }

  /**
   * Creates a new Badger object with the specified size, and inserts them into this Sett (BST).
   * Not guaranteed to result in balance.
   * 
   * @param size is the size of the new Badger that will be settled.
   * @throws IllegalArgumentException while the sett already has an object with this size.
   */
  public void settleBadger(int size) throws IllegalArgumentException {
    settleHelper(topBadger, new Badger(size));
  }

  /**
   * This recursive helper method is used to help settle a new Badger within this Sett.
   * While the new Badger is larger than the current Badger then add to the right
   * otherwise add to the left
   * 
   * @param current is the current Badger that we are considering settling the newBadger beneath.
   * @param newBadger is the new Badger that needs to be settled within this Sett.
   * @throws IllegalArgumentException while the sett already has an object with this size.
   */
  private void settleHelper(Badger current, Badger newBadger) {
    if(topBadger == null){ // Initialize the topBadger if it's null
      topBadger = newBadger;
    } else{
      if (newBadger.getSize() < current.getSize()) { // smaller Badger add to the left
        if (current.getLeftLowerNeighbor() == null) { // add leaf node only if the leaf is null
          current.setLeftLowerNeighbor(newBadger); // add newBadger and link with current Badger
        } else {
          settleHelper(current.getLeftLowerNeighbor(), newBadger); // recursion
        }
      } else if (newBadger.getSize() > current.getSize()) { // larger Badger add to the right
        if (current.getRightLowerNeighbor() == null) {
          current.setRightLowerNeighbor(newBadger);
        } else {
          settleHelper(current.getRightLowerNeighbor(), newBadger); // recursion
        }
      } else { // throws exception while the sett already has an object with this size
        throw new IllegalArgumentException("WARNING: failed to settle the badger with size "
           + newBadger.getSize() + ", as there is already a badger with the same size in this sett");
      }
    }
  }

}
