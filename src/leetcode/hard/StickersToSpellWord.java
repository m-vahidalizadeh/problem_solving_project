package leetcode.hard;

import java.util.*;

/**
 * 691. Stickers to Spell Word
 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
 *
 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
 *
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input:
 *
 * ["with", "example", "science"], "thehat"
 * Output:
 *
 * 3
 * Explanation:
 *
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 * Example 2:
 *
 * Input:
 *
 * ["notice", "possible"], "basicbasic"
 * Output:
 *
 * -1
 * Explanation:
 *
 * We can't form the target "basicbasic" from cutting letters from the given stickers.
 * Note:
 *
 * stickers has length in the range [1, 50].
 * stickers consists of lowercase English words (without apostrophes).
 * target has length in the range [1, 15], and consists of lowercase English letters.
 * In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
 * The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class StickersToSpellWord {

    private boolean isEmpty(int[] freq) {
        for (int f : freq) if (f > 0) return false;
        return true;
    }

    private String getkey(int[] freq) {
        StringBuilder sb = new StringBuilder();
        char c = 'a';
        for (int f : freq) {
            while (f-- > 0) sb.append(c);
            c++;
        }
        return sb.toString();
    }

    public int minStickers(String[] stickers, String target) {
        int[] targetNativeFreq = new int[26];
        for (char c : target.toCharArray()) targetNativeFreq[c - 'a']++;
        int[] index = new int[26];
        int n = 0;
        for (int i = 0; i < 26; i++) {
            if (targetNativeFreq[i] > 0) index[i] = n++;
            else index[i] = -1;
        }
        int[] targetFreq = new int[n];
        int t = 0;
        for (int c : targetNativeFreq) {
            if (c > 0) {
                targetFreq[t++] = c;
            }
        }
        int stickersLength = stickers.length;
        int[][] stickersFreq = new int[stickersLength][n];
        for (int i = 0; i < stickersLength; i++) {
            for (char c : stickers[i].toCharArray()) {
                int j = index[c - 'a'];
                if (j >= 0) stickersFreq[i][j]++;
            }
        }
        int start = 0;
        for (int i = 0; i < stickersLength; i++) {
            for (int j = start; j < stickersLength; j++) {
                if (i == j) continue;
                int k = 0;
                while (k < n && stickersFreq[i][k] <= stickersFreq[j][k]) k++;
                if (k == n) {
                    int[] temp = stickersFreq[i];
                    stickersFreq[i] = stickersFreq[start];
                    stickersFreq[start++] = temp;
                    break;
                }
            }
        }
        List<int[]> currlevel = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        currlevel.add(targetFreq);
        int steps = 0;
        while (!currlevel.isEmpty()) {
            steps++;
            List<int[]> nextLevel = new ArrayList<>();
            for (int[] curr : currlevel) {
                String key = getkey(curr);
                if (visited.contains(key)) continue;
                int first = key.charAt(0) - 'a';
                for (int i = start; i < stickersLength; i++) {
                    if (stickersFreq[i][first] != 0) {
                        int[] next = curr.clone();
                        for (int j = 0; j < n; j++) next[j] = Math.max(0, next[j] - stickersFreq[i][j]);
                        if (isEmpty(next)) return steps;
                        nextLevel.add(next);
                    }
                }
                visited.add(key);
            }
            // Next
            currlevel = nextLevel;
        }
        return -1;
    }

}
