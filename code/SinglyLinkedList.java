public class SinglyLinkedList<E>{

  private Node<E> head;
  private Node<E> tail;
  private int size;
  private static class Node<E>{
    private E element;
    private Node<E> next;
    public Node(E e, Node<E> n){
      this.element = e;
      this.next = n;
    }
    public E getElement(){
      return element;
    }
    public Node<E> getNext(){
      return next;
    }
    public void setNext(Node<E> n){
      next = n;
    }
  }
  
  public SinglyLinkedList(){
    head = null;
    tail = null;
    this.size = 0;
  }
  
  public int size(){
    return this.size;
  }
  
  public boolean isEmpty(){
    if(size==0){
      return true;
    }
    else{
      return false;
    }
  }
  
  public E first(){
    return head.getElement();
  }
  
  public E last(){
    return tail.getElement();
  }
  
  public void addFirst(E element){
    head = new Node<E>(element, head);
    if(isEmpty()){
      tail = head;
    }
    size++;
  }
  
  public void addLast(E element){
    Node<E> new_node = new Node<E>(element, null);
    if(isEmpty()){
      head = new_node;
      tail = new_node;
    }
    else{
      tail.setNext(new_node);
      tail = new_node;
    }
    size++;
  }
  
  public E removeFirst(){
    if(isEmpty()){
      return null;
    }
    E element = head.getElement();
    head = head.getNext();
    size--;
    if(size==0){
      tail = null;
    }
    return element;
  }

}