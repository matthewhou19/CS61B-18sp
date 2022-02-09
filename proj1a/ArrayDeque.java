public class ArrayDeque {


    public boolean isEmpty() {
        return  false;
    }

    /** return Deque size with constant time */
    public int size() {
        return size;
    }

    /** add an item at  first */
    public void  addFirst(T item){
        LinkedListDeque.Node temp = sentinel.next;
        LinkedListDeque.Node curr = new LinkedListDeque.Node(item);
        curr.next = temp;
        temp.pre = curr;
        sentinel.next = curr;
        curr.pre = sentinel;
        size++;
    }

    /** add an item at last */
    public void  addLast(T item) {
        LinkedListDeque.Node temp = sentinel.pre;
        LinkedListDeque.Node curr = new LinkedListDeque.Node(item);
        curr.pre = temp;
        temp.next = curr;
        sentinel.pre = curr;
        curr.next = sentinel;
        size++;
    }

    /** remove the first item*/
    public T removeFirst() {
        if (size == 0) return null;
        LinkedListDeque.Node curr = sentinel.next;
        LinkedListDeque.Node remain = sentinel.next.next;
        sentinel.next = remain;
        remain.pre = sentinel;
        size--;
        return curr.item;
    }

    /** remove the last item */
    public T removeLast() {
        if (size == 0) return null;
        LinkedListDeque.Node curr = sentinel.pre;
        LinkedListDeque.Node remain = curr.pre;
        sentinel.pre = remain;
        remain.next = sentinel;
        size--;
        return curr.item;
    }

    /** get the ith item */
    public T get(int index) {
        if (index < 0 || index >= size) return  null;
        LinkedListDeque.Node curr = sentinel.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        return curr.item;
    }

    /** get the ith item by using a recursive way */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) return  null;
        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque() {
        LinkedListDeque.Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item);
            System.out.print(' ');
            curr = curr.next;
        }
    }





}
