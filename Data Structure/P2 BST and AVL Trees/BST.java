// Title: AVL trees
// Files: BST.java AVL.java BSTNode.java BSTTest.java AVLTest.java
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

import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>

/**
 * This class represents a binary search tree
 * 
 * @author Aaron Zhang
 *
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {
    root = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPreOrderTraversal()
   */
  @Override
  public List<K> getPreOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>();
    if (root == null) {
      return list; // return empty list while root is null
    }
    list = preOrderHelper(root, list);
    return list;
  }

  private ArrayList<K> preOrderHelper(BSTNode<K, V> root, ArrayList<K> list) {
    if (root == null) {
      return null;
    }
    list.add((K) root.key); // add current key
    preOrderHelper(root.left, list); // recursively go through whole tree
    preOrderHelper(root.right, list);
    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPostOrderTraversal()
   */
  @Override
  public List<K> getPostOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>();
    if (root == null) {
      return list; // return empty list while root is null
    }
    list = postOrderHelper(root, list);
    return list;
  }

  private ArrayList<K> postOrderHelper(BSTNode<K, V> root, ArrayList<K> list) {
    if (root == null) {
      return null;
    }
    postOrderHelper(root.left, list); // recursively go through whole tree
    postOrderHelper(root.right, list);
    list.add((K) root.key); // add current key
    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getLevelOrderTraversal()
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>();
    if (root == null) {
      return list; // return empty list while root is null
    }
    int h = getHeight();
    int i;
    for (i = 1; i <= h; i++) { // loop through all levels
      levelOrderHelper(root, list, i);
    }
    return list;
  }

  private void levelOrderHelper(BSTNode<K, V> root, ArrayList<K> list, int level) {
    if (root == null) {
      return;
    }
    if (level == 1) {
      list.add((K) root.key); // add current key
    } else if (level > 1) {
      levelOrderHelper(root.left, list, level - 1); // recursively go through all levels
      levelOrderHelper(root.right, list, level - 1);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getInOrderTraversal()
   */
  @Override
  public List<K> getInOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>();
    if (root == null) {
      return list; // return empty list while root is null
    }
    list = inOrderHelper(root, list);
    return list;
  }

  private ArrayList<K> inOrderHelper(BSTNode<K, V> root, ArrayList<K> list) {
    if (root == null) {
      return null;
    }
    inOrderHelper(root.left, list); // recursively go through whole tree
    list.add((K) root.key); // add current key
    inOrderHelper(root.right, list);
    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    root = insertHelper(root, key, value);
  }

  /**
   * Private helper to do insertion
   * @param root is the current node
   * @param key is the key of new node
   * @param value is the value of new node
   * @return the whole three
   * @throws DuplicateKeyException while the key is existed
   */
  private BSTNode<K, V> insertHelper(BSTNode<K, V> root, K key, V value)
      throws DuplicateKeyException {
    if (root == null) {
      root = new BSTNode<K, V>(key, value);
      numKeys++;
      return root;
    }
    if (key.compareTo(root.key) == 0) { // Find duplicate key, throw exception
      throw new DuplicateKeyException();
    } else if (key.compareTo(root.key) > 0) { // Goes to right if key is larger
      root.right = insertHelper(root.right, key, value);
    } else {    // Goes to left if key is smaller
      root.left = insertHelper(root.left, key, value);
    }
    return root;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (contains(key)) {
      root = removeHelper(root, key);
      numKeys--;
      return true;
    } else {
      throw new KeyNotFoundException();
    }
  }

  /** 
   * @param root is the current node
   * @param key key is the key of the node we want to remove
   * @return the tree after removal
   * @throws KeyNotFoundException while the key is not found
   */
  private BSTNode<K, V> removeHelper(BSTNode<K, V> root, K key) throws KeyNotFoundException {
    if (root == null) {
      return null;
    }
    if (key.compareTo(root.key) < 0) {
      root.left = removeHelper(root.left, key);
    } else if (key.compareTo(root.key) > 0) {
      root.right = removeHelper(root.right, key);
    } else { // found
      if (root.left == null && root.right == null) { // no child
        return null;
      } else if (root.left == null && root.right != null) { // one child
        root = root.right;
      } else if (root.left != null && root.right == null) { // one child
        root = root.left;
      } else { // Two children
        // Goes to the right to find in-order successor (smallest node in the right side)
        BSTNode<K, V> temp = findInorderSuccessor(root.right);
        root.key = temp.key;
        root.value = temp.value;
        root.right = removeHelper(root.right, temp.key);
      }
    }
    return root;
  }

  /**
   * Private helper to find in-order successor
   * Gives the value to current node and set it null
   * @param node is the current node
   * @return current node after setting
   */
  private BSTNode<K, V> findInorderSuccessor(BSTNode<K, V> node) {
    BSTNode<K, V> inorderSuccessor = node;
    while (inorderSuccessor.left != null) { // Goes to the left as far as possible
      inorderSuccessor = node.left;
      node = node.left;
    }
    BSTNode<K, V> temp = inorderSuccessor;
    inorderSuccessor = null; // Remove the successor
    return temp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> curNode = root;
    while (curNode != null) {
      if (key.compareTo((K) curNode.key) < 0) {
        curNode = curNode.left;
      } else if (key.compareTo((K) curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (V) curNode.value;
      }
    }
    // Not found or root is null
    throw new KeyNotFoundException();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> curNode = root;
    while (curNode != null) {
      if (key.compareTo((K) curNode.key) < 0) {
        curNode = curNode.left;
      } else if (key.compareTo((K) curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return true;
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    if (root != null) {
      return root.key;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> curNode = root;
    while (curNode != null) {
      if (key.compareTo((K) curNode.key) < 0) {
        curNode = curNode.left;
      } else if (key.compareTo((K) curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (K) curNode.left.key;
      }
    }
    throw new KeyNotFoundException();
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> curNode = root;
    while (curNode != null) {
      if (key.compareTo((K) curNode.key) < 0) {
        curNode = curNode.left;
      } else if (key.compareTo((K) curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (K) curNode.right.key;
      }
    }
    throw new KeyNotFoundException();
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    return height(root);
  }

  /**
   * Recursive helper to get height
   * @param node
   * @return
   */
  private int height(BSTNode<K, V> node) {
    if (node == null) {
      return 0;
    } else {
      if (height(node.left) >= height(node.right)) {
        return 1 + height(node.left);
      } else {
        return 1 + height(node.right);
      }
    }
  }



}
