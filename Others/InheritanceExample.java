import java.util.*;

class Parent{
  public Parent() {
    System.out.println("Parent constructor");
  }
  public void print() {
    System.out.println("Parent print");
  }
}

class Child extends Parent{
  public Child() {
    System.out.println("Child constructor");
  }
  public void print() {
    System.out.println("Child print");
  }
  public void childPrint() {
    System.out.println("Child only");
  }
}

public class InheritanceExample{
    public static void main(String[] args){
        Parent p = new Parent();
        System.out.println("<------------------->");
        Child c = new Child();
        System.out.println("<------------------->");
        c.print();
        System.out.println("<------------------->");
//        Child c2 = new Parent(); // invalid
        Parent p2 = new Child();
        System.out.println("<------------------->");
        p2.print(); // child print() override parent, p2 is still parent object
//        p2.childPrint(); // invalid 
    }
}