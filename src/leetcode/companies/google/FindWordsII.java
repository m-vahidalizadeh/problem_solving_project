package leetcode.companies.google;

import java.util.*;

/**
 * 212. Word Search II
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class FindWordsII {

    Trie root;
    int n;
    int m;
    Set<String> res;
    int[][] directions;

    public List<String> findWords(char[][] board, String[] words) {
        root = new Trie();
        n = board.length;
        m = board[0].length;
        res = new HashSet<>();
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (String word : words) insert(word);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, i, j, "");
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int i, int j, String pre) {
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] == '#') return;
        pre = pre + board[i][j];
        int isPre = isPrefix(pre);
        if (isPre == 0) return;
        if (isPre == 2) res.add(pre);
        char temp = board[i][j];
        board[i][j] = '#';
        for (int[] dir : directions) dfs(board, i + dir[0], j + dir[1], pre);
        board[i][j] = temp;
    }

    public int isPrefix(String prefix) { // not prefix 0, prefix 1, word 2
        Trie curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else return 0;
        }
        return curr.isWord ? 2 : 1;
    }

    public void insert(String word) {
        Trie curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                Trie child = new Trie();
                curr.children.put(c, child);
                curr = child;
            }
        }
        curr.isWord = true;
        curr.word = word;
    }

    public class Trie {
        boolean isWord;
        String word;
        Map<Character, Trie> children;

        public Trie() {
            children = new HashMap<>();
        }
    }

}
