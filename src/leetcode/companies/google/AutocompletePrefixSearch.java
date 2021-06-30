package leetcode.companies.google;

import java.util.*;

/**
 * 642. Design Search Autocomplete System
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Implement the AutocompleteSystem class:
 *
 * AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 * List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 * Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.
 *
 * Example 1:
 *
 * Input
 * ["AutocompleteSystem", "input", "input", "input", "input"]
 * [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 * Output
 * [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *
 * Explanation
 * AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 * obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 * obj.input("a"); // return []. There are no sentences that have prefix "i a".
 * obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 * Constraints:
 *
 * n == sentences.length
 * n == times.length
 * 1 <= n <= 100
 * 1 <= sentences[i].length <= 100
 * 1 <= times[i] <= 50
 * c is a lowercase English letter, a hash '#', or space ' '.
 * Each tested sentence will be a sequence of characters c that end with the character '#'.
 * Each tested sentence will have a length in the range [1, 200].
 * The words in each input sentence are separated by single spaces.
 * At most 5000 calls will be made to input.
 */
public class AutocompletePrefixSearch {

    MyTrie trie;

    public AutocompletePrefixSearch(String[] sentences, int[] times) {
        trie = new MyTrie(sentences, times);
    }

    public List<String> input(char c) {
        return trie.getTopThree(c);
    }

    public class MyTrie {
        public Trie root;
        public StringBuilder key;
        public PriorityQueue<Trie> pq;

        public MyTrie(String[] sentences, int[] times) {
            root = new Trie();
            key = new StringBuilder();
            for (int i = 0; i < sentences.length; i++) {
                insert(sentences[i], times[i]);
            }
        }

        public void search() {
            Trie curr = root;
            for (int j = 0; j < key.length(); j++) {
                char c = key.charAt(j);
                int i;
                if (c == ' ') i = 26;
                else i = c - 'a';
                if (curr.children[i] == null) return;
                curr = curr.children[i];
            }
            Queue<Trie> q = new LinkedList<>();
            q.add(curr);
            while (!q.isEmpty()) {
                curr = q.poll();
                if (curr.isSentence) pq.add(curr);
                for (int i = 0; i < 27; i++) {
                    if (curr.children[i] != null) q.add(curr.children[i]);
                }
            }
        }

        public List<String> getTopThree(char c) {
            if (c == '#') {
                insertKey();
                resetKey();
                return Collections.emptyList();
            }
            pq = new PriorityQueue<>((a, b) -> a.freq == b.freq ? a.sentence.compareTo(b.sentence) : b.freq - a.freq);
            key.append(c);
            search();
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 3 && !trie.pq.isEmpty(); i++) res.add(trie.pq.poll().sentence);
            return res;
        }

        public void insertKey() {
            insert(key.toString(), 1);
        }

        public void resetKey() {
            key = new StringBuilder();
        }

        public void insert(String sentence, int freq) {
            Trie curr = root;
            int i;
            for (char c : sentence.toCharArray()) {
                if (c == ' ') i = 26;
                else i = c - 'a';
                if (curr.children[i] == null) curr.children[i] = new Trie();
                curr = curr.children[i];
            }
            if (!curr.isSentence) {
                curr.isSentence = true;
                curr.sentence = sentence;
                curr.freq = freq;
            } else curr.freq += freq;
        }

        public class Trie {
            Trie[] children;
            boolean isSentence;
            String sentence;
            int freq;

            public Trie() {
                children = new Trie[27]; // a-z->0-26 ' '->27
                freq = 0;
            }
        }
    }

}
