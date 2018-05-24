public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new ArrayDeque<>();
        //Deque<Character> res = new LinkedListDeque<>();
        if (word == null || word.length() == 0) {
            return res;
        }

        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> s = wordToDeque(word);
        if (s.size() == 0 || s.size() == 1) {
            return true;
        }

        while (s.size() > 1) {
            if (!s.removeFirst().equals(s.removeLast())) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {

    }
}
