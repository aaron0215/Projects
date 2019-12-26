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
 * This is a test class
 * @author Aaron Zhang, Yixing Tu
 */
public class P9Tests {

  /**
   * The main method runs all tests
   * @param args
   */
  public static void main(String[] args) {
    if(runAllBadgerTests()) {
      System.out.println("All badger tests passed");
    }
    if(runAllSettTests()) {
      System.out.println("All sett tests passed");
    }
  }
  
  /**
   * Runs all tests towards Badger class
   * @return true if all tests passed
   */
  public static boolean runAllBadgerTests() {
    boolean passed = true;
    if(!testBadger1()) {
      System.out.println("testBadger1 failed");
      passed = false;
    }
    if(!testBadger2()) {
      System.out.println("testBadger2 failed");
      passed = false;
    }
    return passed;
  }
  
  /**
   * Runs all tests towards Sett class
   * @return true if all tests passed
   */
  public static boolean runAllSettTests() {
    boolean passed = true;
    try {
      if(!testSett1()) {
        System.out.println("testSett1 failed");
        passed = false;
      }
      if(!testSett2()) {
        System.out.println("testSett2 failed");
        passed = false;
      }
      if(!testSett3()) {
        System.out.println("testSett3 failed");
        passed = false;
      }
      if(!testSett4()) {
        System.out.println("testSett4 failed");
        passed = false;
      }
      if(!testSett5()) {
        System.out.println("testSett5 failed");
        passed = false;
      }
    } catch (java.util.NoSuchElementException e) {
      passed = false;
    } catch (IllegalArgumentException e) {
      passed = false;
    }
    return passed;
  }
  
  /**
   * Test constructor and getSize()
   * @return true if size is correct, otherwise, false
   */
  public static boolean testBadger1() {
    Badger test = new Badger(20);
    return test.getSize() == 20;
  }
  
  /**
   * Test mutator and getter
   * @return true if correct otherwise false
   */
  public static boolean testBadger2() {
    Badger b1 = new Badger(30);
    Badger b2 = new Badger(20);
    Badger b3 = new Badger(40);
    b1.setLeftLowerNeighbor(b2);
    b1.setRightLowerNeighbor(b3);
    return (b1.getLeftLowerNeighbor().getSize() == 20 
        && b1.getRightLowerNeighbor().getSize() == 40);
  }
  
  /**
   * Test constructor and settle and getHeight
   * @return true if added successfully, otherwise, false
   */
  public static boolean testSett1() {
    Sett sett = new Sett();
    sett.settleBadger(30);
    sett.settleBadger(35);
    sett.settleBadger(10);
    sett.settleBadger(36);
    sett.settleBadger(37);
    sett.settleBadger(38);
    return sett.getHeight() == 5;
  }
  
  /**
   * Test getLargestBadger()
   * @return true if added successfully, otherwise, false
   */
  public static boolean testSett2() {
    Sett sett = new Sett();
    sett.settleBadger(30);
    sett.settleBadger(40);
    sett.settleBadger(10);
    sett.settleBadger(35);
    sett.settleBadger(50);
    return sett.getLargestBadger().getSize() == 50;
  }
  
  /**
   * Test countBadger()
   * @return true if added successfully, otherwise, false
   */
  public static boolean testSett3() {
    Sett sett = new Sett();
    sett.settleBadger(30);
    sett.settleBadger(40);
    sett.settleBadger(10);
    sett.settleBadger(35);
    sett.settleBadger(50);
    return sett.countBadger() == 5;
  }
  
  /**
   * Test findBadger
   * @return true if added successfully, otherwise, false
   */
  public static boolean testSett4() {
    Sett sett = new Sett();
    sett.settleBadger(30);
    sett.settleBadger(40);
    sett.settleBadger(10);
    sett.settleBadger(35);
    sett.settleBadger(50);
    Badger found = sett.findBadger(10);
    return found.getSize() == 10;
  }
  
  /**
   * Test throw IllegalArgumentException
   * @return true if catches the exception
   */
  public static boolean testSett5() {
    boolean passed = false;
    Sett sett = new Sett();
    sett.settleBadger(30);
    sett.settleBadger(35);
    sett.settleBadger(10);
    try {
      sett.settleBadger(35); // should throw exception here because 35 already exists
    } catch (IllegalArgumentException e) {
      passed = true;
    }
    return passed;
  }

}
