// Title: Implement and Test DataStructureADT
// Files: DS_My.java TestDS_My.java DataStructureADTTest.java
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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a testing class extends data structure that was implemented
 * 
 * @author Aaron Zhang
 * @param <T> is the generic data type
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance; // The instance of ADT used to test

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * Test if the ADT returns size zero while it's empty
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * Test if the ADT returns correct size after inserting one element
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("testKey", "testValue");
    if (dataStructureInstance.size() != 1) {
      fail("data structure should be with size = 1, but size = " + dataStructureInstance.size());
    }
  }

  /**
   * Test if the ADT returns correct size after append and removal It also tests if can insert and
   * then remove one element
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_zero() {
    dataStructureInstance.insert("testKey", "testValue");
    dataStructureInstance.remove("testKey");
    if (dataStructureInstance.size() != 0) {
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
    }
  }

  /**
   * Tests if duplicate insertion can be caught
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    boolean caught = false;
    try {
      dataStructureInstance.insert("testKey1", "testValue1");
      dataStructureInstance.insert("testKey2", "testValue2");
      dataStructureInstance.insert("testKey3", "testValue3");
      dataStructureInstance.insert("testKey1", "testValue1");
    } catch (RuntimeException e) {
      // Good
      caught = true;
    }
    if (!caught) {
      fail("data structure should throw exception, but it didn't");
    }
  }

  /**
   * Test if the remove function returns false while the key not found
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("testKey1", "testValue1");
    dataStructureInstance.insert("testKey2", "testValue2");
    dataStructureInstance.insert("testKey3", "testValue3");
    if (dataStructureInstance.remove("keyNotPresent")) {
      fail("data structure should have moved nothing and should return false but it didn't");
    }
  }

  /**
   * Test if empty input can be caught
   */
  @Test
  void test05_insert_empty_element_should_be_caught() {
    boolean caught = false;
    try {
      dataStructureInstance.insert(null, null);
    } catch (IllegalArgumentException e) {
      caught = true;
    }
    if (!caught) {
      fail("data structure should catch empty input but it didn't");
    }
  }

  /**
   * Test if the size if correct after adding and removing some elements
   */
  @Test
  void test06_insert_and_remove_many_elements_should_give_correct_size() {
    dataStructureInstance.insert("testKey1", "testValue1");
    dataStructureInstance.insert("testKey2", "testValue2");
    dataStructureInstance.insert("testKey3", "testValue3");
    dataStructureInstance.insert("testKey4", "testValue4");
    dataStructureInstance.insert("testKey5", "testValue5");
    dataStructureInstance.insert("testKey6", "testValue6");
    dataStructureInstance.insert("testKey7", "testValue1");
    dataStructureInstance.remove("testKey2");
    if (dataStructureInstance.size() != 6) {
      fail("data structure didn't give correct size while inserting and removing some elements");
    }
  }

  /**
   * Test if duplicate values are not able to be inserted even if the duplicates
   * are far away from each other in the sequence of inserts
   */
  @Test
  void test07_more_tests_on_inserting_duplicate_keys() {
    dataStructureInstance.insert("testKey1", "testValue1");
    dataStructureInstance.insert("testKey2", "testValue2");
    dataStructureInstance.insert("testKey3", "testValue3");
    dataStructureInstance.insert("testKey4", "testValue4");
    try {
      dataStructureInstance.insert("testKey1", "testValue5");
    } catch (RuntimeException e) {
      // Do nothing
    } finally {
      if(dataStructureInstance.size() != 4) {
        fail("didn't take care of duplicate keys well");
      }
      try {
        dataStructureInstance.insert("testKey5", "testValue1");
        dataStructureInstance.insert("testKey6", "testValue2");
        dataStructureInstance.insert("testKey7", "testValue3");
        dataStructureInstance.insert("testKey2", "testValue4");
      } catch (RuntimeException e) {
        // Do nothing
      } finally {
        if(dataStructureInstance.size() != 7) {
          fail("didn't take care of duplicate keys well");
        }
      }
    }
  }
  
  /**
   * Test that it can store 500 items and remove them
   */
  @Test
  void test08_insert_500_items_and_remove_them() {
    String key = "";
    String value = "";
    // Insertion
    for (int i = 1; i <= 500; i++) {
      key = ("testKey" + Integer.toString(i));
      value = ("testValue" + Integer.toString(i));
      dataStructureInstance.insert(key, value);
    }
    if(dataStructureInstance.size() != 500) {
      fail("Failed to insert 500 items." + "It only has " 
          + dataStructureInstance.size() + " items added.");
    }
    
    // Removal
    for (int i = 1; i <= 500; i++) {
      key = ("testKey" + Integer.toString(i));
      dataStructureInstance.remove(key);
    }
    if(dataStructureInstance.size() != 0) {
      fail("Failed to remove 500 items. " + "It has "
          + dataStructureInstance.size() + " items remaining.");
    }
  }
  
  /**
   * Test if getter works properly
   */
  @Test
  void test09_if_getter_works_properly() {
    dataStructureInstance.insert("testKey", "testValue");
    if(dataStructureInstance.get("testNotKey") != null || 
        !dataStructureInstance.get("testKey").equals("testValue")) {
      fail("Getter doesn't work properly");
    }
  }

}
