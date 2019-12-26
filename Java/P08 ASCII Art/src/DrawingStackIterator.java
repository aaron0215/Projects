// Title: Ascii Art
// Files: AsciiArt.java AsciiTest.java Canvas.java DrawingChange.java DrawingStack.java
// DrawingStackIterator.java Node.java StackADT.java
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

import java.util.Iterator;

/**
 * This class is an iterator for the DrawingStack
 * Implements Iterator interface with specified object type (non-generic)
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class DrawingStackIterator implements Iterator<DrawingChange>{
  
  private Node<DrawingChange> node; // The node we are working on
  int count;

  /**
   * Constructor initialize the node of the list we want to iterate
   * @param node is the node from the list
   */
  public DrawingStackIterator(Node<DrawingChange> node){
    this.node = node;
  }
  
  /**
   * @return if the list has next node
   */
  @Override
  public boolean hasNext() {
    if(this.node == null) {
      return false;
    }
    return true;
  }

  /**
   * This method return currents data and set the next node
   * to be the node we are working on
   * @return returnData which is the date of current node
   */
  @Override
  public DrawingChange next() {
    if(!hasNext()) {
      return null; 
    }
    DrawingChange returnDate = this.node.getData();
    this.node = this.node.getNext();
    return returnDate;
  }

}
