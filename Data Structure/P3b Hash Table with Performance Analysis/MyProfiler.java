/**
 * Filename: MyProfiler.java Project: p3b-201903 Authors: Aaron Zhang, Lecture 004
 *
 * Semester: Spring 2019 Course: CS400
 * 
 * Due Date: 3/28/2019
 * 
 * Credits: None
 * 
 * Bugs: None
 */

// Used as the data structure to test our hash table against
import java.util.TreeMap;

/**
 * This is a profiler class which will be used to compare HashTable class we made
 * with java built-in treemap
 * @author Aaron Zhang
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class MyProfiler<K extends Comparable<K>, V> {

  HashTableADT<K, V> hashtable; // HashTable instance
  TreeMap<K, V> treemap; // TreeMap instance

  /**
   * Constructor. Instantiate your HashTable and Java's TreeMap
   */
  public MyProfiler() {
      hashtable = new HashTable<K, V>();
      treemap = new TreeMap<K, V>();
    }

  /**
   * Insert K,V into both data structures
   * @param key is the key of inserting element
   * @param value is the paired value
   * @throws IllegalNullKeyException when key is null
   * @throws DuplicateKeyException when key is existed
   */
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    hashtable.insert(key,value);
    treemap.put(key, value);
  }

  /**
   * Get value V for key K from data structures
   * @param key is the key we are looking for
   * @throws IllegalNullKeyException when key is null
   * @throws KeyNotFoundException when key is not existed
   */
  public void retrieve(K key) throws IllegalNullKeyException, KeyNotFoundException {
    hashtable.get(key);
    treemap.get(key);
  }

  /**
   * Main driver to create a profile object.
   * execute the insert method of profile as many times as numElements
   * execute the retrieve method of profile as many times as numElements
   * @param args
   */
  public static void main(String[] args) {
    try {
      int numElements = Integer.parseInt(args[0]);
      MyProfiler<Integer, Integer> profiler = new MyProfiler<Integer, Integer>();
      for (int i = 0; i < numElements; i++) {
        profiler.insert(i,i); // Insert
      }
      for (int i = 0; i < numElements; i++) {
        profiler.retrieve(i); // Retrieve
      }
      String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
      System.out.println(msg);
    } catch (Exception e) {
      System.out.println("Usage: java MyProfiler <number_of_elements>");
      System.exit(1);
    }
  }
}
