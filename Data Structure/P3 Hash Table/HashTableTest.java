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

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * This is the test class
 * 
 * @author Aaron Zhang
 *
 */
public class HashTableTest {

  // Two types of hash table will be initialized in setUp()
  HashTableADT<Integer, String> htIntegerKey;
  HashTableADT<String, String> htIntegerKey2;

  @Before
  public void setUp() throws Exception {
    htIntegerKey = new HashTable<Integer, String>(10, 0.6);
    htIntegerKey2 = new HashTable<String, String>();
  }

  @After
  public void tearDown() throws Exception {
    htIntegerKey = null;
    htIntegerKey2 = null;
  }

  /**
   * Tests that a HashTable returns an integer code indicating which collision resolution strategy
   * is used. REFER TO HashTableADT for valid collision scheme codes.
   */
  @Test
  public void test000_collision_scheme() {
    int scheme = htIntegerKey.getCollisionResolution();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws IllegalNullKeyException
   */
  @Test
  public void test001_IllegalNullKey() {
    try {
      htIntegerKey.insert(null, null);
      fail("should not be able to insert null key");
    } catch (IllegalNullKeyException e) {
      /* expected */ } catch (Exception e) {
      fail("insert null key should not throw exception " + e.getClass().getName());
    }
  }

  /**
   * Test initial capacity
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  public void test002_initial_capacity_test()
      throws IllegalNullKeyException, DuplicateKeyException {
    int test1 = htIntegerKey.getCapacity(); // should be 10
    if (test1 != 10) {
      fail("Initial capacity is not correct. It should be 10 but it gives " + test1);
    }
  }

  /**
   * Test capacity after rehashing
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  public void test003_rehashing_capacity_test()
      throws IllegalNullKeyException, DuplicateKeyException {
    int test1 = htIntegerKey.getCapacity();
    htIntegerKey.insert(1, "test1");
    htIntegerKey.insert(2, "test2");
    htIntegerKey.insert(3, "test3");
    htIntegerKey.insert(4, "test4");
    htIntegerKey.insert(5, "test5");
    htIntegerKey.insert(6, "test6");
    int test2 = htIntegerKey.getCapacity(); // should be 21
    if (test1 != 10 || test2 != 21) {
      fail("Capacity is not correct. It should be 10 and 21, but it gives " + test1 + " " + test2);
    }
  }

  /**
   * Test insert null key will be caught
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  public void test004_insert_null() throws IllegalNullKeyException, DuplicateKeyException {
    try {
      htIntegerKey.insert(1, "test1");
      htIntegerKey.insert(null, "test2");
      fail("It didn't catch null key");
    } catch (IllegalNullKeyException e) {
      // Passed
    }
  }

  /**
   * Test insert duplicate keys
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  public void test005_insert_duplicate() throws IllegalNullKeyException, DuplicateKeyException {
    try {
      htIntegerKey.insert(1, "test1");
      htIntegerKey.insert(2, "test2");
      htIntegerKey.insert(3, "test3");
      htIntegerKey.insert(4, "test4");
      htIntegerKey.insert(5, "test5");
      htIntegerKey.insert(6, "test6");
      htIntegerKey.insert(3, "Duplicate");
      fail("It didn't catch duplicate key");
    } catch (DuplicateKeyException e) {
      // Passed
    }
  }

  /**
   * Test get value function
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test006_get_value()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    htIntegerKey.insert(1, "test1");
    htIntegerKey.insert(2, "test2");
    htIntegerKey.insert(3, "test3");
    htIntegerKey.insert(4, "test4");
    htIntegerKey.insert(5, "test5");
    htIntegerKey.insert(6, "test6");
    if (!htIntegerKey.get(3).equals("test3")) {
      fail("It didn't return corresponding value.");
    }
  }

  /**
   * Test exception throw while getting a key not existed
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test007_key_not_found()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    try {
      htIntegerKey.insert(1, "test1");
      htIntegerKey.insert(2, "test2");
      htIntegerKey.insert(3, "test3");
      htIntegerKey.insert(4, "test4");
      htIntegerKey.insert(5, "test5");
      htIntegerKey.insert(6, "test6");
      Object value = htIntegerKey.get(7);
      fail("It didn't catch KeyNotFoundException");
    } catch (KeyNotFoundException e) {
      // Passed
    }
  }

  /**
   * Test to remove a node
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test008_remove_a_node()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    htIntegerKey.insert(1, "test1");
    htIntegerKey.insert(2, "test2");
    htIntegerKey.insert(3, "test3");
    htIntegerKey.insert(4, "test4");
    htIntegerKey.insert(5, "test5");
    htIntegerKey.insert(6, "test6");
    boolean test1 = htIntegerKey.remove(3);
    try {
      htIntegerKey.get(3);
      fail("It didn't remove the key successfully");
    } catch (KeyNotFoundException e) {
      if (!test1) {
        fail("It didn't return true");
      }
      // Passed
    }
  }

  /**
   * Test exception throw while removing a key not existed
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test009_remove_key_not_existed()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    htIntegerKey.insert(1, "test1");
    htIntegerKey.insert(2, "test2");
    htIntegerKey.insert(3, "test3");
    htIntegerKey.insert(4, "test4");
    htIntegerKey.insert(5, "test5");
    htIntegerKey.insert(6, "test6");
    boolean test1 = htIntegerKey.remove(7);
    if (test1) {
      fail("It didn't return false");
    }
  }

  /**
   * Test insertion
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test010_test_insert()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    htIntegerKey.insert(1, "test1");
    htIntegerKey.insert(2, "test2");
    htIntegerKey.insert(3, "test3");
    htIntegerKey.insert(4, "test4");
    htIntegerKey.insert(5, "test5");
    htIntegerKey.insert(6, "test6");
    Object test = htIntegerKey.get(5);
    if (!test.equals("test5")) {
      fail("It didn't insert successfully");
    }
  }

  /**
   * Test insertion by accessing all keys
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test011_insertion_check()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    try {
      htIntegerKey.insert(1, "test1");
      htIntegerKey.insert(2, "test2");
      htIntegerKey.insert(3, "test3");
      htIntegerKey.insert(4, "test4");
      htIntegerKey.insert(5, "test5");
      htIntegerKey.insert(6, "test6");
      List<String> expected = new ArrayList<String>();
      expected.add("test1");
      expected.add("test2");
      expected.add("test3");
      expected.add("test4");
      expected.add("test5");
      expected.add("test6");
      List<String> actual = new ArrayList<String>();
      for (int i = 1; i <= 6; i++) {
        actual.add(htIntegerKey.get(i));
      }
      assertEquals(expected,actual);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception: " + e.getMessage());
    }
  }

}
