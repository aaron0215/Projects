import java.util.Iterator;

public class SequenceGeneratorTests {

  public static void main(String[] args) {
    int failed = 0;
    if (!geometricSequenceGeneratorTest()) {
      failed++;
      System.out.println("geometricSequenceGeneratorTest() failed");
    }
    if (!fibonacciSequenceGeneratorTest()) {
      failed++;
      System.out.println("fibonacciSequenceGeneratorTest() failed");
    }
    if (!digitProductSequenceGeneratorTest()) {
      failed++;
      System.out.println("digitProductSequenceGeneratorTest() failed");
    }
    if (failed == 0) {
      System.out.println("All tests passed");
    }
  }

  public static boolean geometricSequenceGeneratorTest() {
    boolean passed = false;
    GeometricSequenceGenerator test = new GeometricSequenceGenerator(5, 2, 6);
    if (test.getSize() == 6 && test.hasNext() && test.next() == 5) {
      passed = true;
    } else {
      System.out.print("Constructor does not work");
    }
    return passed;
  }

  public static boolean fibonacciSequenceGeneratorTest() {
    boolean passed = false;
    FibonacciSequenceGenerator test = new FibonacciSequenceGenerator(10);
    if (test.getSize() == 10 && test.hasNext() && test.next() == 0) {
      passed = true;
    } else {
      System.out.print("Constructor does not work");
    }
    return passed;
  }

  public static boolean digitProductSequenceGeneratorTest() {
    boolean passed = false;
    try {
      DigitProductSequenceGenerator test = new DigitProductSequenceGenerator(-13, 5);
      System.out.print("Try-catch does not work");
    } catch (IllegalArgumentException e) {
      passed = true;
    }
    DigitProductSequenceGenerator test2 = new DigitProductSequenceGenerator(13, 5);
    Iterator<Integer> testIterator = test2.getIterator();
    if (test2.getSize() == 5 && testIterator != null) {
      // Do nothing keep passed true
    } else {
      passed = false;
      System.out.print("Constructor does not work");
    }
    return passed;
  }
}
