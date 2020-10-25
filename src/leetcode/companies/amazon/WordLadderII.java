package leetcode.companies.amazon;

import java.util.*;

/**
 * 126. Word Ladder II
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {

    Map<String, Set<String>> graph;
    int l;
    Integer pathSize;
    Set<List<String>> paths;

    public class Path {
        int size;
        LinkedList<String> nodes;
        String val;

        public Path(int size, LinkedList<String> nodes, String val) {
            this.size = size;
            this.nodes = nodes;
            this.val = val;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        if (n == 0 || !wordList.contains(endWord)) return Collections.emptyList();
        graph = new HashMap<>();
        String[] words = new String[n + 2];
        for (int i = 0; i < n; i++) words[i] = wordList.get(i);
        l = beginWord.length();
        words[n] = beginWord;
        words[n + 1] = endWord;
        paths = new HashSet<>();
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
        LinkedList<String> beginNodes = new LinkedList<>();
        beginNodes.add(beginWord);
        q.add(new Path(0, beginNodes, beginWord));
        while (!q.isEmpty()) {
            Path curr = q.poll();
            if (pathSize != null && curr.size > pathSize) break;
            if (curr.val.equals(endWord)) {
                pathSize = curr.size;
                paths.add(curr.nodes);
            } else {
                visited.add(curr.val);
                if (graph.containsKey(curr.val) && !graph.get(curr.val).isEmpty()) {
                    for (String c : graph.get(curr.val)) {
                        if (visited.contains(c)) continue;
                        curr.nodes.addLast(c);
                        q.add(new Path(curr.size + 1, new LinkedList<>(curr.nodes), c));
                        curr.nodes.removeLast();
                    }
                }
            }
        }

        return new ArrayList<>(paths);
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
        WordLadderII w = new WordLadderII();
        System.out.println(w.findLadders("hit", "cog",
                List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

}
