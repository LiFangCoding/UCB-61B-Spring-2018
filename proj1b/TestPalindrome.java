import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

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
    public void testPalindrome() {
        String sT1 = "a";
        String sT2 = "racecar";
        String sT3 = "noon";
        String sT4 = "";
        String sT5 = " ";
        String sF1 = "horse";
        String sF2 = "rancor";
        String sF3 = "aaaab";
        assertTrue(palindrome.isPalindrome(sT1));
        assertTrue(palindrome.isPalindrome(sT2));
        assertTrue(palindrome.isPalindrome(sT3));
        assertTrue(palindrome.isPalindrome(sT4));
        assertTrue(palindrome.isPalindrome(sT5));
        assertFalse(palindrome.isPalindrome(sF1));
        assertFalse(palindrome.isPalindrome(sF2));
        assertFalse(palindrome.isPalindrome(sF3));

    }

}
