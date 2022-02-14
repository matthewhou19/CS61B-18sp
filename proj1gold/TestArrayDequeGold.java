import static org.junit.Assert.*;
import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {

    @Test
    public void testStudentDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<String> actions = new LinkedList<>();


        for (Integer i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(i);
                list.addFirst(i);
                String action = "addFirst(" + i + ")";
                actions.add(action);
            } else {
                sad.addLast(i);
                list.addLast(i);
                String action = "addLast(" + i + ")";
                actions.add(action);
            }
        }
        //sad.printDeque();

        for (int i = 0 ;i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
               Integer a =sad.removeFirst();
               Integer b = list.removeFirst();
               String action = "removeFirst()";
               actions.add(action);
               String massage = "";
               for (String w: actions) {
                   massage = massage + w +"\n";
               }
               assertEquals(massage, a, b);
            } else {
                Integer a =sad.removeLast();
                Integer b = list.removeLast();
                String action = "removeLast()";
                actions.add(action);
                String massage = "";
                for (String w: actions) {
                    massage = massage + w +"\n";
                }
                assertEquals(massage, a, b);
            }
        }
        //System.out.println();
    }



}
