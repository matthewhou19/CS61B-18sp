

public class Palindrome {
    /**return a deque from a string, use same order!  */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public  boolean isPalindrome(String word) {
        return false;
    }
}
