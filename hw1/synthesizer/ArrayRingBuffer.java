package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int c) {

        rb = (T[]) new Object[c];
        capacity = c;
        fillCount = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw  new RuntimeException("Ring Buffer Underflow");
        }
        T res = rb[first];
        first = (first + 1) % capacity;
        fillCount--;

        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw  new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }


    @Override
    public Iterator<T> iterator() {
        return new RbIterator();
    }

    private class RbIterator implements Iterator<T> {
        int index = first;
        @Override
        public boolean hasNext() {
            return index != last;
        }

        @Override
        public T next() {
            T res = rb[index];
            index = (index + 1) % capacity;
            return res;
        }
    }

}
