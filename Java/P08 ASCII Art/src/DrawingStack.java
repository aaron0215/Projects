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
 * This class is a stack stores and links drawing change nodes It implements StackADT interface and
 * specify the object type (non-generic)
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class DrawingStack implements StackADT<DrawingChange> {
  private Node<DrawingChange> head; // always represents the first node
  private int count;

  /**
   * Constructor initializes the head (first) node to be null
   */
  public DrawingStack() {
    this.head = null;
    count = 0;
  }

  /**
   * Push the DrawingChange object into the stack. Since stack is last in first out,
   * so we add new object at the beginning of the stack to obey the rule
   * 
   * @param element is the new element to push into stack
   * @throws IllegalArgumentException while input is null
   */
  @Override
  public void push(DrawingChange element) throws IllegalArgumentException {
    if(element == null) { // throws IllegalArgumentException while input is null
      throw new IllegalArgumentException("Cannot push null element into stack");
    } else {
      // Create a new node for new drawing change
      Node<DrawingChange> newNode = new Node<DrawingChange>(element, null);
      if(head != null) { // If the head is existed
        newNode.setNext(this.head); // Add new node in front of the head
      }
      this.head = newNode; // The new node should be set as head in any case
      count++;
    }
  }

  /**
   * Pop method removes the element on the stack top and return it
   * 
   * @return the element removed from the stack top
   */
  @Override
  public DrawingChange pop() {
    DrawingChange popData = this.head.getData(); // Get data
    this.head = this.head.getNext(); // Set next node as the new head
    count--;
    return popData; // Return the data
  }

  /**
   * Get the element on the stack top
   * 
   * @return the element on the stack top
   */
  @Override
  public DrawingChange peek() {
    return this.head.getData(); // Access and return the data
  }

  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    return head == null; // Null head indicates empty linked list
  }

  /**
   * Get the number of elements in the stack
   * 
   * @return the size of the stack
   */
  @Override
  public int size() {
    return count;
  }

  /**
   * @return iterator of the stack
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(head);
  }

}
