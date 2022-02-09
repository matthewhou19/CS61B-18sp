public class ArrayDeque<T> {
    private static final int startSize = 8;
    private static final int factor = 2;
    /** For arrays of length 16 or more, your usage factor
     * should always be at least 25%. For smaller arrays,
     * your usage factor can be arbitrarily low. */
    int capacity;
    T[] container;
    int nextFirst;
    int nextLast;
    int size;
    /** Constructor*/
    public ArrayDeque() {
        capacity = 8;
        container = (T[]) new Object[capacity];
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
    public void  addFirst(T item){
        if (size == capacity) {
            resize(capacity * factor);
        }
        container[nextFirst] = item;
        nextFirst = (capacity + nextFirst - 1) % capacity;
    }
    
    /** add an item at last */
    public void  addLast(T item) {
        if (size == capacity) {
            resize(capacity * factor);
        }
        container[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
    }

    /** remove the first item*/
    public T removeFirst() {
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


    /** print all the elements in the Deque*/
    public void printDeque() {

    }

    /**add the first when Deque is empty,no matter what direction*/


    /** resize the container */
    private void resize(int c) {
        T[] origin = container;
        container = (T[]) new Object[c];
        int j = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            container[i] = origin[j / capacity];
            j++;
        }
        nextFirst = capacity - 1;
        nextLast = size;
    }





}
