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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

/**
 * This is the graphic class for this project.
 * It contains a hashmap stores vertex and dependencies.
 * Also stores its size and order.
 * @author Aaron Zhang, Yixing Tu
 */
public class Graph implements GraphADT {

  private HashMap<String, ArrayList<Object>> list;
  private int size = 0; // Keep track number of vertices
  private int order = 0; // Keep track order

  /*
   * Default no-argument constructor
   */
  public Graph() {
    list = new HashMap<>();
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    if (vertex != null) {
      if (!list.containsKey(vertex)) {
        list.put(vertex, new ArrayList<Object>());
        list.get(vertex).add(false);
        order++;
      }
    }
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    if (vertex != null) {
      if (list.containsKey(vertex)) {
        list.remove(vertex);
        order--;
        // remove associated edges
        for (String key : getAllVertices()) {
          list.get(key).remove(vertex);
        }
      }
    }
  }


  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add the non-existing vertex to the graph and then create an edge. If the
   * edge exists in the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. the edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1 != null && vertex2 != null) {
      if (list.containsKey(vertex1) && list.containsKey(vertex2)) {
        List<String> allEdges = this.getAdjacentVerticesOf(vertex1);
        if(!allEdges.contains(vertex2)) {
          list.get(vertex1).add(vertex2);
          size++;
        }
      } else if (!list.containsKey(vertex1) && list.containsKey(vertex2)) {
        this.addVertex(vertex1);
        order++;
        this.addEdge(vertex1, vertex2);
      } else if (list.containsKey(vertex1) && !list.containsKey(vertex2)) {
        this.addVertex(vertex2);
        order++;
        this.addEdge(vertex1, vertex2);
      } else { // neither exists
        // Do nothing
      }
    }
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 != null && vertex2 != null) {
      if (list.containsKey(vertex1) && list.containsKey(vertex2)) {
        if (list.get(vertex1).contains(vertex2)) {
          list.get(vertex1).remove(vertex2);
          size--;
        }
      }
    }
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    return list.keySet();
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    List<String> temp = new ArrayList<String>();
    list.get(vertex).set(0, true); // this vertex is visited
    for (int i = 1; i < list.get(vertex).size(); i++) { // first value is boolean
      temp.add((String) list.get(vertex).get(i));
    }
    return temp;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return size;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return order;
  }
}
