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


public class User {

  private final String USERNAME; // The user's name. Unchangeable
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * The default constructor with three parameters
   * It will set the fields as input parameters
   * @param username is the name of the new user
   * @param password is the password of the new user
   * @param isAdmin is the admin status of the new user
   */
  public User(String username, String password, boolean isAdmin) {
    USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * It checks if the login with the input password is valid
   * @param password is the password that the user is trying to log in with
   * @return true if the login is valid; otherwise, false.
   */
  public boolean isValidLogin(String password) {
    if (password.equals(this.password)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * It returns the USERNAME
   * @return USERNAME
   */
  public String getUsername() {
    return USERNAME;
  }

  /**
   * It accesses the admin status of this user
   * @return isAdmin is the admin status of this user
   */
  public boolean getIsAdmin() {
    return isAdmin;
  }

  /**
   * It sets the password as given
   * @param password is the new password from driver
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * It set the admin status as given
   * @param isAdmin is the desired admin status acquired from driver
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
