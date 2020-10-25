package leetcode.companies.amazon;

import java.util.*;

/**
 * 127. Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderI {

    Map<String, Set<String>> graph;
    int l;

    public class Path {
        int size;
        String val;

        public Path(int size, String val) {
            this.size = size;
            this.val = val;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        if (n == 0 || !wordList.contains(endWord)) return 0;
        graph = new HashMap<>();
        String[] words = new String[n + 2];
        for (int i = 0; i < n; i++) words[i] = wordList.get(i);
        l = beginWord.length();
        words[n] = beginWord;
        words[n + 1] = endWord;
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == j) continue;
                String wordI = words[i];
                String wordJ = words[j];
                if (oneLetterDiff(wordI, wordJ)) {
                    graph.computeIfAbsent(wordI, a -> new HashSet<>());
                    graph.get(wordI).add(wordJ);
                    graph.computeIfAbsent(wordJ, a -> new HashSet<>());
                    graph.get(wordJ).add(wordI);
                }
            }
        }
        // BFS
        Set<String> visited = new HashSet<>();
        Queue<Path> q = new LinkedList<>();
        q.add(new Path(1, beginWord));
        while (!q.isEmpty()) {
            Path curr = q.poll();
            if (curr.val.equals(endWord)) return curr.size;
            else {
                visited.add(curr.val);
                if (graph.containsKey(curr.val) && !graph.get(curr.val).isEmpty()) {
                    for (String c : graph.get(curr.val)) {
                        if (visited.contains(c)) continue;
                        q.add(new Path(curr.size + 1, c));
                    }
                }
            }
        }

        return 0;
    }

    private boolean oneLetterDiff(String a, String b) {
        int count = 0;
        for (int i = 0; i < l; i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        WordLadderI w = new WordLadderI();
        System.out.println(w.ladderLength("hit", "cog",
                List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

}
