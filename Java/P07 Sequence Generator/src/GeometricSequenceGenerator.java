/**
 * File header comes here
 */

import java.util.Iterator;
// ADD import statements here as needed

/**
 * Javadoc class header comes here
 *
 */
public class GeometricSequenceGenerator implements Iterator<Integer> {
  private final int SIZE; // The number of elements in this sequence
  private final int INIT; // The first term in this sequence
  private final int RATIO; // The common ratio for this sequence
  private int next; // The next term in the sequence

  private int generatedCount; // The number of terms generated so far in this sequence
  // It refers also to the order of the next number to be generated by next() method

  // constructor
  public GeometricSequenceGenerator(int init, int ratio, int size) {
    // TODO implement this constructor
    // check for the precondition: size > 0, throws an IllegalArgumentException if this
    // precondition is not satisfied
    if (init < 0 || ratio <= 0)
      throw new IllegalArgumentException(
          "WARNING: The starting element and the common ratio for a geometric progression "
              + "should be STRICTLY POSITIVE.");
    // check for the validity of init (>=0) and ratio (>0), throw an IllegalArgumentException
    // if these two parameters are not valid
    if (size <= 0)
      throw new IllegalArgumentException("WARNING: CANNOT create a sequence with size <= zero.");

    // initialize all fields
    this.SIZE = size;
    this.INIT = init;
    this.RATIO = ratio;
    next = init; // initializes next to the first element in this geometric progression
    generatedCount = 0;
  }

  // TODO implement hasNext(), and next() methods here
  @Override
  public boolean hasNext() {
    // TODO Your code comes here
    // Time Complexity: O(1)
    if (generatedCount < SIZE)
      return true;
    else
      return false;
  }

  @Override
  public Integer next() {
    // TODO your code comes here
    // Time Complexity: O(1)
    if(!hasNext()) {
      return null;
    } else {
      int curNum = next;
      next = next*RATIO;
      generatedCount++;
      return curNum;
    }
  }

  // You can add local variable to your hasNext() or next() method to implement their correct
  // behavior if needed
  // You can add public getters and setters methods as needed to implement your test methods here
  // You CANNOT define mutators for any final field
  
  public int getSize() {
    return SIZE;
  }


}
