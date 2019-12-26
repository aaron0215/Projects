// Title: PackageManger
// Files: Graph.java GraphTest.java PackageManager.java PackageManagerTest.java
// Course: COMP SCI 400, Spring 2019
//
// Team: p4pair 16
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
// Author: Yixing Tu
// Email: ytu26@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
// Due date: 4/19/2019
// Known bugs: None
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
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

/**
 * This is the test class for Graph.
 * @author Aaron Zhang, Yixing Tu
 *
 */
class GraphTest {
  
  /**
   * Test adding one vertex
   */
  @Test
  void test001_add_a_vertex() {
    Graph test = new Graph();
    try {
      int expected = 1;
      test.addVertex("test");
      assertEquals(expected, test.order());
    } catch (Exception e) {
      fail("Unexpected exception occured in test001: " + e);
    }
  }
  
  /**
   * Test adding many vertex
   */
  @Test
  void test002_add_many_vertex() {
    Graph test = new Graph();
    try {
      int expected = 5;
      test.addVertex("test1");
      test.addVertex("test2");
      test.addVertex("test3");
      test.addVertex("test4");
      test.addVertex("test5");
      assertEquals(expected, test.order());
    } catch (Exception e) {
      fail("Unexpected exception occured in test002: " + e);
    }
  }
  
  /**
   * Test adding many edges
   */
  @Test
  void test003_add_edges() {
    Graph test = new Graph();
    try {
      int expectedOrder = 5;
      int expectedSize = 3;
      test.addVertex("test1");
      test.addVertex("test2");
      test.addVertex("test3");
      test.addVertex("test4");
      test.addVertex("test5");
      test.addEdge("test1", "test2");
      test.addEdge("test2", "test3");
      test.addEdge("test3", "test5");
      assertEquals(expectedOrder, test.order());
      assertEquals(expectedSize, test.size());
    } catch (Exception e) {
      fail("Unexpected exception occured in test003: " + e);
    }
  }
  
  /**
   * Test removing edges
   */
  @Test
  void test004_add_and_remove_edges() {
    Graph test = new Graph();
    try {
      int expectedOrder = 5;
      int expectedSize = 2;
      test.addVertex("test1");
      test.addVertex("test2");
      test.addVertex("test3");
      test.addVertex("test4");
      test.addVertex("test5");
      test.addEdge("test1", "test2");
      test.addEdge("test2", "test3");
      test.addEdge("test3", "test5");
      test.removeEdge("test2", "test3");
      assertEquals(expectedOrder, test.order());
      assertEquals(expectedSize, test.size());
    } catch (Exception e) {
      fail("Unexpected exception occured in test004: " + e);
    }
  }
  
  /**
   * Test removing non-existed edge
   */
  @Test
  void test005_remove_nonexisted_edge() {
    Graph test = new Graph();
    try {
      int expectedOrder = 5;
      int expectedSize = 3;
      test.addVertex("test1");
      test.addVertex("test2");
      test.addVertex("test3");
      test.addVertex("test4");
      test.addVertex("test5");
      test.addEdge("test1", "test2");
      test.addEdge("test2", "test3");
      test.addEdge("test3", "test5");
      test.removeEdge("test1", "test5");
      assertEquals(expectedOrder, test.order());
      assertEquals(expectedSize, test.size());
    } catch (Exception e) {
      fail("Unexpected exception occured in test005: " + e);
    }
  }
  
  /**
   * Test removing vertex
   */
  @Test
  void test006_remove_vertex() {
    Graph test = new Graph();
    try {
      int expectedOrder = 1;
      test.addVertex("test1");
      test.addVertex("test2");
      test.removeVertex("test1");
      assertEquals(expectedOrder, test.order());
    } catch (Exception e) {
      fail("Unexpected exception occured in test006: " + e);
    }
  }
  
  /**
   * Test getting all dependencies
   */
  @Test
  void test007_get_neighbors() {
    Graph test = new Graph();
    try {
      test.addVertex("test1");
      test.addVertex("test2");
      test.addVertex("test3");
      test.addVertex("test4");
      test.addVertex("test5");
      test.addEdge("test2", "test1");
      test.addEdge("test2", "test3");
      test.addEdge("test2", "test5");
      ArrayList<String> expected = new ArrayList<>();
      expected.add("test1");
      expected.add("test3");
      expected.add("test5");
      assertEquals(expected, test.getAdjacentVerticesOf("test2"));
    } catch (Exception e) {
      fail("Unexpected exception occured in test007: " + e);
    }
  }

}
