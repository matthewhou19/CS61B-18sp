/**Implement LinkedList based Double end Queue class*/
public class LinkedListDeque<Item> implements Deque<Item>{
    /** nested Node class */
    private class Node {
        Item item;
        Node pre;
        Node next;

        public Node(Item i) {
            item = i;
            pre = null;
            next = null;
        }
    }

    private Node sentinel;
    private int size;

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


    /** return Deque size with constant time */
    @Override
    public int size() {
        return size;
    }

    /** add an item at  first */
    @Override
    public void  addFirst(Item item) {
        Node temp = sentinel.next;
        Node curr = new Node(item);
        curr.next = temp;
        temp.pre = curr;
        sentinel.next = curr;
        curr.pre = sentinel;
        size++;
    }

    /** add an item at last */
    @Override
    public void  addLast(Item item) {
        Node temp = sentinel.pre;
        Node curr = new Node(item);
        curr.pre = temp;
        temp.next = curr;
        sentinel.pre = curr;
        curr.next = sentinel;
        size++;
    }

    /** remove the first item*/
    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Node curr = sentinel.next;
        Node remain = sentinel.next.next;
        sentinel.next = remain;
        remain.pre = sentinel;
        size--;
        return curr.item;
    }

    /** remove the last item */
    @Override
    public Item removeLast() {
        if (size == 0)  {
            return null;
        }
        Node curr = sentinel.pre;
        Node remain = curr.pre;
        sentinel.pre = remain;
        remain.next = sentinel;
        size--;
        return curr.item;
    }

    /** get the ith item */
    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return  null;
        }
        Node curr = sentinel.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        return curr.item;
    }

    /** get the ith item by using a recursive way */
    public Item getRecursive(int index) {
        if (index < 0 || index >= size) {
            return  null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private Item getRecursiveHelper(Node curr, int index) {
        if (index == 0) {
            return  curr.item;
        }
        return  getRecursiveHelper(curr.next, index - 1);
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item);
            System.out.print(' ');
            curr = curr.next;
        }
    }
}
