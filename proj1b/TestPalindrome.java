import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne ob1 = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("A"));
        assertFalse(palindrome.isPalindrome("aA"));
        assertTrue(palindrome.isPalindrome("deified"));
        assertFalse(palindrome.isPalindrome("aaabbbbb"));
    }

    @Test
    public void testIsPalindromeCC(){
        assertTrue(palindrome.isPalindrome("flake", ob1));
        assertFalse(palindrome.isPalindrome("deified", ob1));
        assertTrue(palindrome.isPalindrome("", ob1));
        assertTrue(palindrome.isPalindrome(" ", ob1));
    }


}
