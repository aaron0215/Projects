// Title: SJF Process Scheduler
// Files: CustomProcess.java CustomProcessQueue.java ProcessScheduler.java 
// ProcessSchedulerTests.java WaitingQueueADT.java
// Course: COMP SCI 300, Fall 2018
//
// Author: Aaron Zhang
// Email: zzhang867@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Yixing Tu
// Partner Email: ytu26@wisc.edu
// Partner Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
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

/**
 * This class is the main driver of the ProcessScheduler
 * @author Aaron Zhang, Yixing Tu
 */
public class ProcessScheduler {
  private CustomProcessQueue queue;
  private int numProcessesRun;
  private int currentTime;
  
  /**
   * Constructor initializes all fields
   */
  public ProcessScheduler() {
    queue = new CustomProcessQueue();
    numProcessesRun = 0;
    currentTime = 0;
  }

  /**
   * Validate if the input from user is all valid
   * @param userIn is the user input
   * @return true if valid otherwise false
   */
  public boolean validate(String[] userIn) {
    boolean valid = true;
    int burstTime;
    String command = userIn[0];
    // All valid possible commands
    if (!command.toLowerCase().equals("schedule") && !command.toLowerCase().equals("s")
        && !command.toLowerCase().equals("run") && !command.toLowerCase().equals("r")
        && !command.toLowerCase().equals("quit") && !command.toLowerCase().equals("q")) {
      System.out.println("WARNING: Please enter a valid command!\n");
      valid = false;
    } else if (command.toLowerCase().equals("run") || command.toLowerCase().equals("r")){
      if(userIn.length == 2) {
        System.out.println("WARNING: Run command should not followed anything!\n");
       valid = false; 
      }
    } else if (command.toLowerCase().equals("schedule") || command.toLowerCase().equals("s")) {
      try {
        burstTime = Integer.parseInt(userIn[1]);
        if (burstTime <= 0) { // Test burst time
          System.out.println("WARNING: burst time MUST be greater than 0!\n");
          valid = false;
        }
      } catch (NumberFormatException e) { // Catch non-digital input
        System.out.println("WARNING: burst time MUST be an integer!\n");
        valid = false;
      } catch (ArrayIndexOutOfBoundsException e) { // Schedule command must have one more input
        System.out.println("WARNING: schedule command must follow by burst time\n");
        valid = false;
      }
    }
    return valid;
  }

  /**
   * Enqueue a new process with given burstTime
   * @param burstTime is the burst time from user
   */
  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process); // Add
    System.out.println("Process ID " + process.getProcessId() + 
        " scheduled. Burst Time = " + process.getBurstTime() +"\n");
    numProcessesRun ++;
  }

  /**
   * Loop through all existed processes in order of priority
   * and remove them (executed processes) from the heap
   */
  public String run() {
    int runCount = queue.size(); // Times of waiting processes in total
    String returnString = "";
    
    if(runCount > 1) {
      returnString += "Starting " + runCount + " processes\n\n";
    } else if(runCount == 1 || runCount == 0) {
      returnString += "Starting " + runCount + " process\n\n";
    }
    
    while(!queue.isEmpty()) { // Loop starts
      CustomProcess topProcess = queue.dequeue(); // Get top element
      int id = topProcess.getProcessId(); // Get id
      int span = topProcess.getBurstTime(); // Get time spent on this process
      returnString += "Time " + currentTime + " : Process ID " + id + " Starting.\n";
      currentTime += span; // Time after execution
      returnString += "Time " + currentTime  + " : Process ID " + id + " Completed.\n";
    }
    
    returnString += "\nTime " + currentTime  + " : All scheduled processes completed.\n";
    return returnString;
  }
  
  /**
   * Display message while user chooses to quit
   */
  public void terminate() {
    System.out.println(numProcessesRun + " processes run in " + currentTime + " units of time!");
    System.out.println("Thank you for using our scheduler!");
    System.out.println("Goodbye!");
  }
  
  /**
   * The main method to interact with user
   * @param args
   */
  public static void main(String[] args) {
    // Initialize
    ProcessScheduler scheduler = new ProcessScheduler();
    Scanner in = new Scanner(System.in);
    String[] userIn = new String[2];
    
    // Start
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\n");
    System.out.println("Enter command:\n" + "[schedule <burstTime>] or [s <burstTime>]\n"
        + "[run] or [r]\n" + "[quit] or [q]\n");
    userIn = in.nextLine().trim().split(" ");
    boolean commandValid = scheduler.validate(userIn); // Validation
    while (true) {
      if(commandValid) {
        if (userIn[0].toLowerCase().equals("schedule") || userIn[0].toLowerCase().equals("s")) {
          scheduler.scheduleProcess(new CustomProcess(Integer.parseInt(userIn[1]))); // Get burst time
        } else if (userIn[0].toLowerCase().equals("run") || userIn[0].toLowerCase().equals("r")) {
          System.out.println(scheduler.run());
        } else if(userIn[0].toLowerCase().equals("quit") || userIn[0].toLowerCase().equals("q")){
          scheduler.terminate();
          break; // Stop the loop
        }
      }
      System.out.println("Enter command:\n" + "[schedule <burstTime>] or [s <burstTime>]\n"
          + "[run] or [r]\n" + "[quit] or [q]\n");
      userIn = in.nextLine().trim().split(" ");
      commandValid = scheduler.validate(userIn);
    }

    // End
    in.close();
  }

}
