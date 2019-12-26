/**
 * A data structure that can store at least 500 key,value pairs.
 *
 * May not use any of Java's built-in Java collection types:
 * such as: List, ArrayList, LinkedList, etc...
 * 
 * May not add any public members (fields, methods, inner classes)
 * 
 * @author deppeler
 *
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 */
public interface DataStructureADT<K extends Comparable<K>, V> {

    // Add the key,value pair to the data structure and increases size.
    // If key is null, throws IllegalArgumentException("null key");
    // If key is already in data structure, throws RuntimeException("duplicate key");
    // can accept and insert null values
    void insert(K key, V value);

    // If key is found, Removes the key from the data structure and decreases size
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    // If key is not found, returns false.
    boolean remove(K key);

    // Returns the value associated with the specified key
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    V get(K key);

    // Returns true if the key is in the data structure
    // Returns false if key is null or not present
    boolean contains(K key);

    // Returns the number of elements in the data structure
    int size();

}
