package leetcode.companies.amazon;

import java.util.*;

/**
 * 212. Word Search II
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearchII {

    public class Trie {
        boolean isWord;
        Map<Character, Trie> children;

        public Trie() {
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }

    Trie root;
    char[][] board;
    int n;
    int m;
    int[][] directions;
    Set<String> dic;

    public List<String> findWords(char[][] board, String[] words) {
        root = new Trie();
        dic = new HashSet<>();
        for (String word : words) {
            insert(word);
            dic.add(word);
        }
        this.board = board;
        this.n = board.length;
        this.m = board[0].length;
        Set<String> result = new HashSet<>();
        directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, result, "");
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(int i, int j, Set<String> result, String curr) {
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] == '@') return;
        curr += board[i][j];
        if (dic.contains(curr)) result.add(curr);
        if (!isPrefix(curr)) return;
        char temp = board[i][j];
        board[i][j] = '@';
        for (int[] dir : directions) dfs(i + dir[0], j + dir[1], result, curr);
        board[i][j] = temp;
    }

    private void insert(String word) {
        int i = 0;
        int l = word.length();
        Trie curr = root;
        while (i < l) {
            char c = word.charAt(i++);
            curr.children.computeIfAbsent(c, a -> new Trie());
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    private boolean isPrefix(String prefix) {
        int i = 0;
        int l = prefix.length();
        Trie curr = root;
        while (i < l) {
            char c = prefix.charAt(i++);
            if (!curr.children.containsKey(c)) return false;
            curr = curr.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        WordSearchII w = new WordSearchII();
        System.out.println(w.findWords(
                new char[][]
                        {
                                {'o', 'a', 'a', 'n'},
                                {'e', 't', 'a', 'e'},
                                {'i', 'h', 'k', 'r'},
                                {'i', 'f', 'l', 'v'}
                        }
                ,
                new String[]
                        {"oath", "pea", "eat", "rain"}
        ));
    }

}
