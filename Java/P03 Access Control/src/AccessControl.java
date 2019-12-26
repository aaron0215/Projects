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

import java.util.Scanner;
import java.util.ArrayList;

public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users
  private User currentUser; // Who is currently logged in, if anyone?
  // Default password given to new users or when
  // we reset a user's password.
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * The main function of main class. It creates a new AccessControl object and
   * initialize a scanner for further usage and call loginScreen with, pass the
   * created scanner into it.
   * @param args
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }

  /**
   * Default constructor which initializes all variables
   * It gives users ArrayList an user with admin and sets currentUser to be null
   */
  public AccessControl() {
    users = new ArrayList<>();
    users.add(new User("admin", "root", true));
    currentUser = null;
  }

  /**
   * This method checks if the login is valid. It acquires username and password
   * from driver. Find if the username existed and then check if the password
   * is valid by calling the isValidLogin method inside of the User class
   * @param username is the username that the user tries to log in with
   * @param password is the password that the user tries to log in with under this username
   * @return
   */
  public static boolean isValidLogin(String username, String password) {
    boolean isValid = false;
    for (int index = 0; index < users.size(); index++) {
      if (username.equals(users.get(index).getUsername())) {    // Check if the user is existed
        if (users.get(index).isValidLogin(password)) {  // Check if the password is correct
          isValid = true;
        }
      }
    }
    return isValid;
  }

  /**
   * This method is a mutator to write tests, will not be called in driver.
   * The currentUser will be set manually in the sessionScreen driver
   * @param username is the username that the user wants to set as currentUser
   */
  public void setCurrentUser(String username) {
    for (int index = 0; index < users.size(); index++) {
      if ((users.get(index).getUsername()).equals(username)) {
        currentUser = users.get(index);
      }
    }
  }

  /**
   * This method logs the currentUser out by setting currentUser to be null
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * Changes the password of currentUser
   * @param newPassword is the new desired password of the current user
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * Creates a new user with the default password and isAdmin==false
   * @param username is the name of new user
   * @return true if add user successfully, otherwise, false
   */
  public boolean addUser(String username) {
    boolean done = false;
    boolean userExisted = false;
    // Check if the user is existed
    for (int index = 0; index < users.size(); index++) {
      if (username.equals(users.get(index).getUsername())) {
        userExisted = true;
      }
    }
    // If NOT existed, add the user
    if (!userExisted) {
      if (currentUser != null && currentUser.getIsAdmin()) {
        users.add(new User(username, DEFAULT_PASSWORD, false));
        done = true;
      } else {
        System.out.println("The current user does not exist or has no access.");
      }
    } else {
      System.out.println("User already existed");
    }
    return done;
  }

  /**
   * Create a new user with the default password and input user name and given admin status
   * @param username is the name of new user
   * @return true if add user successfully, otherwise, false
   */
  public boolean addUser(String username, boolean isAdmin) {
    boolean done = false;
    boolean userExisted = false;
    // Algorithm is similar with the method above
    for (int index = 0; index < users.size(); index++) {
      if (username.equals(users.get(index).getUsername())) {
        userExisted = true;
      }
    }
    if (!userExisted) {
      if (currentUser != null && currentUser.getIsAdmin()) {
        users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
        done = true;
      } else {
        System.out.println("The current user does not exist or has no access.");
      }
    } else {
      System.out.println("User already existed.");
    }
    return done;
  }

  /**
   * Removes the specific user from the ArrayList
   * @param username is the name of the user that expected to be removed
   * @return true if removes it successfully, otherwise, false
   */
  public boolean removeUser(String username) {
    boolean removed = false;
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.remove(index);  // Remove the user from the ArrayList
          removed = true;
        }
      }
    }
    else {
      System.out.println("The current user does not exist or has no access.");
    }
    if(!removed) {
      System.out.println("The user [" + username + "] does not exist");
    }
    return removed;
  }

  /**
   * Gives the specific user admin access
   * @param username is the user expects to be set as admin
   * @return true if the process is successfully, otherwise, false
   */
  public boolean giveAdmin(String username) {
    boolean succeed = false;
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setIsAdmin(true);
          succeed = true;
        }
      }
    } else {
      System.out.println("The current user does not exist or has no access");
    }
    if(!succeed) {
      System.out.println("The user [" + username + "] does not exist");
    }
    return succeed;
  }

  /**
   * Removes the admin access of the specific user
   * @param username is the user expected to be removed admin access
   * @return true if removes it successfully, otherwise, false
   */
  public boolean takeAdmin(String username) {
    boolean succeed = false;
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setIsAdmin(false);
          succeed = true;
        }
      }
    } else {
      System.out.println("The current user does not exist or has no access");
    }
    if(!succeed) {
      System.out.println("The user [" + username + "] does not exist");
    }
    return succeed;
  }

  /**
   * Resets the password of the specific user to be the default password
   * @param username is the user expected to be reset the password
   * @return true if resets if successfully, otherwise, false
   */
  public boolean resetPassword(String username) {
    boolean reset = false;
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setPassword(DEFAULT_PASSWORD);
          reset = true;
        }
      }
    } else {
      System.out.println("The current user does not exist or has no access");
    }
    if(!reset) {
      System.out.println("The user [" + username + "] does not exist");
    }
    return reset;
  }

  /**
   * Main drive loop. Prompts the user for login information, calls isValidLogin method
   * to check if the login activity is valid. If valid, call the sessionScreen driver.
   * @param userInputScanner is a scanner was created in main and being passed to further driver
   */
  public void loginScreen(Scanner userInputScanner) {
    String name, password;   // Login information
    boolean foundUser;  // If the user is existed
    int userIndex;  // The index of the user in the ArraryList
    while (true) {  // Generated while loop
      name = null;
      password = null;
      foundUser = false;
      userIndex = 0;
      System.out.println("\nPlease enter your username and password to login");
      System.out.print("Username: ");
      name = userInputScanner.nextLine().trim();
      if (name != null) {
        for (int index = 0; index < users.size(); index++) {
          if ((users.get(index).getUsername().equals(name))) {
            foundUser = true;
            userIndex = index;  // Record the index
          }
        }
      }
      if (!foundUser) {
        System.out.println("Username does NOT exist or empty input");
      } else {
        System.out.print("Password: ");
        password = userInputScanner.nextLine().trim();
      }
      if (password != null && users.get(userIndex).isValidLogin(password)) {
        System.out.println("\nWelcome! " + name);
        sessionScreen(name, userInputScanner);
      } else {
        System.out.println("Please verify your account information");
      }
    }
  }

  /**
   * Will be called if a user is logged in successfully. It set the currentUser
   * as the user just logged in. It allows user to change password or logout;
   * only allows the user who is an admin to access to admin methods (such as addUser)
   * @param username is the user who just logged in
   * @param userInputScanner is the scanner passed through main and loginScreen method
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    for (int index = 0; index < users.size(); index++) {
      if ((users.get(index).getUsername()).equals(username)) {
        currentUser = users.get(index); // Set the currentUser
      }
    }
    String[] userInput = new String[3]; // Record user input
    userInput[0] = "default";   // To start the while loop
    while (!userInput[0].equals("logout")) {
      boolean done = true;
      System.out.println("\nWhat would you like to do next: ");
      System.out.println("  - logout");
      System.out.println("  - newpw [newpassword]");
      System.out.println("  - adduser [username]");
      System.out.println("  - adduser [username] [true or false]");
      System.out.println("  - rmuser [username]");
      System.out.println("  - giveadmin [username]");
      System.out.println("  - rmadmin [username]");
      System.out.println("  - resetpw [username]");
      System.out.print("Enter your command (e.g. newpw javaisgreat): ");
      userInput = userInputScanner.nextLine().trim().split("\\s+");
      if (userInput[0].equals("logout")) {
        logout();
      } else if (userInput[0].equals("newpw")) {
        changePassword(userInput[1]);
      } else if (userInput[0].equals("adduser") && userInput.length == 2) {
        done = addUser(userInput[1]);           // Distinguish two types of addUser methods
      } else if (userInput[0].equals("adduser") && userInput.length == 3) {
        done = addUser(userInput[1], Boolean.parseBoolean(userInput[2]));
      } else if (userInput[0].equals("rmuser")) {
        done = removeUser(userInput[1]);
      } else if (userInput[0].equals("giveadmin")) {
        done = giveAdmin(userInput[1]);
      } else if (userInput[0].equals("rmadmin")) {
        done = takeAdmin(userInput[1]);
      } else if (userInput[0].equals("resetpw")) {
        done = resetPassword(userInput[1]);
      } else {
        System.out.println("Invalid command");
      }
      if (!done) {
        System.out.println("You command was not processed due to the reason above");
      }
    }
  }

}
