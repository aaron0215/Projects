// Title: Hashing Table
// Files: HashTable.java HashTableADT.java HashingTableTest.java
// Course: COMP SCI 400, Spring 2019
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Andrew Kuemmel
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

// This program uses CHAINED BUCKET: array of linked nodes to resolve collisions.

// Using built-in function hashCode() to convert
// keys to be hash code and use Math.floorMod method
// to modify the hash code with table size to hash index

/**
 * This class represents a hash table entends comparable objects and
 * implements HashTableADT class. It has capacity, loadFactor, loadFactorThreshold,
 * keyNums and an array table which is the hash table created.
 * @author Aaron Zhang
 *
 * @param <K> is the key
 * @param <V> is the paired value
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  int capacity; // Size of array (hash table)
  double loadFactor; // current load
  double loadFactorThreshold; //  the load factor that causes a resize and rehash
  int keyNums = 0;  // Number of keys in the table
  Node<K,V>[] table; // the table

  /**
   * Non-arg constructor. Set initial size to 11, loadFactorThreshold to 0.75
   */
  public HashTable() {
    loadFactor = 0;
    capacity = 11;
    loadFactorThreshold = 0.75;
    table = new Node[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = null;
    }
  }

  /**
   * Constructor initialize the hash table with given parameters
   * @param initialCapacity is the initial capacity
   * @param loadFactorThreshold is the load factor that causes a resize and rehash
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    capacity = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    loadFactor = 0;
    table = new Node[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = null; // initialize the table array
    }
  }

  /**
   * Insert pair key and value into the table.
   * Create node and create it into it.
   * @param key is the key of the new node
   * @param value is the paired value
   * @throws IllegalNullKeyException when the key is null
   * @throws DuplicateKeyException when the key is existed
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int index = Math.floorMod(key.hashCode(),capacity); // Get hash index
    Node<K, V> element = new Node<K, V>(key, value);
    if (table[index] == null) { // The first node in this array cell
      table[index] = element;
    } else {
      Node<K,V> curNode = table[index];
      while(curNode != null) { // check any duplicate
        if(!curNode.getKey().equals(key)) {
          curNode = curNode.getNext();
        } else { // found duplicate
          throw new DuplicateKeyException();
        }
      }
      // reach the end and not found duplicate
      curNode = element;
    }
    keyNums++;

    // Check if it needs to be resized
    loadFactor = (double)keyNums/capacity;
    if(loadFactor >= loadFactorThreshold) {
      capacity = 2*capacity+1; // update capacity
      table = rehashing(table);
    }
    loadFactor = (double)keyNums/capacity; // update load factor
  }
  
  /**
   * Private helper to do rehashing
   * Loop through all nodes and re-computer the index and add to the new table
   * @param table is the original table
   * @return the new table
   */
  private Node<K,V>[] rehashing(Node<K,V>[] table) {
    Node<K,V>[] temp = new Node[capacity];
    for (int i = 0; i < table.length; i++) {
      Node<K,V> curNode = table[i];
      while(curNode != null) { // go through nodes in original table
        // recompute
        int newIndex = Math.floorMod(table[i].getKey().hashCode(),capacity);
        Node<K,V> innerNode = temp[newIndex];
        if(temp[newIndex] == null) { // check collision
          temp[newIndex] = table[i];
        }
        while(innerNode != null) { // Go through all nodes in this linked list
          innerNode = innerNode.getNext();
        }
        innerNode = table[i];
        curNode = curNode.getNext(); // Go to the next node in the table
      }
    }
    return temp;
  }

  /**
   * Remove key from hash table
   * @param key is the key of node needs to be removed
   * @throws IllegalNullKeyException when key is null
   * @return true while removed otherwise false
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int index = Math.floorMod(key.hashCode(),capacity);
    if(table[index] != null && table[index].getKey().equals(key)) {
      table[index] = null; // remove the first node
      return true;
    }
    if(table[index] != null) {
      Node<K,V> curNode = table[index];
      while(curNode.getNext() != null) {
        if(!curNode.getNext().getKey().equals(key)) { // Loop through all nodes
          curNode = curNode.getNext();
        } else { // found
          curNode.setNext(null);
          keyNums--;
          loadFactor = (double)keyNums/capacity;
          return true;
        }
      }
    }
    // reach the end and still not found
    return false;
  }

  /**
   * Get the paired value of a certain key
   * @param key is the key of the node we are looking for
   * @throws IllegalNullKeyException when key is null
   * @throws KeyNotFoundException when key is not found
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int index = Math.floorMod(key.hashCode(),capacity);
    Node<K,V> curNode = table[index];
    while(curNode != null) {
      if(!curNode.getKey().equals(key)) {
        curNode = curNode.getNext(); // loop through all nodes
      } else {
        return curNode.getValue(); // return value when found
      }
    }
    // reach the end
    throw new KeyNotFoundException();
  }

  /**
   * return number of keys
   */
  @Override
  public int numKeys() {
    return keyNums;
  }

  /**
   * return load factor threshold
   */
  @Override
  public double getLoadFactorThreshold() {
    return loadFactorThreshold;
  }

  /**
   * return load factor
   */
  @Override
  public double getLoadFactor() {
    return loadFactor;
  }

  /**
   * return capacity
   */
  @Override
  public int getCapacity() {
    return capacity;
  }

  /**
   * return collision solution
   */
  @Override
  public int getCollisionResolution() {
    return 5;
  }
  
  /**
   * Inner class represnts a node
   * @author Aaron Zhang
   *
   * @param <K> is the key of the node
   * @param <V> is the value of the node
   */
  private class Node <K extends Comparable<K>, V>{
    private K key;
    private V value;
    private Node <K,V> next;
    private Node(){
      key = null;
      value = null;
      next = null;
    }
    private Node (K key, V value){
      this.key = key;
      this.value = value;
      next = null;
    }
    private void setKey(K key) {
      this.key = key;
    }
    private void setValue(V value) {
      this.value = value;
    }
    private K getKey() {
      return key;
    }
    private V getValue() {
      return value;
    }
    private void setNext(Node<K,V> next) {
      this.next = next;
    }
    private Node<K,V> getNext() {
      return next;
    }
  }
}
