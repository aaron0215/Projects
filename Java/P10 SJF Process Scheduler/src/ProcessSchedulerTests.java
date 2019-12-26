// Title: SJF Process Scheduler
// Files: CustomProcess.java CustomProcessQueue.java ProcessScheduler.java 
// ProcessSchedulerTests.java WaitingQueueADT.java
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
 * This is the test class
 * @author Aaron Zhang, Yixing Tu
 */
public class ProcessSchedulerTests {

  /**
   * Main method runs all tests and print out results respectively
   * @param args
   */
  public static void main(String[] args) {
    int failed = 0;
    if(!testCustomProcess()) {
      System.out.println("Test of constructor of the CustomProcess class failed");
      failed++;
    }
    if(!testCompareTo()) {
      System.out.println("Test of compareTo method of the CustomProcess class failed");
      failed++;
    }
    if(!testEnqueueCustomProcessQueue()) {
      System.out.println("Test of enqueue method of CustomProcessQueue class failed");
      failed++;
    }
    if(!testDequeueCustomProcessQueue()) {
      System.out.println("Test of dequeue method of CustomProcessQueue class failed");
      failed++;
    }
    if(failed == 0) {
      System.out.println("All tests passed");
    }
  }
  
  /**
   * Check constructor of CustomProcess class
   * @return true if test passed, otherwise, false
   */
  public static boolean testCustomProcess() {
    CustomProcess test = new CustomProcess(10);
    return (test.getBurstTime() == 10 && test.getProcessId() == 1);
  }
  
  /**
   * Check CompareTo method of CustomProcess class
   * @return true if test passed, otherwise, false
   */
  public static boolean testCompareTo() {
    CustomProcess test1 = new CustomProcess(20);
    CustomProcess test2 = new CustomProcess(10);
    if(test1.compareTo(test2) > 0) {
      return true;
    }
    return false;
  }
  
  /**
   * checks the correctness of the enqueue 
   * operation implemented in the CustomProcessQueue class
   * @return true if test passed, otherwise, false
   */
  public static boolean testEnqueueCustomProcessQueue(){
    CustomProcess process1 = new CustomProcess(20);
    CustomProcess process2 = new CustomProcess(10);
    CustomProcess process3 = new CustomProcess(30);
    CustomProcess process4 = new CustomProcess(5);
    CustomProcess process5 = new CustomProcess(25);
    CustomProcessQueue testQueue = new CustomProcessQueue();
    testQueue.enqueue(process1);
    testQueue.enqueue(process2);
    testQueue.enqueue(process3);
    testQueue.enqueue(process4);
    testQueue.enqueue(process5);
    return testQueue.peek().getBurstTime() == 5;
  }
  
  /**
   * checks the correctness of the dequeue 
   * operation implemented in the CustomProcessQueue class
   * @return true if test passed, otherwise, false
   */
  public static boolean testDequeueCustomProcessQueue(){
    boolean passed = false;
    CustomProcess process1 = new CustomProcess(20);
    CustomProcess process2 = new CustomProcess(10);
    CustomProcess process3 = new CustomProcess(30);
    CustomProcess process4 = new CustomProcess(5);
    CustomProcess process5 = new CustomProcess(25);
    CustomProcessQueue testQueue = new CustomProcessQueue();
    testQueue.enqueue(process1);
    testQueue.enqueue(process2);
    testQueue.enqueue(process3);
    testQueue.enqueue(process4);
    testQueue.enqueue(process5);
    CustomProcess dequeue = testQueue.dequeue();
    if(dequeue.getBurstTime() == 5 && testQueue.peek().getBurstTime() == 10) {
      passed = true;
    }
    return passed;
  }

}
