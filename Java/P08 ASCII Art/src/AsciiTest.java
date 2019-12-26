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

/**
 * This class does some tests of operations in classes
 * 
 * @author Aaron Zhang, Yixing Tu
 *
 */
public class AsciiTest {

  /**
   * This main method runs the tests and count the failed tests
   * If not failed tests then shows all tests passed
   * 
   * @param args
   */
  public static void main(String[] args) {
    int failed = 0;
    if(!testStackPushPeek()){
      System.out.println("Stack push peek test failed");
      failed++;
    }
    if(!runStackTestSuite()){
      System.out.println("Run stack test failed");
      failed++;
    }
    if(failed == 0) {
      System.out.println("All tests passed");
    }
  }

  /**
   * This test method tests push method of stack by
   * called peek to check if the element is pushed into stack
   * 
   * @return true if test is passed, otherwise, false
   */
  public static boolean testStackPushPeek() {
    boolean passed = false;
    DrawingStack testStack = new DrawingStack(); // New stack
    DrawingChange test = new DrawingChange(10, 20, 'a', 'b'); // New element
    testStack.push(test); // Push
    if (testStack.peek() == test) // Check if the element is in it
      passed = true;
    else
      System.out.println("Stack push peek test failed");
    return passed;
  }
  
  /**
   * This test method tests a bunch of method of Canvas class
   * Contains constructor, drawing, toString, undo and redo
   * 
   * @return true if test is passed, otherwise, false
   */
  public static boolean runStackTestSuite() {
    boolean passed = false;
    try {
      String testString;
      Canvas testCanvas = new Canvas(3, 1); // canvas for testing
      testCanvas.draw(0, 0, 'a'); // Draw two characters
      testCanvas.draw(0, 2, 'b');
      testString = testCanvas.toString(); // Get the Canvas
      // Compare the canvas with what it should look like
      boolean createPassed = testString.equals("a b" + System.lineSeparator());
      testCanvas.undo(); // test undo by comparing with the canvas after undo operation
      testString = testCanvas.toString();
      boolean undoPassed = testString.equals("a  " + System.lineSeparator());
      testCanvas.redo();// test redo by comparing with the canvas after redo operation
      testString = testCanvas.toString();
      boolean redoPassed = testString.equals("a b" + System.lineSeparator());
      if(undoPassed && redoPassed && createPassed) {
        passed = true;
      }
    } catch(IllegalArgumentException e) {
      passed = false;
    }
   
    return passed;
  }

}
