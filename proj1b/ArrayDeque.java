public class ArrayDeque<Item> implements Deque<Item> {
    private static final int STARTERSIZE = 8;
    private static final int FACTOR = 2;
    /** For arrays of length 16 or more, your usage factor
     * should always be at least 25%. For smaller arrays,
     * your usage factor can be arbitrarily low. */
    private int capacity;
    private Item[] container;
    private int nextFirst;
    private int nextLast;
    private int size;
    /** Constructor*/
    public ArrayDeque() {
        capacity = STARTERSIZE;
        container = (Item[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** return true if deque is empty*/
    public boolean isEmpty() {
        return  size == 0;
    }

    /** return Deque size with constant time */
    public int size() {
        return size;
    }

    /** add an item at  first */
    public void  addFirst(Item item) {
        if (size == capacity) {
            resize(capacity * FACTOR);
        }
        container[nextFirst] = item;
        nextFirst = (capacity + nextFirst - 1) % capacity;
        size++;
    }

    /** add an item at last */
    public void  addLast(Item item) {
        if (size == capacity) {
            resize(capacity * FACTOR);
        }
        container[nextLast] = item;
        nextLast = (nextLast + 1 + capacity) % capacity;
        size++;
    }

    /** remove the first item*/
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1 + capacity) % capacity;
        size--;
        Item item = container[nextFirst];
        double usage = (double) size / (double) capacity;
        if (usage < 0.25 && capacity >= 16) {
            resize(capacity / FACTOR);
        }
        return item;
    }

    /** remove the last item */
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + capacity) % capacity;
        size--;
        Item item = container[nextLast];
        double usage = (double) size / (double) capacity;
        if (usage < 0.25 && capacity >= 16) {
            resize(capacity / FACTOR);
        }
        return item;
    }

    /** get the ith item */
    public Item get(int index) {
        return container[(nextFirst + 1 + index) % capacity];
    }

    /** print all the elements in the Deque*/
    public void printDeque() {
        int j = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(container[j % capacity]);
            System.out.print(" ");
            j++;
        }
    }

    /** resize the container */
    private void resize(int c) {
        Item[] origin = container;
        container = (Item[]) new Object[c];
        int j = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            container[i] = origin[j % capacity];
            j++;
        }
        nextFirst = c - 1;
        nextLast = size;
        capacity = c;
    }


}
