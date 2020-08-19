package leetcode.companies.google;

import java.util.*;

/**
 * Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 * <p>
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * <p>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output: 5
 * <p>
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 * <p>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * Output: 0
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class LadderLength {

    Map<String, List<String>> regex;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        regex = new HashMap<>();
        int n = beginWord.length();
        for (String word : wordList) {
            for (int i = 0; i < n; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, n);
                List<String> existingVal = regex.getOrDefault(newWord, new ArrayList<>());
                existingVal.add(word);
                regex.put(newWord, existingVal);
            }
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Pair entry = q.poll();
            for (int i = 0; i < n; i++) {
                String newWord = entry.word.substring(0, i) + '*' + entry.word.substring(i + 1, n);
                for (String adj : regex.getOrDefault(newWord, new ArrayList<>())) {
                    if (adj.equals(endWord)) return entry.level + 1;
                    if (!visited.contains(adj)) {
                        visited.add(adj);
                        q.add(new Pair(adj, entry.level + 1));
                    }
                }
            }
        }
        return 0;
    }

    public class Pair {
        String word;
        int level;

        public Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        LadderLength l = new LadderLength();
        System.out.println(l.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

}
