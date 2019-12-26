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
import java.util.Scanner;

/**
 * This class is the driver of the math game
 * 
 * @author Aaron Zhang, Yixing Tu
 */
public class GameApplication {

  /**
   * Main method activates the program and gets user input
   * 
   * @param args
   */
  public static void main(String[] args) {
    Random rand = new Random(); // Initial instances we might use
    GameList list = new GameList();
    Scanner in = new Scanner(System.in);
    String input; // Entire input from the user
    char operator; // The character at the last index of input which is the operator
    int number; // The number gained from user
    int moveCount = 0; // Count moves
    int goal = rand.nextInt(90) + 10; // Random goal number
    boolean achieved = false; // If the goal number is achieved

    // Initialize the list
    int count = 1;
    while (count <= 7) { // Count and add 7 nodes.
      list.addNode(new GameNode(rand));
      count++;
    }

    // Prompt user to input
    System.out.println("Goal: " + goal + " Moves Taken: " + moveCount);
    System.out.println("Puzzles: " + list);
    System.out.print("Number and Operation " + GameOperator.ALL_OPERATORS + " to Apply: ");
    input = in.nextLine().trim();

    // Main loop
    // Check if user chooses quit or the goal is achieved
    while (!input.toLowerCase().equals("quit") && !achieved) {
      operator = input.charAt(input.length() - 1); // Get operator
      input = input.substring(0, input.length() - 1); // The rest of string exception operation
      if (checkValidInput(operator, input)) { // Check if the input only contains valid character
        number = Integer.parseInt(input);
        if (list.contains(number)) { // Determine if the number is existed
          list.applyOperatorToNumber(number, GameOperator.getFromChar(operator));
          moveCount++;
        } else {
          System.out.println("The number you entered does not exits. Try again");
        }
        if (getGoal(list, goal)) { // Determine if the goal number is achieved
          achieved = true;
        }
      }
      if (!achieved) { // Continue the loop if the goal is not achieved
        System.out.println("\nGoal: " + goal + " Moves Taken: " + moveCount);
        System.out.println("Puzzles: " + list);
        System.out.print("Number and Operation " + GameOperator.ALL_OPERATORS + " to Apply: ");
        input = in.nextLine().trim();
      }
    }
    // If user chooses to quit or goal is achieved, these lines will be executed
    if (input.toLowerCase().equals("quit")) {
      System.out.println("\nBye Bye!");
    } else if (achieved) {
      System.out.println("\nCongratulations, you won in " + moveCount + " moves.");
      System.out.println("Solution: " + list);
    }
    in.close();
  }

  /**
   * getGoal method check if the goal number is achieved
   * 
   * @param list is the list of nodes needs to search in
   * @param goal is the goal number
   * @return true if achieved; otherwise, return false
   */
  public static boolean getGoal(GameList list, int goal) {
    if (list.contains(goal)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * checkValidInput method if the user input is valid
   * 
   * @return true if it's valid; otherwise, return false
   */
  public static boolean checkValidInput(char operator, String restInput) {
    boolean valid = true;
    if (GameOperator.getFromChar(operator) == null) {
      System.out.println("The operator doesn't exist");
      valid = false;
    }
    try {
      Integer.parseInt(restInput);  // Find non-digital by catching NumberFormatException
    } catch (NumberFormatException e) {
      if (!restInput.toUpperCase().equals("QUIT")) { // QUIT is a special case
        System.out.println("Invalid number");
        valid = false;
      }
    }
    return valid;
  }

}
