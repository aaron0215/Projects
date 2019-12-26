// Title: Access Control Project
// Files: AccessControl.java AccessControlTest.java User.java
// Course: CS300 Fall 2018
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
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

public class AccessControlTest {
  /*
   * Testing main. Runs each test and prints which (if any) failed. Expect all tests will pass and
   * get statement at the end
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testAddUser1 [good username with default password] failed");
      fails++;
    }
    if (!testAddUser2()) {
      System.out.println("testAddUser2 [add user after logging in as admin] failed");
      fails++;
    }
    if (!testChangePw()) {
      System.out.println("testChangePw [change password of current user] failed");
      fails++;
    }
    if (!testResetPw()) {
      System.out.println("testResetPw [reset password of current user] failed");
      fails++;
    }
    if (!testTakeAdmin()) {
      System.out.println("testTakeAdmin [remove the access of user] failed");
      fails++;
    }
    if (fails == 0)
      System.out.println("All tests passed!");
  }

  /*
   * This test tries to log in a user that doesn't exist
   * 
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  /*
   * This test tries to log in a user does exist
   * 
   * @return boolean test passed
   */
  public static boolean testLogin2() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    String pw = "root";
    return AccessControl.isValidLogin(user, pw); // isValidLogin should return true
  }

  /*
   * This test tries to log in a user with invalid name but default password
   * 
   * @return boolean test passed
   */
  public static boolean testLogin3() {
    // AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234";
    String pw = "changeme";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  /*
   * This test tries to add an user without logging in as admin Verify that addUser(String username)
   * Should print statement "The current user does not exist or has no access"
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "alexi";
    boolean addUserReport = ac.addUser(user);
    if (addUserReport)
      return false; // addUserReport should be false
    // Make sure user wasn't added anyway
    return !AccessControl.isValidLogin(user, "changeme"); // isValidLogin should return false
  }

  /*
   * This test treis to add an user after logging in as admin Verify that addUser(String username,
   * boolean admin)
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser2() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String user = "alexi";
    boolean addUserReport = ac.addUser(user, true);
    if (!addUserReport)
      return false; // addUserReport should be false
    // Make sure user wasn't added anyway
    return AccessControl.isValidLogin(user, "changeme"); // isValidLogin should return true
  }

  /*
   * This test tries to log in as admin and add new user and change its password
   * 
   * @return boolean test passed
   */
  public static boolean testChangePw() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String user = "alexi";
    ac.addUser(user);
    ac.setCurrentUser(user);
    ac.changePassword("javaisfun");
    return AccessControl.isValidLogin(user, "javaisfun"); // isValidLogin should return true
  }

  /*
   * This test tries to log in as admin and reset the password of it
   * 
   * @return boolean test passed
   */
  public static boolean testResetPw() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    ac.setCurrentUser(user);
    ac.resetPassword(user);
    return AccessControl.isValidLogin(user, "changeme"); // isValidLogin should return true
  }
  
  /*
   * This test tries to remove the access of admin
   * Should print statement "The current user does not exist or has no access."
   * 
   * @return boolean test passed
   */
  public static boolean testTakeAdmin() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    ac.setCurrentUser(user);
    ac.takeAdmin(user);
    String newUser = "aaron";
    ac.addUser(newUser); // It should not be able to add user
    return !AccessControl.isValidLogin(newUser, "changeme"); // isValidLogin should return false
  }
}
