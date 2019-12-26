// Title: Auditable Banking System
// Files: AuditableBanking.java AuditableBankingTests.java
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

public class AuditableBanking {

  public static void main(String[] args) {
    String command = "B"; // Initialized string enables while loop to start 
    int[][] allTransactions = new int[100][]; // Oversized array for all transactions
    int allTransactionsCount = 0; // Initialized count number
    
    Scanner userInput = new Scanner(System.in);
    
    System.out.println("========== Welcome to the Auditable Banking App ==========");

    // User's view and command menu starts here
    while (!command.toUpperCase().equals("Q")) {
      System.out.println("COMMAND MENU:");
      System.out.println("  Submit a Transaction (enter sequence of integers separated by spaces)");
      System.out.println("  Show Current [B]alance");
      System.out.println("  Show Number of [O]verdrafts");
      System.out.println("  [Q]uit Program");
      System.out.print("ENTER COMMAND: ");
      command = userInput.nextLine();
      allTransactionsCount = processCommand(command, allTransactions, allTransactionsCount);
    }
    userInput.close();
    // Command menu ends here

    System.out.println("============ Thank you for using this App!!!! ============");

  }

  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full then this method will do nothing other than return allTransactionCount.
   * 
   * @param newTransactions is the new transaction group being added (perfect size).
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
      int allTransactionsCount) {
    
    if (allTransactionsCount != allTransactions.length) {   // Test if the oversized array is full
      allTransactions[allTransactionsCount] = newTransactions;  // Add perfect size array to oversize array
      allTransactionsCount += 1;
    }
    
    return allTransactionsCount;
  }

  /**
   * Process the command from user. If the first element the users inputs is number (0,1,2), the
   * method will call submitTransactions method to add newTransactions. If it is B, O, Q, the
   * method will call relevant method to print balance/overdraft count or terminate the program.
   * 
   * @param command is a string of user's input
   * @param newTransactions is the new transaction group being added (perfect size).
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @return the number of transaction groups within allTransactions.
   */
  public static int processCommand(String command, int[][] allTransactions,
      int allTransactionsCount) {
    
    String[] newArray = command.split(" ");
    int[] newTransactions = new int[newArray.length];   // Perfect size array

    // If the element at index 0 is 0, 1 or 2, add whole transaction into allTransactions
    if (newArray[0].equals("0") || newArray[0].equals("1") || newArray[0].equals("2")) {
      for (int i = 0; i < newArray.length; i++) {
        newTransactions[i] = Integer.parseInt(newArray[i]);
      }
      
      // Append new transaction
      allTransactionsCount =
          submitTransactions(newTransactions, allTransactions, allTransactionsCount);
      System.out.println();
    }

    // If the element at index 0 is B,O or Q, do something other than append
    else if (newArray[0].toUpperCase().equals("B")) {
      System.out.println("Current Balance: "
          + calculateCurrentBalance(allTransactions, allTransactionsCount) + "\n");
    } else if (newArray[0].toUpperCase().equals("O")) {
      System.out.println("Numbers of Overdrafts: "
          + calculateNumberOfOverdrafts(allTransactions, allTransactionsCount) + "\n");
    } else if (newArray[0].toUpperCase().equals("Q")) {
      // Do nothing to terminate
    } else {
      // Do nothing
    }
    
    return allTransactionsCount;
  }

  /**
   * Import all arrays from allTransactions and go through all of them to calculate and return an
   * overall current balance and return the amount.
   * 
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions.
   * @return the current balance after all transactions have been applied
   */
  public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
    
    int balance = 0;
    
    for (int row = 0; row < allTransactionsCount; row++) {
      
      // Doing calculation according to the first element of input string
      if (allTransactions[row][0] == 0) {
        if (allTransactions[row].length > 1) {  // Check if the string is invalid (only has one element)
          for (int i = 1; i < allTransactions[row].length; i++) {   // Binary amount transaction
            if (allTransactions[row][i] == 1) {
              balance += 1;
            } else if (allTransactions[row][i] == 0) {
              balance -= 1;
            } else {
              // Do nothing
            }
          }
        }
      } else if (allTransactions[row][0] == 1) {
        if (allTransactions[row].length > 1) {
          for (int i = 1; i < allTransactions[row].length; i++) {   // Integer amount transaction
            balance += allTransactions[row][i];
          }
        }
      } else if (allTransactions[row][0] == 2) {    // Quick withdraw transaction
        if (allTransactions[row].length > 1) {
          balance -= allTransactions[row][1] * 20;
          balance -= allTransactions[row][2] * 40;
          balance -= allTransactions[row][3] * 80;
          balance -= allTransactions[row][4] * 100;
        }
      } else {
        // Do nothing
      }
      
    }
    return balance;
  }

  /**
   * Import all arrays from allTransactions and go through all of calculations
   * and count the number of overdrafts during the process. Then return the number we get.
   * 
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions.
   * @return the current number of overdrafts after all transactions have been applied
   */
  public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
    int balance = 0;
    int overdraftCount = 0;
    for (int row = 0; row < allTransactionsCount; row++) {

      // Doing calculation and count overdraft according to the first element of input string
      if (allTransactions[row][0] == 0) {
        if (allTransactions[row].length > 1) {  // Check if the string is invalid (only has one element)
          for (int i = 1; i < allTransactions[row].length; i++) {   // Binary amount transaction
            if (allTransactions[row][i] == 1) {
              balance += 1;
            } else if (allTransactions[row][i] == 0) {
              balance -= 1;
              if (balance < 0) {
                overdraftCount += 1;    
              }
            } else {
              // Do nothing
            }
          }
        }
      }

      else if (allTransactions[row][0] == 1) {
        if (allTransactions[row].length > 1) {
          for (int i = 1; i < allTransactions[row].length; i++) {   // Integer amount transaction
            balance += allTransactions[row][i];
            if (balance < 0 && allTransactions[row][i] < 0) {   // Overdraft: negative balance and withdraw
              overdraftCount += 1;
            }
          }
        }
      }

      else if (allTransactions[row][0] == 2) {
        if (allTransactions[row].length > 1) {
          balance -= allTransactions[row][1] * 20;    // Quick withdraw transaction
          if (balance < 0) {
            overdraftCount += 1;
          }

          balance -= allTransactions[row][2] * 40;
          if (balance < 0) {
            overdraftCount += 1;
          }

          balance -= allTransactions[row][3] * 80;
          if (balance < 0) {
            overdraftCount += 1;
          }

          balance -= allTransactions[row][4] * 100;
          if (balance < 0) {
            overdraftCount += 1;
          }
        }
      }
    }
    return overdraftCount;
  }
}
