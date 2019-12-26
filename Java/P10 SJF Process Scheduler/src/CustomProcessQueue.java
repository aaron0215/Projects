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

import java.util.Arrays;

/**
 * This class represents a min-heap queue stores CustomProcess objects
 * It implements the WaitingQueueADT interface
 * @author Aaron Zhang, Yixing Tu
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
  private CustomProcess[] heap; // over-sized array-based min-heap storing the data
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  // NOTE: child index i, parent index i/2 in this case since we start at 1

  /**
   * Constructor initializes the heap and size
   */
  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY]; // make it over-sized for now
    size = 0;
  }
  
  /**
   * Add new object into the heap and sort the data
   * @param newObject is the object to be added
   */
  @Override
  public void enqueue(CustomProcess newObject) {
    if (size >= heap.length) { // Reaches the end of this heap
      // expand array (simply double its size)
      heap = Arrays.copyOf(heap, heap.length * 2);
    }
    heap[++size] = newObject; // Add newObject into the end of heap
    minHeapPercolateUp(size); // Percolate up the object
  }

  /**
   * Remove and return the object with highest priority
   * And sort the data
   * @return the removed object
   */
  @Override
  public CustomProcess dequeue() {
    CustomProcess bestProcess;
    if (size < 1) { // Empty heap, so returns null
      bestProcess = null;
    } else {
      bestProcess = heap[1]; // Item with highest priority is always at the top of min-heap
      heap[1] = heap[size]; // Move the object at the bottom to the top
      heap[size] = null; // Set the end node to be null
      size--; // Decrement the size
      minHeapPercolateDown(1); // Percolate down the object moved to the top just now
    }
    return bestProcess; // Return the removed object
  }

  /**
   * @return the top object in this queue (highest prior object in heap)
   */
  @Override
  public CustomProcess peek() {
    return heap[1];
  }

  /**
   * @return the size of this heap
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * @return if the heap is null
   */
  @Override
  public boolean isEmpty() {
    return heap[1] == null; // empty heap <-> root is null
  }

  /**
   * This method percolates up the element at given index in this heap
   * @param index is the index of the element we need to work on
   */
  private void minHeapPercolateUp(int index) {
    if(index > 1) { // If index == 1, it's the root which can't be percolated up
      
      // Since we started at index 1, to get the parent and child index,
      // the index of the parent would be index/2
      
      CustomProcess child = heap[index]; // child node is at the current index
      CustomProcess parent = heap[index/2]; // get parent
      if(parent.compareTo(child) > 0) { 
        // if parent > child (parent has higher priority), then swap them
        heap[index/2] = child;
        heap[index] = parent;
      }
      minHeapPercolateUp(index/2); // Operate recursively at new index after swap
    }
  }

  /**
   * This method percolates down the element at given index in this heap
   * @param index is the index of the element we need to work on
   */
  private void minHeapPercolateDown(int index) {
    if (index != size) { // If index == size, it's the bottom which can't be percolated down
      CustomProcess parent = heap[index]; // parent node is at the current index
      
      // Since we started at index 1, to get the parent and child index,
      // the index left leaf would be 2*index, right would be 2*index + 1 respectively
      
      CustomProcess childLeft = heap[2 * index]; // child on the left path
      CustomProcess childRight = heap[2 * index + 1]; // child on the right path
      int swapIndex = 0; // index of the element might be swapped
      if(childLeft != null) { // Since heap usually starts from the left
                              // When it's null, it means we reach the end
        if(childRight == null) { // If there is no right leaf, only need to consider the left leaf
          swapIndex = 2*index;
        } else if(childLeft.compareTo(childRight) > 0) { // Get swapIndex accordingly
          swapIndex = 2*index + 1;
        } else { // Same node usually goes left
          swapIndex = 2*index;
        }
        if (parent.compareTo(heap[swapIndex]) > 0) { // Comparison starts from here
          // Swap parent and child only if parent > child (parent has higher priority)
          CustomProcess temp = heap[swapIndex];
          heap[index] = temp;
          heap[swapIndex] = parent;
          minHeapPercolateDown(swapIndex); // Operate recursively at new index after swap
        }
      }
    }
  }

}
