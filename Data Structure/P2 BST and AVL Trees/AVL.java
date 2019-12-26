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

import java.util.ArrayList;
import java.util.List;

/**
 * BST maintains balanced by using AVL
 * @author Aaron Zhang
 *
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  // must add rebalancing to BST via rotate operations

  BSTNode<K, V> root;

  public AVL() {
    // Do nothing
  }
  
  /**
   * Method to calculate balance factor by subtracting left height with right height
   * @param node is the current node
   * @return the balance factor
   */
  private int getBalanceFactor(BSTNode<K,V> node) {
    if(node == null) {
      return 0;
    } else {
      return height(node.left) - height(node.right);
    }
  }
  
  /**
   * Calculate height
   * @param node is the current node
   * @return the height
   */
  private int height(BSTNode<K,V> node) {
    if(node == null) {
      return 0;
    } else {
      if(height(node.left) >= height(node.right)) {
        return 1 + height(node.left);
      } else {
        return 1 + height(node.right);
      }
    }
  }
  
  /**
   * Return height
   */
  @Override
  public int getHeight() {
    return height(root);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if(key == null) {
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
  private BSTNode<K,V> insertHelper(BSTNode<K,V> node, K key, V value) throws DuplicateKeyException {
    if (node == null) {
      numKeys++;
      return new BSTNode<K, V>(key, value);
    }
    if (key.compareTo(node.key) == 0) { // Find duplicate key, throw exception
      throw new DuplicateKeyException();
    } else if (key.compareTo(node.key) > 0) { // Goes to right if key is larger
      node.right = insertHelper(node.right, key, value);
    } else {    // Goes to left if key is smaller
      node.left = insertHelper(node.left, key, value);
    }
    
    // Find the balance factor of current node
    // To decide if we need to re-balance it
    node.balanceFactor = getBalanceFactor(node);
    
    // Left side unbalanced
    if(node.balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
      return rightRotate(node);
    }
    
    // Left side unbalanced and left child has right children
    if(node.balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
      return leftRightRotate(node);
    }
    
    // Right side unbalanced
    if (node.balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
      return leftRotate(node);
    }
    
    // Right side unbalanced and right child has left children
    if(node.balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
      return rightLeftRotate(node);
    }
    
    return node;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    if(contains(key)) {
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
  private BSTNode<K,V> removeHelper(BSTNode<K,V> node, K key) throws KeyNotFoundException {
    if (node == null) {
      return null;
    }
    if(key.compareTo(node.key) < 0) {
      node.left = removeHelper(node.left, key);
    } else if(key.compareTo(node.key) > 0) {
      node.right = removeHelper(node.right, key);
    } else { // found
      if (node.left == null && node.right == null) { // no child
        return null;
      } else if (node.left == null && node.right != null) { // one right child
        node = node.right;
      } else if (node.left != null && node.right == null) { // one left child
        node = node.left;
      } else { // Two children
        // Goes to the right to find in-order successor (smallest node in the right side)
        BSTNode<K,V> temp = findInorderSuccessor(node.right);
        node.key = temp.key;
        node.value = temp.value;
        node.right = removeHelper(node.right, temp.key);
        
      }
    }  
    
    // Find the balance factor of current node
    // To decide if we need to re-balance it
    node.balanceFactor = getBalanceFactor(node);
    
    // Left side unbalanced
    if(node.balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
      return rightRotate(node);
    }
    
    // Left side unbalanced and left child has right children
    if(node.balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
      return leftRightRotate(node);
    }
    
    // Right side unbalanced
    if (node.balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
      return leftRotate(node);
    }

    // Right side unbalanced and right child has left children
    if(node.balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
      return rightLeftRotate(node);
    }
    
    return node;
  }
  
  /**
   * Private helper to find in-order successor
   * Gives the value to current node and set it null
   * @param node is the current node
   * @return current node after setting
   */
  private BSTNode<K,V> findInorderSuccessor(BSTNode<K,V> node){
    BSTNode<K,V> inorderSuccessor = node;
    while (inorderSuccessor.left != null) { // Goes to the left as far as possible
      inorderSuccessor = node.left;
      node = node.left;
    }
    BSTNode<K,V> temp = inorderSuccessor;
    inorderSuccessor = null; // Remove the successor
    return temp;
  }

  /**
   * This method to do left rotation to balance the tree
   * 
   * @param node is the current node we need to re-balance
   * @return parent node
   */
  private BSTNode<K, V> leftRotate(BSTNode<K, V> node) {
    // set grand to be parent's left leaf
    BSTNode<K,V> grand = node;
    BSTNode<K,V> parent = grand.right;
    BSTNode<K,V> temp = parent.left;
    grand.right = temp;
    parent.left = grand;
    parent.balanceFactor = getBalanceFactor(parent);
    grand.balanceFactor = getBalanceFactor(grand);
    return parent;
  }

  /**
   * This method to do right rotation to balance the tree
   * 
   * @param node is the current node we need to re-balance
   * @return parent node
   */
  private BSTNode<K, V> rightRotate(BSTNode<K, V> node) {
    // set grand to be parent's right leaf
    BSTNode<K, V> grand = node;
    BSTNode<K, V> parent = grand.left;
    BSTNode<K, V> temp = parent.right;
    grand.left = temp;
    parent.right = grand;
    parent.balanceFactor = getBalanceFactor(parent);
    grand.balanceFactor = getBalanceFactor(grand);
    return parent;
  }

  /**
   * This method to do right-left rotation to balance the tree
   * 
   * @param node is the current node we need to re-balance
   * @return node after rotation
   */
  private BSTNode<K,V> rightLeftRotate(BSTNode<K,V> node) {
    node.right = rightRotate(node.right);
    return leftRotate(node);
  }
  
  /**
   * This method to do left-right rotation to balance the tree
   * 
   * @param node is the current node we need to re-balance
   * @return node after rotation
   */
  private BSTNode<K,V> leftRightRotate(BSTNode<K,V> node) {
    node.left = leftRotate(node.left);
    return rightRotate(node);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K,V> curNode = root;
    while(curNode != null){
      if(key.compareTo((K)curNode.key) < 0) {
        curNode = curNode.left;
      } else if(key.compareTo((K)curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (V)curNode.value;
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
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K,V> curNode = root;
    while(curNode != null){
      if(key.compareTo((K)curNode.key) < 0) {
        curNode = curNode.left;
      } else if(key.compareTo((K)curNode.key) > 0) {
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
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K,V> curNode = root;
    while(curNode != null){
      if(key.compareTo((K)curNode.key) < 0) {
        curNode = curNode.left;
      } else if(key.compareTo((K)curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (K)curNode.left.key;
      }
    }
    // Not found
    throw new KeyNotFoundException();
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K,V> curNode = root;
    while(curNode != null){
      if(key.compareTo((K)curNode.key) < 0) {
        curNode = curNode.left;
      } else if(key.compareTo((K)curNode.key) > 0) {
        curNode = curNode.right;
      } else { // found
        return (K)curNode.right.key;
      }
    }
    // Not found
    throw new KeyNotFoundException();
  }
  
  /////////////////////////////Traversals////////////////////////////////
  
  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPreOrderTraversal()
   */
  @Override
  public List<K> getPreOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>();
    if (root == null) {
      return list;
    }
    list = preOrderHelper(root, list);
    return list;
  }
  
  private ArrayList<K> preOrderHelper(BSTNode<K,V> root, ArrayList<K> list){
    if(root == null) {
      return null;
    }
    list.add((K)root.key);
    preOrderHelper(root.left, list);
    preOrderHelper(root.right,list);
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
      return list;
    }
    list = postOrderHelper(root, list);
    return list;
  }
  
  private ArrayList<K> postOrderHelper(BSTNode<K,V> root, ArrayList<K> list){
    if(root == null) {
      return null;
    }
    postOrderHelper(root.left, list);
    postOrderHelper(root.right,list);
    list.add((K)root.key);
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
      return list;
    }
    int h = height(root);
    for (int i = 1; i <= h; i++) {
      levelOrderHelper(root, list, i);
    }
    return list;
  }
  private void levelOrderHelper(BSTNode<K,V> root, ArrayList<K> list, int level) {
    if(root == null) {
      return;
    }
    if(level == 1) {
      list.add((K)root.key);
    }
    else if (level > 1) {
      levelOrderHelper(root.left, list, level-1);
      levelOrderHelper(root.right, list, level-1);
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
      return list;
    }
    list = inOrderHelper(root, list);
    return list;
  }
  
  private ArrayList<K> inOrderHelper(BSTNode<K,V> root, ArrayList<K> list){
    if(root == null) {
      return null;
    }
    inOrderHelper(root.left, list);
    list.add((K)root.key);
    inOrderHelper(root.right,list);
    return list;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    if(root != null) {
      return root.key;
    }
    return null;
  }

}
