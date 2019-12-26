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
 * GameList class represents a list of nodes
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class GameList {
  private GameNode list; // reference to the first GameNode in this list

  /**
   * Default constructor initializes list to start out empty
   */
  public GameList() {
    list = null;
  }

  /**
   * addNode method adds the new node to the end of this list
   * 
   * @param newNode is the node we need to add
   */
  public void addNode(GameNode newNode) {
    GameNode curNode = list;
    if (list == null) {
      list = newNode;
    } else {
      while (curNode.getNext() != null) { // Check if the curNode is the last node
        curNode = curNode.getNext();
      }
      curNode.setNext(newNode); // curNode is the last node and add newNode next to it
                                // newNode.next is null
    }
  }

  /**
   * contains method check if the given number is contained by this list of nodes only returns true
   * when this list contains a node with the specified number
   * 
   * @param number is the number needs to search for
   * @return true when it is contained; otherwise, return false
   */
  public boolean contains(int number) {
    boolean contains = false;
    GameNode curNode = list;
    if (curNode == null) {
      // Do thing keep contains false
    } else {
      while (curNode.getNext() != null) { // Loop through the list
        if (curNode.getNumber() == number) { // Detect the matched number
          contains = true;
          break;
        } else {
          curNode = curNode.getNext();
        }
      }
      if (curNode.getNext() == null && curNode.getNumber() == number) { // Check the last node
        contains = true;
      }
    }
    return contains;
  }

  /**
   * @return a string with each number in the list separated by " -> "s, and ending with " -> END"
   */
  @Override
  public String toString() {
    String output = "";
    GameNode curNode = list;
    while (curNode.getNext() != null) {
      output += curNode.getNumber() + " -> ";
      curNode = curNode.getNext();
    }
    if (curNode.getNext() == null) { // Add the last node and END
      output += curNode.getNumber() + " -> ";
      output += "END";
    }
    return output;
  }

  /**
   * It scans through this list to find for the first occurrence of a node with the given number
   * 
   * @param number is the number needs to search for
   * @param operator is the operator needs to be applied
   * @throws NullPointerException when out of bound
   */
  public void applyOperatorToNumber(int number, GameOperator operator) {
    GameNode curNode = list;
    Random rng = new Random();
    while (curNode.getNext() != null) {
      if (curNode.getNumber() == number) {
        curNode.applyOperator(operator); // Call applyOperator while find matched number
        this.addNode(new GameNode(rng)); // Add new node at the end of the list
        break;
      } else {
        curNode = curNode.getNext();
      }
    }
    if (curNode.getNext() == null && curNode.getNumber() != number) { // number not existed case
      System.out.println(number + " is not existed.");
    } else if(curNode.getNext() == null & curNode.getNumber() == number){// the last node -> null
      System.out.println("There is no next number");
    }
  }

}
