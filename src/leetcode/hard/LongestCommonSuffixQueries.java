package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 3093. Longest Common Suffix Queries
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given two arrays of strings wordsContainer and wordsQuery.
 *
 * For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is the smallest in length. If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.
 *
 * Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].
 *
 * Example 1:
 *
 * Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
 *
 * Output: [1,1,1]
 *
 * Explanation:
 *
 * Let's look at each wordsQuery[i] separately:
 *
 * For wordsQuery[0] = "cd", strings from wordsContainer that share the longest common suffix "cd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
 * For wordsQuery[1] = "bcd", strings from wordsContainer that share the longest common suffix "bcd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
 * For wordsQuery[2] = "xyz", there is no string from wordsContainer that shares a common suffix. Hence the longest common suffix is "", that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
 * Example 2:
 *
 * Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]
 *
 * Output: [2,0,2]
 *
 * Explanation:
 *
 * Let's look at each wordsQuery[i] separately:
 *
 * For wordsQuery[0] = "gh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.
 * For wordsQuery[1] = "acbfgh", only the string at index 0 shares the longest common suffix "fgh". Hence it is the answer, even though the string at index 2 is shorter.
 * For wordsQuery[2] = "acbfegh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.
 *
 * Constraints:
 *
 * 1 <= wordsContainer.length, wordsQuery.length <= 10^4
 * 1 <= wordsContainer[i].length <= 5 * 10^3
 * 1 <= wordsQuery[i].length <= 5 * 10^3
 * wordsContainer[i] consists only of lowercase English letters.
 * wordsQuery[i] consists only of lowercase English letters.
 * Sum of wordsContainer[i].length is at most 5 * 10^5.
 * Sum of wordsQuery[i].length is at most 5 * 10^5.
 */
public class LongestCommonSuffixQueries {

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie root = new Trie();
        int minLength = Integer.MAX_VALUE, minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < wordsContainer.length; i++) {
            addToTrie(root, wordsContainer[i], wordsContainer[i].length() - 1, i);
            if (wordsContainer[i].length() < minLength) {
                minIndex = i;
                minLength = wordsContainer[i].length();
            }
        }
        int[] res = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            int lcsIndex = findLCSIndex(root, wordsQuery[i]);
            res[i] = lcsIndex == Integer.MAX_VALUE ? minIndex : lcsIndex;
        }
        return res;
    }

    private int findLCSIndex(Trie node, String query) {
        int i = query.length() - 1;
        while (i >= 0 && node.children[query.charAt(i) - 'a'] != null) node = node.children[query.charAt(i--) - 'a'];
        int res = Integer.MAX_VALUE, resLength = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : node.indexToLengthMap.entrySet()) {
            if ((entry.getValue() < resLength) || (entry.getValue() == resLength && entry.getKey() < res)) {
                res = entry.getKey();
                resLength = entry.getValue();
            }
        }
        return res;
    }

    private void addToTrie(Trie node, String word, int wordIndex, int arrayIndex) {
        while (wordIndex >= 0) {
            Trie child = node.children[word.charAt(wordIndex) - 'a'] != null ? node.children[word.charAt(wordIndex) - 'a'] : new Trie();
            child.indexToLengthMap.put(arrayIndex, word.length());
            node=node.children[word.charAt(wordIndex--) - 'a'] = child;
        }
    }

    class Trie {
        Trie[] children = new Trie[26];
        Map<Integer, Integer> indexToLengthMap = new HashMap<>();
    }

}
