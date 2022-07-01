package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = 0;
        }
        for (Oomage o : oomages ) {
            int h = o.hashCode() ;
            int i =( h &  0x7FFFFFFF) % M;
            buckets[i] = buckets[i] + 1;
        }

        int N = oomages.size();

        for (int bucket : buckets) {
            if (bucket < N / 50 || bucket > N / 2.5) return false;
        }

        return true;
    }
}
