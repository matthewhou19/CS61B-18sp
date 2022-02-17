package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        int times = 10;
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(times);
        for (int i = 0; i < times; i++) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
        for (int i = 0; i < times; i++) {
            int b = arb.peek();
            assertEquals(i, b);
            b = arb.dequeue();
            assertEquals(i, b);
        }
        assertTrue(arb.isEmpty());
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
