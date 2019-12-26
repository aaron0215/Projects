//SampleApplication.java
import java.util.*;
public class SampleProfilerApplication {
     public static void main(String args[]) {
        try {
            long numElements = Long.parseLong(args[0]);
            ArrayList<Long> elements = new ArrayList<Long>();
            for (long i = 0; i < numElements; i++) {
                elements.add(i); 
            }
            System.out.println(String.format("inserted %d elements into the hash set", numElements));
        } catch (Exception e) {
            System.out.println("Usage: java SampleApplication <num_elements_as_positive_integer>");
             System.exit(1);
         }
    }
}
