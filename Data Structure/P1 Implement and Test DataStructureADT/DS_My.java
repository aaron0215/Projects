// Title: Implement and Test DataStructureADT
// Files: DS_My.java TestDS_My.java DataStructureADTTest.java
// Course: COMP SCI 400, Spring 2019
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Andrew Kuemmel
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
 * This class represents the data structure I use
 * It contains an inner class which creates a linked list
 * @author Aaron Zhang
 */
public class DS_My implements DataStructureADT {

  /**
   * Inner class represents a linked node
   * @author Aaron Zhang
   */
  private class LinkNode {
    
    // Fields of inner class
    private Comparable key;
    private Object value;
    private LinkNode next;
    
    /**
     * Constructor sets key and value of this node and set next to be null
     * @param key is the comparable key stored in this node
     * @param value is the object value stored in this node
     */
    private LinkNode(Comparable key, Object value) {
      this.key = key;
      this.value = value;
      next = null;
    }
    
    /**
     * Link to the next node
     * @param next is the linked next node
     */
    private void setNext(LinkNode next) {
      this.next = next;
    }
    
    /**
     * @return the comparable key inside this node
     */
    private Comparable getKey() {
      return key;
    }
    
    /**
     * @return the object value inside this node
     */
    private Object getValue() {
      return value;
    }
    
    /**
     * @return the next node
     */
    private LinkNode getNext() {
      return next;
    }
  } // End of inner class
  
  // Fields of outer class
  private LinkNode head; // Root (head) node
  private int count;    // Count the number of nodes to make counting easier

  /**
   * Constructor sets head as null and count as zero
   */
  public DS_My() {
    head = null;
    count = 0;
  }

  /**
   * Insert new node at the end of linked list
   * @param k is the key of the new node
   * @param v is the value of the new node
   */
  @Override
  public void insert(Comparable k, Object v) {
    if(k == null || v == null) {    // Can't accept null input
      throw new IllegalArgumentException("Null input detected");
    }
    LinkNode curNode = head;
    if(head == null) {
      head = new LinkNode(k,v); // Set the first (head) node
      count++;
    } else {
      while(!curNode.getKey().equals(k) && curNode.getNext() != null) {
        curNode = curNode.getNext(); // Get to the next node if not reach the end
      }
      if(curNode.getKey().equals(k))
        throw new RuntimeException("Key already exists");
      else {
        curNode.setNext(new LinkNode(k,v)); // Reach the end of the list and then insert
        count++;
      }
    }
  }

  /**
   * Removes the node and returns true if found
   * No action and returns false if not found
   * @param k is the key of the node needs to be removed
   */
  @Override
  public boolean remove(Comparable k) {
    if(k == null) { // Can't accept null input
      throw new IllegalArgumentException("Null input detected");
    }
    LinkNode curNode = head;
    LinkNode previousNode = null; // Will be used to do removal
    if(head == null) {
      return false; // Return false if the list is empty
    } else if(head.getKey().equals(k)){ // While head is the one we are looking for
      if(head.getNext() != null) {  // Case 1: there are nodes following
        head = head.getNext();
      } else {  // Case 2: no nodes following (head is the only node left)
        head = null;
      }
      count--;
      return true;
    } else {
      while(!curNode.getKey().equals(k)) {
        if(curNode.getNext() == null) {
          return false; // Not found. Stop the program
        } else {
          previousNode = curNode;
          curNode = curNode.getNext();
        }
      }
      // Find the node, link previous node to next node to complete the removal
      previousNode.setNext(curNode.getNext());
      count--;
      return true;
    }
  }

  /**
   * Search for a node. Return true if found; otherwise, false
   * @param k is the key of the node needs to be found
   */
  @Override
  public boolean contains(Comparable k) {
    if(k == null) { // Can't accept null input
      return false;
    }
    LinkNode curNode = head;
    if(curNode == null) {
      return false;
    } else {
      while(!curNode.getKey().equals(k)) {
        if(curNode.getNext() == null) {
          return false; // Stop and return false while not found
        } else {
          curNode = curNode.getNext();
        }
      }
      return true; // Find match thus return true
    }
  }

  /**
   * Find and return a certain key. If not found, return null
   * @param is the key of the node needs to be returned
   */
  @Override
  public Object get(Comparable k) {
    if(k == null) { // Can't accept null input
      throw new IllegalArgumentException("Null input detected");
    }
    LinkNode curNode = head;
    if(curNode == null) {
      return null;
    } else {
      while(!curNode.getKey().equals(k)) {
        if(curNode.getNext() == null) {
          return null;  // Return null while not found
        } else {
          curNode = curNode.getNext();
        }
      }
      return curNode.getValue(); // Find and return it
    }
  }

  /**
   * @return count which is the size of this linked list
   */
  @Override
  public int size() {
    return count;
  }

}
