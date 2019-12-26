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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Package manager class contains a graph and hashmap which stores all 
 * vertex with dependencies. Also it contains a visited list which
 * tracks visited vertex and a inStack list which tracks vertex in
 * recursion vertex when traversing the whole graph.
 * @author Aaron Zhang, Yixing Tu
 *
 */
public class PackageManager {

  private Graph graph;
  private HashMap<String, Set<String>> list;
  List<String> visited;
  List<String> inStack;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    list = new HashMap<>();
    graph = new Graph();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException           if the give file cannot be read
   * @throws ParseException        if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    Object input = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jo = (JSONObject) input; // Typecast

    // Get packages
    JSONArray ja = (JSONArray) jo.get("packages");

    // Iterate packages
    Iterator itr = ja.iterator();

    while (itr.hasNext()) { // read through all names and corresponding dependencies
      JSONObject obj = (JSONObject) itr.next();
      String key = (String) obj.get("name");
      list.put(key, new HashSet<String>());
      Object temp = obj.get("dependencies");
      JSONArray edges = (JSONArray) temp;
      for (int i = 0; i < edges.size(); i++) {
        list.get(key).add((String) edges.get(i));
      }
    }

    // build graph
    for (String key : list.keySet()) {
      graph.addVertex(key);
      for (String edge : list.get(key)) {
        graph.addEdge(key, edge);
      }
    }

  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  installation order for a particular package. Tip: Cycles in
   *                                  some other part of the graph that do not affect the
   *                                  installation order for the specified package, should not throw
   *                                  this exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    if (!list.containsKey(pkg)) {
      throw new PackageNotFoundException();
    }
    visited = new ArrayList<>(); // Traversed vertex
    inStack = new ArrayList<String>(); // Check recursion stack
    traverse(pkg, visited, inStack);
    return visited;
  }

  /**
   * Helper to traverse the graph from given package
   * @param pkg is the start package
   * @param visited is the list tracks visited packages
   * @param inStack is the list tracks packages in recursion stack
   * @throws CycleException while encounter a cycle
   */
  private void traverse(String pkg, List<String> visited, List<String> inStack)
      throws CycleException {
    if (cycleDetected(pkg, visited, inStack)) {
      throw new CycleException();
    }
    // Swap package with its dependencies if there exists
    for (int i = 0; i < visited.size(); i++) {
      for (int j = 0; j < visited.size(); j++) {
        if (graph.getAdjacentVerticesOf(visited.get(i)).contains(visited.get(j)) && j > i) {
          Collections.swap(visited, i, j);
        }
      }
    }
  }

  /**
   * Help to detect cycle
   * @param pkg is the start package
   * @param visited is the list tracks visited packages
   * @param inStack is the list tracks packages in recursion stack
   * @return
   */
  private boolean cycleDetected(String pkg, List<String> visited, List<String> inStack) {
    visited.add(pkg);
    inStack.add(pkg);
    for (String vertex : graph.getAdjacentVerticesOf(pkg)) { // Loop through all vertex
      if (!visited.contains(vertex) && cycleDetected(vertex, visited, inStack)) {
        return true; // Find vertex in stack during recursion
      } else if (inStack.contains(vertex)) {
        return true;
      }
    }
    inStack.remove(pkg); // Clear ins tack once we finish a recursion
    return false; // Not found after looping through all dependencies
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  dependencies of the given packages. If there is a cycle in
   *                                  some other part of the graph that doesn't affect the parsing
   *                                  of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *                                  graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    if (!list.containsKey(newPkg) || !list.containsKey(installedPkg)) {
      throw new PackageNotFoundException();
    }
    // All packages need to be installed before newPkg
    List<String> allPkg = this.getInstallationOrder(newPkg);
    allPkg.remove(installedPkg); // Remove all existed packages
    for (String deps : graph.getAdjacentVerticesOf(installedPkg)) {
      allPkg.remove(deps);
    }
    return allPkg;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException           if you encounter a cycle in the graph
   * @throws PackageNotFoundException
   */
  public List<String> getInstallationOrderForAllPackages()
      throws CycleException, PackageNotFoundException {
    visited = new ArrayList<>();
    inStack = new ArrayList<>();
    for (String pkg : list.keySet()) { // Loop through all packages
      if (!visited.contains(pkg)) { // Don't traverse packages have been visited
        this.traverse(pkg, visited, inStack);
      }
    }
    return visited;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException           if you encounter a cycle in the graph
   * @throws PackageNotFoundException
   */
  public String getPackageWithMaxDependencies() throws CycleException, PackageNotFoundException {
    int maxDep = 0;
    String maxPkg = null;
    for (String pkg : list.keySet()) { // Keep track of max depth and its package
      int temp = this.getInstallationOrder(pkg).size();
      if (temp > maxDep) {
        maxDep = temp;
        maxPkg = pkg;
      }
    }
    return maxPkg;
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
