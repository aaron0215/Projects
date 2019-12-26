import java.util.Arrays;

public class AuditableBankingTests {

  public static void main(String[] args) {

    boolean foundA = testProcessCommand();
    boolean foundB = testCalculateCurrentBalance();
    boolean foundC = testCalculateNumbersofOverdrafts();
    if (!(foundA && foundB && foundC)) {
      System.out.println("Passed all tests");
    }

  }

  /**
   * Test the processCommand method. If the first element of input is 0,1 or 2, add transactions to
   * allTransactions array. Otherwise, nothing will be added. Check transactionCount to test.
   * 
   * @return the result of test.
   */
  public static boolean testProcessCommand() {
    boolean foundProblem = false;
    int[][] allTransactions = new int[100][];
    int transCount = 0;
    String commandA = "1 10 -20 +30 -20 -20";   // Assumed command
    String commandB = "0 1 1 1 0 0 1 1 1 1";
    String commandC = "B";

    // Since the first element of commandA and commandB is 1 and 0, we expect the processCommand
    // will convert command and add them into allTransactions array and count plus one
    transCount = AuditableBanking.processCommand(commandA, allTransactions, transCount);
    if (transCount != 1) {
      System.out.println("Failed, the transactionsCount should be 1");
      foundProblem = true;
    } else {
      System.out.println("Pass 1/3 testProcessCommand");
    }

    transCount = AuditableBanking.processCommand(commandB, allTransactions, transCount);
    if (transCount != 2) {
      System.out.println("Failed, the transactionsCount should be 2");
      foundProblem = true;
    } else {
      System.out.println("Pass 2/3 testProcessCommand");
    }

    // Since the first element of commandC is B, we expect the number of transaction will not increase
    // which means the processCommand will not convert and add this array into allTransactions array
    transCount = AuditableBanking.processCommand(commandC, allTransactions, transCount);
    if (transCount != 2) {
      System.out.println("Failed, the transactionsCount should be 2");
      foundProblem = true;
    } else {
      System.out.println("Pass 3/3 testProcessCommand");
    }
    
    return foundProblem;
  }

  /**
   * Test calculateCurrentBalance method. Keep tracking the balance after each transaction is
   * applied. If the balance after each transaction is correct, pass it. Otherwise, failed.
   * 
   * @return the result of test.
   */
  public static boolean testCalculateCurrentBalance() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {    // all expected values below
      {1, 10, -20, +30, -20, -20}, // +2 overdrafts (ending balance: -20)
      {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (ending balance: -15)
      {1, 115}, // +0 overdrafts (ending balance: +100)
      {2, 3, 1, 0, 1}, // +1 overdrafts (ending balance: -100)
    };

    // test with a single transaction of the Integer Amount encoding
    // test the current balance the method returns
    int transactionCount = 1;
    int balance = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
    if (balance != -20) {
      System.out
          .println("FAILURE: calculateCurrentBalance did not return -20 when transactionCount = 1, "
              + "and transactions contained: " + Arrays.deepToString(transactions));
    } else
      System.out.println("PASSED TEST 1/3 of TestCalculateCurrentBalance!!!");

    // test with two transactions: including one of each encoding
    // test the current balance the method returns
    transactionCount = 2;
    balance = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
    if (balance != -15) {
      System.out
          .println("FAILURE: calculateCurrentBalance did not return -15 when transactionCount = 2, "
              + "and transactions contained: " + Arrays.deepToString(transactions));
    } else
      System.out.println("PASSED TESTS 2/3 of TestCalculateCurrentBalance!!!");

    // test with four transactions
    // test the current balance the method returns
    transactionCount = 4;
    balance = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
    if (balance != -100) {
      System.out.println(
          "FAILURE: calculateCurrentBalance did not return -100 when transactionCount = 3, "
              + "and transactions contained: " + Arrays.deepToString(transactions));
    } else
      System.out.println("PASSED TEST 3/3 of TestCalculateCurrentBalance!!!");
    
    return !foundProblem;
  }

  /**
   * Test calculateNumbersofOverdrafts method. Determine and count the number of overdraft after
   * each transaction is completed. If the result is correct, pass it. Otherwise, failed.
   * 
   * @return the result of test.
   */
  public static boolean testCalculateNumbersofOverdrafts() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {    // all expected values below
      {1, 10, -20, +30, -20, -20}, // +2 overdrafts (ending balance: -20)
      {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (ending balance: -15)
      {1, 115}, // +0 overdrafts (ending balance: +100)
      {2, 3, 1, 0, 1}, // +1 overdrafts (ending balance: -100)
    };

    // test with a single transaction of the Integer Amount encoding
    // test overdrafts counter
    int transactionCount = 1;
    int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 2) {
      System.out.println(
          "FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, "
              + "and transactions contained: " + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");

    // test with four transactions: including one of each encoding
    // test overdrafts counter
    transactionCount = 4;
    overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 5) {
      System.out.println(
          "FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, "
              + "and transactions contained: " + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");

    return !foundProblem;
  }

}
