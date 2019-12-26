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
 * This class represents a change happened in drawing process
 * It contains x and y which makes the location of the drawing
 * and previous and new drawing character
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class DrawingChange {
  public final int x; // x coordinate for a change
  public final int y; // y coordinate for a change
  public final char prevChar; // previous character in the (x,y)
  public final char newChar; // new character in the (x,y)

  /**
   * Constructor creates instance and initialize all fields
   * 
   * @param x is the x-coordinate
   * @param y is the y-coordinate
   * @param prevChar is the previous drawing character
   * @param newChar is the new drawing character
   */
  public DrawingChange(int x, int y, char prevChar, char newChar) {
    this.x = x;
    this.y = y;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }

}
