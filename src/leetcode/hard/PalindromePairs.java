package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 336. Palindrome Pairs
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lower-case English letters.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> wordDict = new HashMap<>();
        for (int i = 0; i < words.length; i++) wordDict.put(words[i], i);
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordDict.entrySet()) {
            String word = entry.getKey();
            int i = entry.getValue();
            String reversedWord = new StringBuilder(word).reverse().toString();
            if (wordDict.containsKey(reversedWord)) {
                int j = wordDict.get(reversedWord);
                if (i != j) res.add(List.of(i, j));
            }
            for (String prefix : getPrefixes(word)) {
                String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                if (wordDict.containsKey(reversedPrefix))
                    res.add(List.of(i, wordDict.get(reversedPrefix)));
            }
            for (String suffix : getSuffixes(word)) {
                String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                if (wordDict.containsKey(reversedSuffix))
                    res.add(List.of(wordDict.get(reversedSuffix), i));
            }
        }
        return res;
    }

    private List<String> getPrefixes(String word) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            String sub = word.substring(i);
            if (sub.equals(new StringBuilder(sub).reverse().toString()))
                res.add(word.substring(0, i));
        }
        return res;
    }

    private List<String> getSuffixes(String word) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            String sub = word.substring(0, i + 1);
            if (sub.equals(new StringBuilder(sub).reverse().toString()))
                res.add(word.substring(i + 1));
        }
        return res;
    }

}
