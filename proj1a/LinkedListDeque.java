/**Implement LinkedList based Double end Queue class*/
public class LinkedListDeque<T> {
    /** nested Node class */
    private class Node{
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
        sentinel = new Node(null);
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
        Node temp = sentinel.next;
        Node curr = new Node(item);
        curr.next = temp;
        temp.pre = curr;
        sentinel.next = curr;
        curr.pre = sentinel;
        size++;
    }

    /** add an item at last */
    public void  addLast(T item) {
        Node temp = sentinel.pre;
        Node curr = new Node(item);
        curr.pre = temp;
        temp.next = curr;
        sentinel.pre = curr;
        curr.next = sentinel;
        size++;
    }

    /** remove the first item*/
    public T removeFist() {
        if (size == 0) return null;
        Node curr = sentinel.next;
        Node remain = sentinel.next.next;
        sentinel.next = remain;
        remain.pre = sentinel;
        return curr.item;
    }

    /** remove the last item */
    public T removeLast() {
        if (size == 0) return null;
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
