package leetcode.companies.google;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Word Search II
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
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

    static class Trie {
        boolean isWord;
        Map<Character, Trie> children;
        char val;

        public Trie(char val, boolean isWord) {
            this.val = val;
            this.isWord = isWord;
            children = new HashMap<>();
        }
    }

    Set<String> resultSet;
    Trie root;
    int n;
    int m;
    Set<String> wordsSet;

    public List<String> findWords(char[][] board, String[] words) {
        n = board.length;
        if (n == 0) return Collections.emptyList();
        if (words.length == 0) return Collections.emptyList();
        m = board[0].length;
        buildTrie(words);
        resultSet = new HashSet<>();
        wordsSet = new HashSet<>(Arrays.asList(words));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, i, j, "");
            }
        }
        return resultSet.stream().collect(Collectors.toUnmodifiableList());
    }

    private void dfs(char[][] board, int i, int j, String prefix) {
        if (i < 0 || i == n || j < 0 || j == m) return;
        prefix += board[i][j];
        if (wordsSet.contains(prefix)) {
            resultSet.add(prefix);
            char temp = board[i][j];
            board[i][j] = '#';
            dfs(board, i + 1, j, prefix);
            dfs(board, i - 1, j, prefix);
            dfs(board, i, j + 1, prefix);
            dfs(board, i, j - 1, prefix);
            board[i][j] = temp;
        } else {
            if (!prefixExists(prefix)) return;
            char temp = board[i][j];
            board[i][j] = '#';
            dfs(board, i + 1, j, prefix);
            dfs(board, i - 1, j, prefix);
            dfs(board, i, j + 1, prefix);
            dfs(board, i, j - 1, prefix);
            board[i][j] = temp;
        }
    }

    private boolean prefixExists(String prefix) {
        Trie curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char pI = prefix.charAt(i);
            if (!curr.children.containsKey(pI)) return false;
            else curr = curr.children.get(pI);
        }
        return true;
    }

    private void buildTrie(String[] words) {
        root = new Trie('@', false);
        for (String word : words) {
            int l = word.length();
            Trie curr = root;
            for (int i = 0; i < l; i++) {
                char c = word.charAt(i);
                curr.children.putIfAbsent(c, new Trie(c, i == l - 1));
                curr = curr.children.get(c);
            }
        }
    }

    public static void main(String[] args) {
        WordSearchII w = new WordSearchII();
        String[] words1 = {"oath", "pea", "eat", "rain"};
        char[][] board1 = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        System.out.println(w.findWords(board1, words1));
    }

}
