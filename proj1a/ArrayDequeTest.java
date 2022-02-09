public class ArrayDequeTest {

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int i = 0;
        while(i < 5) {
            ad.addFirst(i);
            i++;
        }
        while(i < 10) {
            ad.addLast(i);
            i++;
        }
        ad.printDeque();
        System.out.println(ad.size());
        for(int j = 0; j < 7; j++) {
            ad.removeFirst();
        }
        for (int j = 0; j < 3; j++) {
            ad.removeLast();
        }
        System.out.println(ad.isEmpty());

        for(int j = 0; j < 10; j++) {
            ad.addFirst(j);
        }
        ad.printDeque();
        for (int j = 0; j < 10; j++) {
            System.out.println(ad.removeLast());
        }
       System.out.println(ad.size());
        for (int j = 0; j < 10; j++) {
            ad.addLast(j);
        }
        ad.printDeque();

    }
}
