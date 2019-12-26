import java.util.Scanner;

public class puretest {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
//    String command;
//    System.out.println("Here are wha you can do: ");
//    System.out.println("1. logout");
//    System.out.println("2. newpw [newpassword]");
//    System.out.println("3. adduser [username]");
//    System.out.println("4. adduser [username] [true or false]");
//    System.out.println("5. rmuser [username]");
//    System.out.println("6. giveadmin [username]");
//    System.out.println("7. rmadmin [username]");
//    System.out.println("8. resetpw [username]");
//    System.out.print("Enter your command: ");
//    command = sc.next();
//    System.out.print(command); //command type
//    command = sc.nextLine();
//    command = command.replaceAll("\\s+","");
//    System.out.println(command); //pw or name without space
//    command = command.replaceAll("\\[","");
//    command = command.replaceAll("\\]"," ");
//    String [] test = new String[2];
//    test = command.split(" ");
//    System.out.println(test[0]);
//    System.out.println(test[1]);
    
    String command = null;
    String values = null;
    String [] userInput;
    while(command != "logout") {
      System.out.println("Here are wha you can do: ");
      System.out.println("1. logout");
      System.out.println("2. newpw [newpassword]");
      System.out.println("3. adduser [username]");
      System.out.println("4. adduser [username] [true or false]");
      System.out.println("5. rmuser [username]");
      System.out.println("6. giveadmin [username]");
      System.out.println("7. rmadmin [username]");
      System.out.println("8. resetpw [username]");
      System.out.print("Enter your command (e.g. newpw [javaisgreat]: ");
      command = sc.next();
      values = sc.nextLine();
      values = values.replaceAll("\\s+","");
      values = values.replaceAll("\\[","");
      values = values.replaceAll("\\]"," ");
      userInput = values.split(" ");
      System.out.println(userInput[0]);
      if(userInput[1] != null) {
        System.out.println(userInput[1]);
      }
      else if(userInput[1] == null) {
        System.out.println("Nothing");
      }
    }
  }

}
