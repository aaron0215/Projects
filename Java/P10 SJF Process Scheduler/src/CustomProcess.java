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
 * This class represents a CustomProcess object which contains its own ID and burstTime
 * and also the ID of next item. It implements comparable interface over itself
 * @author Aaron Zhang, Yixing Tu
 */
public class CustomProcess implements java.lang.Comparable<CustomProcess> {

  private static int nextProcessId = 1; // the id to be assigned to the next process 
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution
  
  /**
   * Constructor assigns ID and burstTime to this object and increment next ID
   * @param burstTime is the burstTime of this object from console
   */
  public CustomProcess (int burstTime) { 
    PROCESS_ID = nextProcessId;
    this.burstTime = burstTime;
    nextProcessId++;
  }
  
  /**
   * @return PROCESS_ID which is the ID of this object
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * @return burstTime which is the required execution time of this object
   */
  public int getBurstTime() {
    return burstTime;
  }
  
  /**
   * Compares the burstTime of this object and the other one.
   * If they have same burstTime, then compare PROCESS_ID
   * @return the subtraction of compared fields
   */
  @Override
  public int compareTo(CustomProcess other) {
    // Smaller one has higher priority
    if(this.burstTime == other.getBurstTime())
      return this.PROCESS_ID - other.PROCESS_ID;
    return this.burstTime - other.getBurstTime();
  }

}
