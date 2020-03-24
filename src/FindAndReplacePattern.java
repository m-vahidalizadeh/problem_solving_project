import java.util.*;

public class FindAndReplacePattern {

    public static void main(String[] args) {
    /*
Example 1:
Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
     */
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        FindAndReplacePattern findAndReplacePattern = new FindAndReplacePattern();
        List<String> result = findAndReplacePattern.findAndReplacePattern(words, pattern);
        for (String r : result) {
            System.out.println(r);
        }
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int n = words.length;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (patternFound(words[i], pattern)) {
                result.add(words[i]);
            }
        }
        return result;
    }

    private boolean patternFound(String word, String pattern) {
        int n = word.length();
        int m = pattern.length();
        char[] wordChars = word.toCharArray();
        char[] patternChars = pattern.toCharArray();
        if (m > n) {
            return false;
        }
        for (int i = 0; i <= n - m; i++) {
            Map<Character, Character> maps = new HashMap<>();
            Set<Character> alreadyMapped = new HashSet<>();
            for (int j = 0; j < m; j++) {
                if (maps.containsKey(wordChars[i + j])) {
                    if (patternChars[j] != maps.get(wordChars[i + j])) {
                        j = m;
                    } else if (j == m - 1) {
                        return true;
                    }
                } else {
                    if (alreadyMapped.contains(patternChars[j])) {
                        j = m;
                    } else {
                        if (j == m - 1) {
                            return true;
                        }
                        maps.put(wordChars[i + j], patternChars[j]);
                        alreadyMapped.add(patternChars[j]);
                    }
                }
            }
        }
        return false;
    }


}
