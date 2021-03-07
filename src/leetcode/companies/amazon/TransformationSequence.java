package leetcode.companies.amazon;

import java.util.*;

/**
 * 127. Word Ladder
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words such that:
 *
 * The first word in the sequence is beginWord.
 * The last word in the sequence is endWord.
 * Only one letter is different between each adjacent pair of words in the sequence.
 * Every word in the sequence is in wordList.
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with 5 words.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no possible transformation.
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the strings in wordList are unique.
 */
public class TransformationSequence {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>();
        words.addAll(wordList);
        if (!words.contains(endWord)) return 0;
        int l = beginWord.length();
        int level = 0;
        Deque<String> q = new ArrayDeque<>();
        q.add(beginWord);
        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int a = 0; a < size; a++) {
                String curr = q.poll();
                if (curr.equals(endWord)) return level + 1;
                visited.add(curr);
                for (int i = 0; i < l; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        StringBuilder sb = new StringBuilder(curr);
                        sb.setCharAt(i, c);
                        if (words.contains(sb.toString()) && !visited.contains(sb.toString())) {
                            visited.add(sb.toString());
                            q.add(sb.toString());
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        TransformationSequence t = new TransformationSequence();
        System.out.println(t.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

}
