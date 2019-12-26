/**
 * Node class for LinkedList for Stack<T>
 * @param <T> type of data of node
 */
public class Node<T> {
  private final T data;
  private Node<T> next;

  public Node(T element, Node<T> next) {
    this.data = element;
    this.next = next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public Node<T> getNext() {
    return this.next;
  }

  public T getData() {
    return this.data;
  }
}