

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
        Deque<Character> dq= wordToDeque(word);
        while (dq.size() > 1) {
            char a = dq.removeFirst();
            char b = dq.removeLast();
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq= wordToDeque(word);
        while (dq.size() > 1) {
            char a = dq.removeFirst();
            char b = dq.removeLast();
            if (!cc.equalChars(a, b)) {
                return false;
            }
        }
        return true;
    }
}
