/**Implement LinkedList based Double end Queue class*/
public class LinkedListDeque<T> {
    /** nested Node class */
    private static class Node<T>{
        T item;
        Node pre;
        Node next;

        public Node(T i){
            item = i;
            pre = null;
            next = null;
        }
    }

    Node sentinel;
    int size;

    /** Constructor
     * 1. circular sentinel implementation
     * 2. set size with 0
     * */
    public LinkedListDeque() {
        sentinel = new Node(0);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** return true if deque is empty*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** return Deque size with constant time */
    public int size() {
        return size;
    }

    /** add an item at  first */
    public void  addFirst(T item){
        

    }

    /** add an item at last */
    public void  addLast(T item) {

    }

    /** remove the first item*/
    public T removeFist() {
        return null;
    }

    /** remove the last item */
    public T removeLast() {
        return null;
    }

    /** get the ith item */
    public T get(int index) {
        return null;
    }

    /** get the ith item by using a recursive way */
    public T getRecursive(int index) {
        return null;
    }
}
