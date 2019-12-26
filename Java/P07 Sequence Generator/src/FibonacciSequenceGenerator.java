/**
 * File header comes here
 */

import java.util.Iterator;

/**
 * Javadoc method header comes here
 *
 */
public class FibonacciSequenceGenerator implements Iterator<Integer> {
  private final int SIZE; // number of elements in this sequence
  private int prev; // previous item in the sequence with respect to the current iteration
  private int next; // next item in the sequence with respect to the current iteration
  private int generatedCount; // number of items generated so far
  

  // constructor
  public FibonacciSequenceGenerator(int size) {
    if (size <= 0)
      throw new IllegalArgumentException(
          "WARNING: CANNOT create a sequence with size <= zero.");

    this.SIZE = size;
    prev = 0;
    next = 1;
    generatedCount = 0;
  }


  //Override hasNext() and next() methods
  @Override
  public boolean hasNext() {
    // TODO Your code comes here
    // Time Complexity: O(1)
    if(generatedCount < SIZE)
      return true;
    else
      return false;
  }

  @Override
  public Integer next() {
    // TODO Your code comes here
    // Time Complexity: O(1)
    if (!hasNext())
      return null;
    int current = prev;
    prev = next;
    next = current + prev;
    generatedCount++;
    return current;
  }

  // You CAN add public accessor and mutator methods as needed here in order to implement your test methods

  public int getSize() {
    return SIZE;
  }
}