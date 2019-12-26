
public class rawtest {

  public static void main(String[] args) {
    Integer i = new Integer(1);
    Node<Integer> n = new Node(i,null);
    System.out.print(n.getNext() == null);
  }

}
