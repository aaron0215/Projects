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

import java.util.Scanner;

/**
 * This class is a driver of the this drawing game
 * It contains six methods which represent six operations
 * 
 * @author Aaron Zhang, Yixing Tu
 *
 */
public class AsciiArt {
  public static Canvas canvas; // Canvas will be drawn on
  
  /**
   * Main method runs the program, shows menu to user
   * and call specific method based on user choice
   * 
   * @param args
   */
  public static void main(String[] args) {
    String command; // Command entered by user.
    Scanner in = new Scanner(System.in); // Initialize the scanner
    // Display menu
    System.out.print("======== MENU ========\n" + "[1] Create a new canvas\n"
        + "[2] Draw a character\n" + "[3] Undo drawing" + "[4] Redo drawing\n"
        + "[5] Show current canvas\n" + "[6] Show drawing history\n" + "[7] Exit\n" + ">");
    command = in.nextLine(); // initialize the command
    while(command.charAt(0) != '7') { // Loop
      switch (command.charAt(0)) { // Get user choice and call corresponding method
        // Call corresponding methods
        case '1': 
          createCanvas(in);
          break;
        case '2':
          drawCharacter(in);
          break;
        case '3':
          undoDrawing();
          break;
        case '4':
          redoDrawing();
          break;
        case '5':
          showCanvas();
          break;
        case'6':
          showDrawingHistory();
          break;
        default:
          System.out.println("Invalid command"); // Get ride of other command
          break;
      }
      // Loop again
      System.out.print("======== MENU ========\n" + "[1] Create a new canvas\n"
          + "[2] Draw a character\n" + "[3] Undo drawing\n" + "[4] Redo drawing\n"
          + "[5] Show current canvas\n" + "[6] Show drawing history\n" + "[7] Exit\n" + ">");
      command = in.nextLine();
    }
    // Program ends
    in.close();
    System.out.println("Bye!");
  }
  
  /**
   * This method creates and initialized a new canvas with
   * the width and height from user input
   * 
   * @param in is the scanner used in the whole program
   */
  public static void createCanvas(Scanner in) {
    int width, height;
    System.out.print("Width > ");
    width = Integer.parseInt(in.nextLine()); // Convert coordinate to integer
    System.out.print("Height > ");
    height = Integer.parseInt(in.nextLine());
    canvas = new Canvas(width, height);
  }
  
  /**
   * This method draws the character that user chooses
   * at certain position on the canvas
   * 
   * @param in is the scanner used in the whole program
   */
  public static void drawCharacter(Scanner in) {
    int row, col;
    char c;
    System.out.print("Row > ");
    row = Integer.parseInt(in.nextLine()); // Convert coordinate to integer
    System.out.print("Col > ");
    col = Integer.parseInt(in.nextLine());
    System.out.print("Character > ");
    c = in.nextLine().charAt(0); // Get character from input string
    canvas.draw(row, col, c);
  }
  
  /**
   * This method undo the last drawing
   */
  public static void undoDrawing() {
    canvas.undo();
  }
  
  /**
   * This method redo the last drawing
   */
  public static void redoDrawing() {
    canvas.redo();
  }
  
  /**
   * This method prints the canvas by calling toString() method
   */
  public static void showCanvas() {
    System.out.print(canvas); // toString() method is called automatically
  }
  
  /**
   * This methods shows drawing history
   */
  public static void showDrawingHistory() {
    canvas.printHistory();
  }
}
