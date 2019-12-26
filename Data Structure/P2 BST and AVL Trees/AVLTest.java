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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

//@SuppressWarnings("rawtypes")
/**
 * This is the test class for AVL
 * @author Aaron Zhang
 *
 */
public class AVLTest extends BSTTest {

	AVL<String,String> avl;
	AVL<Integer,String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String,String>();
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer,String>();
	}

	/** 
	 * Insert three values in sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 001: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values in reverse sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
	  try {
        avl2.insert(30, "30");
        if (!avl2.getKeyAtRoot().equals(30)) 
            fail("avl insert at root does not work");
        
        avl2.insert(20, "20");
        if (!avl2.getKeyOfLeftChildOf(30).equals(20)) 
            fail("avl insert to right child of root does not work");
        
        avl2.insert(10, "10");
        Integer k = avl2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work");
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child

        Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
        Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
        
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 002: "+e.getMessage() );
    }
		
	}

	/** 
	 * Insert three values so that a right-left rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
	  try {
        avl2.insert(10, "10");
        avl2.insert(30, "30");
        avl2.insert(20, "20");
        Integer k = avl2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work");
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child

        Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
        Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
        
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 003: "+e.getMessage() );
    }
		
	}

	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_largest_smallest_middle_order_simple() {
	  try {
	    avl2.insert(30, "30");
	    avl2.insert(10, "10");
	    avl2.insert(20, "20");
	    Integer k = avl2.getKeyAtRoot();
	    if (!k.equals(20)) 
	      fail("avl rotate does not work");

	    // IF rebalancing is working,
	    // the tree should have 20 at the root
	    // and 10 as its left child and 30 as its right child

	    Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
	    Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
	    Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));

	  } catch (Exception e) {
	    e.printStackTrace();
	    fail( "Unexpected exception AVL 004: "+e.getMessage() );
	  }	
	}
	

	/**
	 * This test checks if the auto-balance method works as expected
	 * by calling getPreOrderTraversal
	 * 
	 * Insert order: 10-20-30
	 * Pre-order after balance: 20-10-30
	 * 
	 * @throws IllegalNullKeyException
	 * @throws DuplicateKeyException
	 */
	@Test
	@Override
	void testBST_008_check_preOrder_for_not_balanced_insert_order() 
	    throws IllegalNullKeyException, DuplicateKeyException {
      try {
        avl2.insert(10, "10");
        avl2.insert(20, "20");
        avl2.insert(30, "30");
        System.out.println(avl2.getPreOrderTraversal());
        List<Integer> list = avl2.getPreOrderTraversal();
        List<Integer> expectedOrder = new ArrayList<Integer>();
        expectedOrder.add(20);
        expectedOrder.add(10);
        expectedOrder.add(30);
        assertEquals(expectedOrder,list);
      }
      catch (Exception e) {
        e.printStackTrace();
        fail("Preorder is not correct");
      }
    }
	
	/**
     * This test checks if the auto-balance method works as expected
     * by calling getLevelOrderTraversal
     * 
     * Insert order: 10-20-30
     * Level-order after balance: 20-10-30
     * 
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
	@Test
	@Override
    void testBST_010_check_levelOrder_for_not_balanced_insert_order() 
        throws IllegalNullKeyException, DuplicateKeyException {
      avl2.insert(10, "10");
      avl2.insert(20, "20");
      avl2.insert(30, "30");
      List<Integer> list = avl2.getLevelOrderTraversal();
      if(list.get(0) != 20 || list.get(1) != 10 || list.get(2) != 30) {
          fail("Level order is not correct");
      } 
    }
	
	/**
     * This test checks if the auto-balance method works as expected
     * by calling getPostOrderTraversal
     * 
     * Insert order: 10-20-30
     * Level-order after balance: 10-30-20
     * 
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
	@Test
	@Override
    void testBST_009_check_postOrder_for_not_balanced_insert_order() 
        throws IllegalNullKeyException, DuplicateKeyException {
	  avl2.insert(10, "10");
	  avl2.insert(20, "20");
	  avl2.insert(30, "30");
      List<Integer> list = avl2.getPostOrderTraversal();
      if(list.get(0) != 10 || list.get(1) != 30 || list.get(2) != 20) {
          fail("Post order is not correct");
      }
    }
    
	/**
     * This test checks if rotate methods work as 
     * expected after insertion and removal
     * 
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
	@Test
	void testBST_010_insert_remove_checkrotate() 
	    throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
	  try {
	    avl2.insert(10, "10");
	    avl2.insert(20, "20");
	    avl2.insert(30, "30");
	    avl2.insert(5, "5");
	    avl2.insert(25, "25");
	    avl2.insert(35, "35");
	    avl2.remove(20);
	    Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(25));
	    Assert.assertEquals(avl2.getKeyOfLeftChildOf(25),new Integer(10));
	    Assert.assertEquals(avl2.getKeyOfRightChildOf(25),new Integer(30));
	  } catch (Exception e) {
	    e.printStackTrace();
	    fail( "Unexpected exception AVL 010: "+e.getMessage() );
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
	    avl2.insert(20, "20");
	    avl2.insert(10, "10");
	    avl2.insert(30, "30");
	    avl2.insert(25, "25");
	    avl2.remove(20);
	    if (avl2.root.key != 25) {
	      fail("New root should be 25 not " + avl2.root.key);
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
	    avl2.insert(20, "20");
	    avl2.insert(10, "10");
	    avl2.insert(30, "30");
	    avl2.insert(5, "5");
	    avl2.remove(10);
	    if (avl2.root.left.key != 5) {
	      fail("New key of left leaf should be 5 not " + avl2.root.left.key);
	    }
	  }

}
