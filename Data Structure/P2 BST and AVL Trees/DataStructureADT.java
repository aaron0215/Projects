/**
 * A data structure that can store at least 500 key,value pairs.
 * 
 * CAUTION: The methods in this interface are similar, but not exactly the same as given in p1.
 *
 * May not use any of Java's built-in Java collection types:
 * such as: List, ArrayList, LinkedList, etc...
 * 
 * May not add any public members.
 * 
 * @author deppeler
 *
 * @param <K> The key must not be null and must be Comparable.
 * @param <V> The data value associated with a given key.
 */
public interface DataStructureADT<K extends Comparable<K>, V> {

    // Add the key,value pair to the data structure and increase the number of keys.
    // If key is null, throw IllegalNullKeyException;
    // If key is already in data structure, throw DuplicateKeyException();
    void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException;

    // If key is found, remove the key,value pair from the data structure and decrease num keys.
    // If key is null, throw IllegalNullKeyException
    // If key is not found, throw KeyNotFoundException().
    boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException;

    // Returns the value associated with the specified key
    // Does not remove key or decrease number of keys
    // If key is null, throw IllegalNullKeyException 
    // If key is not found, throw KeyNotFoundException().
    V get(K key) throws IllegalNullKeyException, KeyNotFoundException;

    // Returns true if the key is in the data structure
    // If key is null, throw IllegalNullKeyException 
    // Returns false if key is not null and is not present
    boolean contains(K key) throws IllegalNullKeyException;

    // Returns the number of key,value pairs in the data structure
    int numKeys();

}
