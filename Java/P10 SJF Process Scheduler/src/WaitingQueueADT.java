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
 * This interface is the abstract data type queue represents the pattern for
 * ready process waiting list. It is a generic interface
 * @author Aaron Zhang, Yixing Tu
 * @param <T> is the generic type inherits Comparable generic type
 */
public interface WaitingQueueADT<T extends Comparable<T>> {

  /**
   * Inserts a newObject in the priority queue
   * @param newObject is the object needs to be inserted
   */
  public void enqueue(T newObject);

  /**
   * Removes and returns the item with the highest priority
   * @return the removed item
   */
  public T dequeue();

  /**
   * Returns without removing the item with the highest priority
   * @return the top item
   */
  public T peek();

  /**
   * Getter of the size
   * @return size of the waiting queue
   */
  public int size();
  
  /**
   * Check if the waiting queue is empty
   * @return true if it's empty, otherwise, false
   */
  public boolean isEmpty();
}