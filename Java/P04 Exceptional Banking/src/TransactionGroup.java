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

public class TransactionGroup {

  // Enum type of all applicable transacton types
  private enum EncodingType {
    BINARY_AMOUNT, INTEGER_AMOUNT, QUICK_WITHDRAW
  };

  private EncodingType type;
  private int[] values;

  /**
   * Constructor that accepts an array of groupEncoding and convert them into transactions
   * 
   * @param groupEncoding which is an array of transaction codings
   * @throws DataFormatException which transaction group has invalid element
   */
  public TransactionGroup(int[] groupEncoding) throws DataFormatException {
    if (groupEncoding == null) {
      throw new DataFormatException("transaction group encoding cannot be null or empty");
    } else {
      if (groupEncoding[0] != 0 && groupEncoding[0] != 1 && groupEncoding[0] != 2) {
        throw new DataFormatException(
            "the first element within a transaction group must be 0, 1, or 2");
      } else {
        // 0, 1 and 2 refer to the index of the element in enum
        this.type = EncodingType.values()[groupEncoding[0]];
        // length - 1 gets rid of the first element which only means transaction type
        this.values = new int[groupEncoding.length - 1];
        // Transaction amount starts from the second element
        for (int i = 0; i < values.length; i++) {
          this.values[i] = groupEncoding[i + 1]; // All transaction values
        }
      }
    }
    // DataFormatException check
    for (int i = 0; i < this.values.length; i++) {
      if (groupEncoding[0] == 0 && this.values[i] != 1 && this.values[i] != 0) {
        throw new DataFormatException(
            "binary amount transaction groups may only contain 0s and 1s");
      } else if (groupEncoding[0] == 1 && this.values[i] == 0) {
        throw new DataFormatException("integer amount transaction groups may not contain 0s");
      } else if (groupEncoding[0] == 2 && this.values[i] < 0) {
        throw new DataFormatException(
            "quick withdraw transaction groups may not contain negative numbers");
      } else if (groupEncoding[0] == 2 && this.values.length != 4) {
        throw new DataFormatException("quick withdraw transaction groups must contain 5 elements");
      }
    }
    // End of DataFormatException check
  }

  /**
   * getTransactionCount methods goes through transactions and count them
   * 
   * @return transactionCount which is the number of transactions
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    switch (this.type) {
      case BINARY_AMOUNT:
        int lastAmount = -1;
        for (int i = 0; i < this.values.length; i++) {
          if (this.values[i] != lastAmount) {
            transactionCount++;
            lastAmount = this.values[i];
          }
        }
        break;
      case INTEGER_AMOUNT: // Each element represents one transaction
        transactionCount = values.length;
        break;
      case QUICK_WITHDRAW: // Each element means how many times of withdraw at certain amount
        for (int i = 0; i < this.values.length; i++)
          transactionCount += this.values[i];
    }
    return transactionCount;
  }

  /**
   * getTransactionAmount method goes through specific transaction at a certain index
   * location of transactionsGroup and calculates and returns the amount of it
   * 
   * @param transactionIndex is the index location if the transaction
   * @return the transaction amount if correct; otherwise, return -1
   */
  public int getTransactionAmount(int transactionIndex) {
    int transactionCount = 0;
    if (transactionIndex > this.values.length - 1) {
      throw new IndexOutOfBoundsException("the valid range of index is " + this.values.length
          + ", you cannot input" + transactionIndex);
    } else {
      switch (this.type) {
        case BINARY_AMOUNT:
          int lastAmount = -1;
          int amountCount = 0;
          for (int i = 0; i <= this.values.length; i++) {
            if (i == this.values.length || this.values[i] != lastAmount) {
              if (transactionCount - 1 == transactionIndex) {
                if (lastAmount == 0)
                  return -1 * amountCount;
                else
                  return +1 * amountCount;
              }
              transactionCount++;
              lastAmount = this.values[i];
              amountCount = 1;
            } else
              amountCount++;
            lastAmount = this.values[i];
          }
          break;
        case INTEGER_AMOUNT:
          return this.values[transactionIndex];
        case QUICK_WITHDRAW:
          final int[] QW_AMOUNTS = new int[] {-20, -40, -80, -100};
          for (int i = 0; i < this.values.length; i++) {
            for (int j = 0; j < this.values[i]; j++)
              if (transactionCount == transactionIndex)
                return QW_AMOUNTS[i];
              else
                transactionCount++;
          }
      }
    }
    return -1;
  }
}
