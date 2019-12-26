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

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a test class for BST
 * @author Aaron Zhang
 *
 */
public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {

      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten");
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 
   * In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 
   * Pre-Order traversal order: 20-10-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getPreOrderTraversal();
    if (list.get(0) != 20 || list.get(1) != 10 || list.get(2) != 30) {
      fail("Pre order is not correct");
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 
   * Post-Order traversal order: 10-30-20
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getPostOrderTraversal();
    if (list.get(0) != 10 || list.get(1) != 30 || list.get(2) != 20) {
      fail("Post order is not correct");
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 
   * Level-Order traversal order: 20-10-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getLevelOrderTraversal();
    if (list.get(0) != 20 || list.get(1) != 10 || list.get(2) != 30) {
      fail("Level order is not correct");
    }
  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 
   * In-Order traversal order: 10-20-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(10, "10");
    bst2.insert(20, "20");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getInOrderTraversal();
    if (list.get(0) != 10 || list.get(1) != 20 || list.get(2) != 30) {
      fail("In order is not correct");
    }


  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 
   * Pre-Order traversal order: 10-20-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(10, "20");
    bst2.insert(20, "10");
    bst2.insert(30, "30");
    System.out.println(bst2.getPreOrderTraversal());
    List<Integer> list = bst2.getPreOrderTraversal();
    if (list.get(0) != 10 || list.get(1) != 20 || list.get(2) != 30) {
      fail("Pre order is not correct");
    }
  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 
   * Post-Order traversal order: 30-20-10
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(10, "10");
    bst2.insert(20, "20");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getPostOrderTraversal();
    if (list.get(0) != 30 || list.get(1) != 20 || list.get(2) != 10) {
      fail("Post order is not correct");
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 
   * Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    bst2.insert(10, "10");
    bst2.insert(20, "20");
    bst2.insert(30, "30");
    List<Integer> list = bst2.getLevelOrderTraversal();
    if (list.get(0) != 10 || list.get(1) != 20 || list.get(2) != 30) {
      fail("Level order is not correct");
    }
  }

  /**
   * Test that the insertion and removal methods work as expected
   * 
   * Insert order: 20-10-30-5-15-35 
   * Remove order: 5-30 
   * Level-Order traversal order: 20-10-15-35
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void testBST_011_check_insert_and_remove()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    try {
      bst2.insert(20, "20");
      bst2.insert(10, "10");
      bst2.insert(30, "30");
      bst2.insert(5, "5");
      bst2.insert(15, "15");
      bst2.insert(35, "35");
      bst2.remove(5);
      bst2.remove(30);
      if (bst2.getHeight() != 3)
        fail("The height should be 3 not " + bst2.getHeight());

      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(10);
      expectedOrder.add(35);
      expectedOrder.add(15);
      // Get the actual traversal order lists for each type
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      System.out.println("Expected Order: " + levelOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, levelOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }
  }

  /**
   * Test getter after insertions and removals
   * 
   * Insert order: 20-10-30-5-15-35 
   * Removal order: 5-30 
   * Get 20 should gives 20
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void testBST_012_get_after_insertions_and_removals()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    bst2.insert(5, "5");
    bst2.insert(15, "15");
    bst2.insert(35, "35");
    bst2.remove(5);
    bst2.remove(30);
    if (!bst2.get(20).equals("20")) {
      fail("It should get 20 not " + bst2.get(20));
    }
  }

  /**
   * Test removing node with one child
   * 
   * Insert order: 20-10-30-5 
   * Remove: 10
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void testBST_013_remove_node_with_one_child()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    bst2.insert(5, "5");
    bst2.remove(10);
    if (bst2.root.left.key != 5) {
      fail("New key of left leaf should be 5 not " + bst2.root.left.key);
    }
  }

  /**
   * Test removing node with two children and it's the root
   * Also tests in-order successor finder
   * 
   * Insert order: 20-10-30-5-25 
   * Remove: 20
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void testBST_014_remove_node_with_two_children()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    bst2.insert(25, "25");
    bst2.remove(20);
    if (bst2.root.key != 25) {
      fail("New root should be 25 not " + bst2.root.key);
    }
  }

  /**
   * Test getKeyOfLeftChildOf and getKeyOfRightChildOf
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void testBST_015_get_key_of_child()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bst2.insert(20, "20");
    bst2.insert(10, "10");
    bst2.insert(30, "30");
    bst2.insert(25, "25");
    if(bst2.getKeyOfLeftChildOf(20) != 10) {
      fail("Get key of left child should return 10 not "+ bst2.getKeyOfLeftChildOf(20));
    }
    if(bst2.getKeyOfRightChildOf(20) != 30) {
      fail("Get key of left child should return 30 not "+ bst2.getKeyOfRightChildOf(20));
    }
  }



}
