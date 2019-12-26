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

import java.io.*;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Account {

  private static final int MAX_GROUPS = 10000;
  private static int nextUniqueId = 1000;

  private String name;
  private final int UNIQUE_ID;
  private TransactionGroup[] transactionGroups;
  private int transactionGroupsCount;

  /**
   * Constructor. Set the name as user's input and initialize all fields
   * 
   * @param name is the name of account holder from user
   */
  public Account(String name) {
    this.name = name;
    this.UNIQUE_ID = Account.nextUniqueId;
    Account.nextUniqueId++;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS]; // Array of TransactionGroup objects
    this.transactionGroupsCount = 0;
  }

  /**
   * Constructor with an input file object. Read from file after check if the
   * file is existed and pass into the values to fields from the file.
   * 
   * @param file is the File object from the user
   * @throws FileNotFoundException while the file is not found and print the name of the file
   */
  public Account(File file) throws FileNotFoundException {
    Scanner in = new Scanner(file);
    this.name = in.nextLine();
    this.UNIQUE_ID = Integer.parseInt(in.nextLine());
    Account.nextUniqueId = this.UNIQUE_ID + 1;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;
    String nextLine = "";
    while (in.hasNextLine()) {  // Read from the file
      try { // Catch the DataFormatException and continues reading after catching
        this.addTransactionGroup(in.nextLine());
      } catch (DataFormatException e) {
        System.out.println("DataFormatException occured");
      }
    }
    in.close();
  }

  /**
   * getId() method is used to return UNIQUE_ID when needed
   * 
   * @return UNIQUE_ID of this object(Account)
   */
  public int getId() {
    return this.UNIQUE_ID;
  }

  /**
   * addTransactionGroup method is used to add transactions into the transactionGroups
   * 
   * @param command is the user's input
   * @throws DataFormatException while command contains strings other than space and number
   * @throws OutOfMemoryError while the transactionGroup is full
   */
  public void addTransactionGroup(String command) throws DataFormatException {
    try {
      boolean passTest = true;
      for (char ch : command.toCharArray()) {
        if (!(Character.isDigit(ch) || ch == ' ' || ch == '-' || ch == '+')) {
          passTest = false;
        }
      }
      if (passTest && transactionGroupsCount != MAX_GROUPS) {
        String[] parts = command.split(" ");
        int[] newTransactions = new int[parts.length];
        for (int i = 0; i < parts.length; i++)
          newTransactions[i] = Integer.parseInt(parts[i]);
        TransactionGroup t = new TransactionGroup(newTransactions);
        this.transactionGroups[this.transactionGroupsCount] = t; // Add TransactionGroup object
        this.transactionGroupsCount++;
      }
      if (!passTest) {
        throw new DataFormatException(
            "addTransactionGroup requires string commands that contain only space separated integer values");
      }
      if (transactionGroupsCount > MAX_GROUPS) {
        throw new OutOfMemoryError("The capacity of this Account is " + MAX_GROUPS
            + " transactions which is already full");
      }
    } catch (RuntimeException e) {
      // Do nothing
    }
  }

  /**
   * getTransactionCount method calculates and returns the transactionCount
   * 
   * @return transactionCount which is the number of existed transactions
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    for (int i = 0; i < this.transactionGroupsCount; i++)
      transactionCount += this.transactionGroups[i].getTransactionCount();
    return transactionCount;
  }

  /**
   * getTransactionAmount methods returns the amount of the transaction
   * at the specific index location inside the transactionGroup
   * 
   * @param index is the location of the transaction that user is looking for
   * @return the amount if correct. Otherwise, return -1.
   */
  public int getTransactionAmount(int index) {
    try {
      int transactionCount = 0;
      for (int i = 0; i < this.transactionGroupsCount; i++) {
        int prevTransactionCount = transactionCount;
        transactionCount += this.transactionGroups[i].getTransactionCount();
        if (transactionCount > index) {
          index -= prevTransactionCount;
          return this.transactionGroups[i].getTransactionAmount(index);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      throw e;
    }
    return -1;
  }

  /**
   * getCurrentBalance() method calculates and returns the current balance
   * 
   * @return balance which is the current balance
   */
  public int getCurrentBalance() {
    int balance = 0;
    int size = this.getTransactionCount();
    for (int i = 0; i < size; i++)
      balance += this.getTransactionAmount(i);
    return balance;
  }

  /**
   * getNumberOfOverdrafts() methods calculates and returns the number of overdrafts
   * 
   * @return overdraftCount is the times of overdrafts
   */
  public int getNumberOfOverdrafts() {
    int balance = 0;
    int overdraftCount = 0;
    int size = this.getTransactionCount();
    for (int i = 0; i < size; i++) {
      int amount = this.getTransactionAmount(i);
      balance += amount;
      if (balance < 0 && amount < 0)
        overdraftCount++;
    }
    return overdraftCount;
  }

}
