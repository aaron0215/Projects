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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PackageManager
 * @author Aaron Zhang, Yixing Tu
 *
 */
class PackageManagerTest {

  /**
   * Test cycle detection
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test001_cycle_detection()
      throws FileNotFoundException, IOException, ParseException, PackageNotFoundException {
    PackageManager test = new PackageManager();
    test.constructGraph("cyclic.json");
    try {
      test.getInstallationOrder("A");
      fail("Cycle exception should be caught in test001");
    } catch (CycleException e) {
      // Passed
    }
  }

  /**
   * Test handle sharing dependencies cases
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test002_sharing_dependencies()
      throws FileNotFoundException, IOException, ParseException, PackageNotFoundException {
    PackageManager test = new PackageManager();
    test.constructGraph("shared_dependencies.json");
    try {
      List<String> expected = new ArrayList<String>();
      expected.add("D");
      expected.add("B");
      expected.add("C");
      expected.add("A");
      assertEquals(expected, test.getInstallationOrder("A"));
    } catch (Exception e) {
      fail("Unexpected exception occured in test002: " + e);
    }
  }

  /**
   * Test parse valid json file
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test003_valid_json_for_particular() throws FileNotFoundException, IOException,
      ParseException, PackageNotFoundException, CycleException {
    PackageManager test = new PackageManager();
    test.constructGraph("valid.json");
    try {
      List<String> expected = new ArrayList<String>();
      expected.add("C");
      expected.add("D");
      expected.add("B");
      expected.add("A");
      assertEquals(expected, test.getInstallationOrder("A"));
    } catch (Exception e) {
      fail("Unexpected exception occured in test003: " + e);
    }
  }
  
  /**
   * Test getting all packages needs to be installed except installed ones
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test004_get_all_except_installed() throws FileNotFoundException, IOException,
      ParseException, PackageNotFoundException, CycleException {
    PackageManager test = new PackageManager();
    test.constructGraph("shared_dependencies.json");
    try {
      List<String> expected = new ArrayList<String>();
      expected.add("C");
      expected.add("A");
      assertEquals(expected, test.toInstall("A", "B"));
    } catch (Exception e) {
      fail("Unexpected exception occured in test004: " + e);
    }
  }
  
  /**
   * Test get all packages
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test005_get_all() throws FileNotFoundException, IOException,
      ParseException, PackageNotFoundException, CycleException {
    PackageManager test = new PackageManager();
    test.constructGraph("valid.json");
    try {
      List<String> expected = new ArrayList<String>();
      expected.add("C");
      expected.add("D");
      expected.add("B");
      expected.add("A");
      expected.add("E");
      assertEquals(expected, test.getInstallationOrderForAllPackages());
    } catch (Exception e) {
      fail("Unexpected exception occured in test005: " + e);
    }
  }
  
  /**
   * Self-made test
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test006_own_test()throws FileNotFoundException, IOException,
      ParseException, PackageNotFoundException, CycleException {
    PackageManager test = new PackageManager();
    test.constructGraph("test.json");
    try {
      List<String> expected = new ArrayList<String>();
      expected.add("C");
      expected.add("D");
      expected.add("B");
      expected.add("E");
      expected.add("A");
      assertEquals(expected, test.getInstallationOrder("A"));
    } catch (Exception e) {
      fail("Unexpected exception occured in test006: " + e);
    }
  }
  
  /**
   * Test getting package with maximum number of dependencies
   * @throws FileNotFoundException if the JSON file is not existed
   * @throws IOException if the file cannot be read
   * @throws ParseException if the file cannot be parsed
   * @throws PackageNotFoundException if the package is not found in graph
   */
  @Test
  public void test007_get_max_dep() throws FileNotFoundException, IOException,
  ParseException, PackageNotFoundException, CycleException {
    PackageManager test = new PackageManager();
    test.constructGraph("valid.json");
    try {
      String expected = "A";
      assertEquals(expected, test.getPackageWithMaxDependencies());
    } catch (Exception e) {
      fail("Unexpected exception occured in test006: " + e);
    }
  }


}
