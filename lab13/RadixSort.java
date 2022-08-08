import edu.princeton.cs.algs4.LSD;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        return LSDsort(asciis);
    }

    private static String[] LSDsort(String[] strs) {
        String[] res = new String[strs.length];
        int maxW = 0;
        int index = 0;
        for (String str : strs) {
            maxW = Math.max(maxW, str.length());
            res[index++] = str;
        }
        sortHelperLSD(res, maxW - 1);
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        if (index < 0) return;
        int[] counts = new int[257];
        for (String str : asciis) {
            counts[getBucket(str, index)] += 1;
        }
        int start = 0;
        int[] starts = new int[257];
        for (int i = 0; i < starts.length; i++) {
            starts[i] = start;
            start = start + counts[i];
        }

        String[] res = new String[asciis.length];
        for (String str : asciis) {
            int bucket = getBucket(str, index);
            res[starts[bucket]] = str;
            starts[bucket] += 1;
        }

        for (int i = 0; i < asciis.length;i++) {
            asciis[i] = res[i];
        }
        
        sortHelperLSD(asciis, index - 1);
    }

    private static int getBucket(String str, int index) {
        if (index >= str.length()) return 256;
        return (int)str.charAt(index);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
