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

import java.lang.StringBuilder; // Using it to get rid of error from simply adding string together
import java.util.Iterator;

/**
 * This class represents a Canvas that user can draw character on
 * It contains width and height, and a array stores all drawings
 * Also undoStack and redoStack to store drawing history
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas

  private char[][] drawingArray; // 2D character array to store the drawing

  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Constructor. Throws IllegalArgumentException if width or height is 0 or negative A Canvas is
   * initially blank (use the space ' ' character)
   * 
   * @param width is the initialized width of the canvas
   * @param height is the initialized height of the canvas
   */
  public Canvas(int width, int height) {
    if (width <= 0 || height <= 0) { // Throw exception while size is 0 or negative
      throw new IllegalArgumentException("Neither width nor height can be 0");
    }
    this.width = width; // Initialize width
    this.height = height; // Initialize height
    drawingArray = new char[height][width]; // Initialize the array
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        drawingArray[row][col] = ' '; // Initialize the array by filling with blank
      }
    }
    undoStack = new DrawingStack(); // Initialize undoStack
    redoStack = new DrawingStack(); // Initialize redoStack
  }

  /**
   * Draw a character at the given position This method should throw an IllegalArgumentException if
   * the drawing position is outside the canvas If that position is already marked with a different
   * character, overwrite it.After making a new change, add a matching DrawingChange to the
   * undoStack so that we can undo if needed. After making a new change, the redoStack should be
   * empty
   * 
   * @param row is the row of drawing character
   * @param col is the column of drawing character
   * @param c is the drawing character
   */
  public void draw(int row, int col, char c) {
    // height and width minus 1 because coordinates start from 0 but size starts from 1
    if (row > height - 1 || col > width - 1) { // Throws exception while out of the canvas
      throw new IllegalArgumentException("Coordinate out of canvas");
    }
    // Create a new drawing change
    DrawingChange change = new DrawingChange(row, col, drawingArray[row][col], c);
    undoStack.push(change); // add previous element into undo stack no matter what it is
    drawingArray[row][col] = c; // Draw (will overwrite) the character at certain position
  }

  /**
   * Undo the most recent drawing change. Return true if successful. False otherwise.
   * An undone DrawingChange should be added to the redoStack so that we can redo if needed.
   * 
   * @return true while undo successfully, otherwise false
   */
  public boolean undo() {
    if (undoStack == null) { // cannot undo if there is not history
      return false;
    } else {
      DrawingChange change = undoStack.pop(); // Get the drawing change from undoStack
      redoStack.push(change); // Get ready for redo
      drawingArray[change.x][change.y] = change.prevChar; // Get previous character
      return true;
    }
  }

  /**
   * Redo the most recent undone drawing change. Return true if successful. False otherwise. 
   * A redone DrawingChange should be added (back) to the undoStack so that we can undo again if
   * needed.
   * 
   * @return true while redo successfully, otherwise false
   */
  public boolean redo() {
    if (redoStack == null) { // cannot undo if none operation was done
      return false;
    } else {
      DrawingChange change = redoStack.pop(); // Get the drawing change from redoStack
      undoStack.push(change); // Get ready for undo
      drawingArray[change.x][change.y] = change.newChar; // Get next character
      return true;
    }
  }

  /**
   * @return a printable string version of the Canvas.
   */
  @Override
  public String toString() {
    // Simply adding strings together is expensive
    // Use StringBuilder to lower complexity and get rid of time out error in Zybooks test3
    StringBuilder s = new StringBuilder();
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        s.append(Character.toString(drawingArray[row][col])); // Convert character to string
      }
      s.append(System.lineSeparator()); // Separate lines
    }
    return s.toString(); // Call toString() method of the StringBuilder class
  }
  
  /**
   * This method loops through undoStack to print history
   */
  public void printHistory() {
    Iterator<DrawingChange> iterator = undoStack.iterator(); // get iterator
    DrawingChange d; // initialize a DrawingChange instance
    int count = undoStack.size(); // get count of history
    while(iterator.hasNext()) { // detect if it has next
      d = iterator.next(); // get data
      System.out.println(count + ". draw '" + d.newChar + "' on (" + d.y + "," + d.x + ")");
      count--;
    }
  }
}
