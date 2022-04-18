package byog.Core;
import edu.princeton.cs.algs4.SET;
import org.junit.Test;
import org.junit.Assert.*;
import java.util.*;

import static org.junit.Assert.*;


public class TestPosition {




    @Test
    public void testRandomPosition() {
        int x1 = 10;
        int x2 = 20;
        int y1 = 50;
        int y2 = 60;
        List<Position> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(Position.randomPosition(x1, x2, y1, y2));
        }

        for (Position p : list) {
            int x = p.getX();
            int y = p.getY();
            assertTrue(x >= x1 & x <= x2 & y >= y1 & y<= y2);
        }

        list = new ArrayList<>();
        for ( int i = 0; i < 100; i++) {
            list.add(Position.randomPosition(x1, y1));
        }

        for (Position p : list) {
            int x = p.getX();
            int y = p.getY();
            assertTrue(x >= 0 & x <= x1 & y >= 0 & y<= y1);
        }
    }

    @Test
    public void testHashCode() {
        Set<Position> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            set.add(Position.randomPosition(10,10));
        }

        assertTrue(set.size() <= 121);
    }

    @Test
    public void testEqual() {
        Position p1 = new Position(1, 2);
        Object p2 = new Position(1, 2);
        assertTrue(p1.equals(p2));

        p2 = new Position(3, 4);
        assertNotEquals(p1, p2);
    }

}
