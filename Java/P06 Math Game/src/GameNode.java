// Title: Math Game
// Files: GameApplication.java GameList.java GameNode.java GameOperator.java GameTests.java
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

import java.util.Random;

/**
 * This class represents a GameNode object contains number and reference to next node
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class GameNode {
  private int number; // the number held within this node
  private GameNode next; // the next GameNode in the list, or null for the last node

  /**
   * Default constructor initializes number to random 1-9 value, and next to null
   * 
   * @param rng is the random instance
   */
  public GameNode(Random rng) {
    number = rng.nextInt(9) + 1; // 0-8 + 1 -> 1-9
    next = null;
  }

  /**
   * getNumber method accesses the number field
   * 
   * @return the number in this node
   */
  public int getNumber() {
    return number;
  }
  
  /**
   * getNext method accesses the next node
   * 
   * @return the next node
   */
  //
  public GameNode getNext() {
    return next;
  } 

  /**
   * setNext method is the mutator for the next filed
   * 
   * @param next is the new input next node
   */
  public void setNext(GameNode next) {
    this.next = next;
  } 

  /**
   * applyOperator method does operation based on given operator
   * It calls apply method in GameOperator class to apply the operator
   * 
   * @param operator is the given operator
   */
  public void applyOperator(GameOperator operator) {
    // New number is the combination of this number and next number
    number = operator.apply(number, next.getNumber());
    // New next reference is the next node of following node
    next = next.getNext();
    
  }
}
