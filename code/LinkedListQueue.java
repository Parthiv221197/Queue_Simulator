public class LinkedListQueue<E> implements Queue<E>{

  private SinglyLinkedList<E> list;
  private int size;


  public LinkedListQueue(){

    list = new SinglyLinkedList<E>();
    this.size = 0;
  }
  public int size(){
    return this.size;
  }
  public boolean isEmpty(){
   return list.isEmpty();
  }
  public E first(){
    return list.first();
  }
  public void enqueue(E node){
    list.addLast(node);
    size++;
  }
  public E dequeue(){
    size--;
    return list.removeFirst();
  }
}
