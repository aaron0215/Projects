// Title: Exceptional Banking
// Files: TransactionGroup.java Account.java ExceptionalBankingTests.java
// Course: CS300 Fall 2018
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
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

import java.util.zip.DataFormatException;
import java.io.*;

public class ExceptionalBankingTests {

  /**
   * Testing main. Runs each test and prints which (if any) failed. Expect all tests will
   * pass and get statement at the end.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static void main(String[] args) throws DataFormatException, FileNotFoundException {
    int failed = 0;
    if(!testAccountBalance()) {
      System.out.println("Test account balance failed");
      failed++;
    }
    if(!testOverdraftCount()) {
      System.out.println("Test number of overdraft failed");
      failed++;
    }
    if (!testTransactionGroupInvalidEncoding()) {
      System.out.println("Test entering invalid encoding failed");
      failed++;
    }
    if (!testTransactionGroupEmpty()) {
      System.out.println("Test entering empty group failed");
      failed++;
    }
    if (!testAccountAddNegativeQuickWithdraw()) {
      System.out.println("Test quick withdraw with negative input failed");
      failed++;
    }
    if (!testAccountBadTransactionGroup()) {
      System.out.println("Test bad transaction group failed");
      failed++;
    }
    if (!testAccountIndexOutOfBounds()) {
      System.out.println("Test accound index out of bounds failed");
      failed++;
    }
    if (!testAccountMissingFile()) {
      System.out.println("Test non-existed file input failed");
      failed++;
    }
    if (failed == 0) {
      System.out.println("All tests passed!");
    }
  }

  /**
   * This method checks whether the calculation of balance is correct
   * when valid commands sent into the object
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBalance() throws DataFormatException {
    Account ac = new Account("Test");
    String command1 = "0 1 0 1";    // Balance: 1
    String command2 = "1 100 200 -10"; // Balance: 291
    String command3 = "2 1 1 0 1";  // Balance: 131
    ac.addTransactionGroup(command1);
    ac.addTransactionGroup(command2);
    ac.addTransactionGroup(command3);
    int balance = ac.getCurrentBalance();
    if (balance == 131) {
      System.out.println("Balance is correct");
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * This method checks whether the count of overdraft times is correct
   * when valid commands sent into the object
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testOverdraftCount() throws DataFormatException{
    Account ac = new Account("Test");
    String command1 = "0 1 0 0";    // Overdraft: +1
    String command2 = "1 100 -100 10"; // Overdraft: +1
    String command3 = "2 0 0 0 1";  // Balance: +1
    ac.addTransactionGroup(command1);
    ac.addTransactionGroup(command2);
    ac.addTransactionGroup(command3);
    int overDraft = ac.getNumberOfOverdrafts();
    if (overDraft == 3) {
      System.out.println("Number of overdraft is correct");
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when it is passed an empty int[].
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupEmpty() {
    try {
      int[] testArray = new int[3];
      testArray[0] = 10;
      TransactionGroup tg = new TransactionGroup(testArray);
      return false;
    } catch (DataFormatException e) {
      System.out.println("Expected DataFormatException caught");
      return true;
    }

  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when then first int in the provided array is not 0, 1, or 2.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupInvalidEncoding() {
    try {
      int[] testArray = new int[] {12, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      TransactionGroup tg = new TransactionGroup(testArray);
      return false;
    } catch (DataFormatException e) {
      System.out.println("Expected DataFormatException caught");
      return true;
    }
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a quick withdraw encoded group that contains negative
   * numbers of withdraws.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountAddNegativeQuickWithdraw() {
    try { // TODO: need to check
      Account ac = new Account("Test");
      String command = "2 0 1 2 -3 1";
      ac.addTransactionGroup(command);
      return false;
    } catch (DataFormatException e) {
      System.out.println("Expected DataFormatException caught");
      return true;
    } catch (OutOfMemoryError e) {
      System.out.println("Expected OutOfMemoryError caught");
      return true;
    }
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a string with space separated English words (non-int).
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBadTransactionGroup() {
    try {
      Account ad = new Account("Aaron");
      ad.addTransactionGroup("1 zwei deux tres");
      return false;
    } catch (DataFormatException e) {
      System.out.println("Expected DataFormatException caught");
      return true;
    }
  }

  /**
   * This method checks whether the Account.getTransactionAmount method throws an exception with an
   * appropriate message, when it is passed an index that is out of bounds.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountIndexOutOfBounds() {
    try {   // TODO: need to be fixed
      int[] testArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      TransactionGroup tg = new TransactionGroup(testArray);
      tg.getTransactionAmount(10);  // 10 is out of bound
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Expected OutOfBoundException caught");
      return true;
    } catch (DataFormatException e) {
      System.out.println("Expected DataFormatException caught");
      return true;
    }
  }

  /**
   * This method checks whether the Account constructor that takes a File parameter throws an
   * exception with an appropriate message, when it is passed a File object that does not correspond
   * to an actual file within the file system.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountMissingFile() {
    try {
      File file = new File("test.txt");
      Account ad = new Account(file);
      return false;
    } catch (FileNotFoundException e) { // DataFormatException will be thrown from account body
      System.out.println("Expected FileNotFoundException caught");
      return true;
    }

  }

}
