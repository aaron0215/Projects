import java.util.Iterator;
import java.util.ArrayList;

public class DigitProductSequenceGenerator implements Iterator<Integer> {
  private final int INIT; // initial number
  private final int SIZE; // size of sequence
  private ArrayList<Integer> sequence; // ArrayList object storing the sequence

  public DigitProductSequenceGenerator(int init, int size) {
    if (size <= 0)
      throw new IllegalArgumentException("WARNING: CANNOT create a sequence with size <= zero.");
    if (init < 0)
      throw new IllegalArgumentException("WARNING: The starting element for digit product "
          + "sequence cannot be less than or equal to zero.");
    INIT = init;
    SIZE = size;
    sequence = new ArrayList<Integer>();
    this.generateSequence();
  }

  public void generateSequence() {
    // TODO: double-check
    sequence.clear();
    int count = 0;
    int product, prev;
    String temp;
    Integer integer = Integer.valueOf(INIT);
    //sequence.add(new Integer(INIT)); // Initialize the first element
    sequence.add(integer);
    while(count < SIZE -1 ) {
      temp = Integer.toString(sequence.get(count));
      product = 1;
      for(int i = 0; i < temp.length(); i++) {
        if(temp.charAt(i) != '0') {
          product *= Character.getNumericValue(temp.charAt(i));
        }
      }
      prev = sequence.get(count);
      integer = Integer.valueOf(prev + product);
      //sequence.add(new Integer(prev + product));
      sequence.add(integer);
      count++;
    }
  }
  
  public Iterator<Integer> getIterator() {
    // TODO return an Iterator to iterate over the ArrayList sequence field
    return sequence.listIterator();
  }
  
  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Integer next() {
    // TODO Auto-generated method stub
    return null;
  }
  
  public int getSize() {
    return sequence.size();
  }

}
