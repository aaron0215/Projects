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
 * This is a testing class
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class GameTests {

  /**
   * The main method of the tests. Count failed tests and print proper statement
   * 
   * @param args
   */
  public static void main(String[] args) {
    int failed = 0;
    if(!test1GameNode()) {
      System.out.println("Testing constructor of GameNode failed");
      failed ++;
    }
    if(!test2GameNode()) {
      System.out.println("Testing setNext method of GameNode class failed");
      failed ++;
    }
    if(!test1GameList()) {
      System.out.println("Testing addNode method of GameList class failed");
      failed ++;
    }
    if(!test2GameList()) {
      System.out.println("Testing applyOperatorToNumber method of GameList class failed");
      failed ++;
    }
    if(failed == 0) {
      System.out.print("All tests passed");
    }
  }
  
  /**
   * This method tests constructor of GameNode class
   * 
   * @return true when passed
   */
  public static boolean test1GameNode() {
    boolean passed = false;
    Random rng = new Random();
    GameNode testNode = new GameNode(rng); // Test if the GameNode is created successfully
    if(testNode.getNext() == null && testNode.getNumber() <= 9 && testNode.getNumber() >= 1) {
      passed = true;
    }
    return passed;
  }
  
  /**
   * This methods setNext method of GameNode class
   * 
   * @return true when passed
   */
  public static boolean test2GameNode() {
    boolean passed = false;
    Random rng = new Random();
    GameNode testNode = new GameNode(rng);
    GameNode newNode = new GameNode(rng);
    testNode.setNext(newNode);
    if(testNode.getNext() != null) {
      passed = true;
    }
    return passed;
  }
  
  /**
   * This method tests addNode method of the GameList class
   * 
   * @return true when passed
   */
  public static boolean test1GameList() {
    boolean passed = false;
    Random rng = new Random();
    GameNode testNode = new GameNode(rng);
    GameList testList = new GameList();
    testList.addNode(testNode);
    if(testList.contains(testNode.getNumber())) {
      passed = true;
    }
    return passed;
  }
  
  /**
   * This methods tests applyOperatorToNumber method of GameList class directly
   * And it tests applyOperator of GameNode class indirectly
   * 
   * @return true when the test passed
   */
  public static boolean test2GameList() {
    boolean passed = false;
    char operator = '+';
    Random rng = new Random();
    GameList list = new GameList();
    GameNode node1 = new GameNode(rng);
    GameNode node2 = new GameNode(rng);
    int num = node1.getNumber();
    int sum = num + node2.getNumber();
    list.addNode(node1);
    list.addNode(node2); // node2 is at the end
    list.applyOperatorToNumber(num, GameOperator.getFromChar(operator));
    if(node1.getNumber() == sum) {
      passed = true;
    }
    return passed;
  }

}
