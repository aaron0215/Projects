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

/**
 * Initialize the test
 * @author Aaron Zhang
 */
@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {

  // the return type must be the name of the data structure class you are testing
  @Override
  protected DataStructureADT createInstance() {
    return new DS_My();
  }

}
