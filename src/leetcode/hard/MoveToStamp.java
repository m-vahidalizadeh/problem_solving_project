package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 936. Stamping The Sequence
 * You want to form a target string of lowercase letters.
 *
 * At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.
 *
 * On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.
 *
 * For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)
 *
 * If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.
 *
 * For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".
 *
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
 *
 * Example 1:
 *
 * Input: stamp = "abc", target = "ababc"
 * Output: [0,2]
 * ([1,0,2] would also be accepted as an answer, as well as some other answers.)
 * Example 2:
 *
 * Input: stamp = "abca", target = "aabcaca"
 * Output: [3,0,1]
 *
 * Note:
 *
 * 1 <= stamp.length <= target.length <= 1000
 * stamp and target only contain lowercase letters.
 */
public class MoveToStamp {

    int n;
    int m;
    char[] stampChars;
    char[] targetChars;

    public int[] movesToStamp(String stamp, String target) {
        n = target.length();
        m = stamp.length();
        stampChars = stamp.toCharArray();
        targetChars = target.toCharArray();
        int fixed = 0;
        List<Integer> answer = new ArrayList<>();
        boolean anyReplace = false;
        boolean[] visited = new boolean[n];
        while (fixed < n) {
            anyReplace = false;
            for (int i = 0; i <= n - m; i++) {
                if (!visited[i] && canReplace(i)) {
                    fixed = doReplace(i, fixed);
                    anyReplace = true;
                    answer.add(i);
                    visited[i] = true;
                    if (fixed == n) break;
                }
            }
            if (!anyReplace) {
                return new int[0];
            }
        }
        int size = answer.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) result[size - 1 - i] = answer.get(i);
        return result;
    }

    private boolean canReplace(int start) {
        for (int i = 0; i < m; i++) {
            if (targetChars[start + i] != '?' && targetChars[start + i] != stampChars[i]) return false;
        }
        return true;
    }

    private int doReplace(int start, int fixed) {
        for (int i = 0; i < m; i++) {
            if (targetChars[start + i] != '?') {
                fixed++;
                targetChars[start + i] = '?';
            }
        }
        return fixed;
    }

    public static void main(String[] args) {
        MoveToStamp moveToStamp = new MoveToStamp();
        System.out.println(Arrays.toString(moveToStamp.movesToStamp("abc", "ababc")));
        System.out.println(Arrays.toString(moveToStamp.movesToStamp("abca", "aabcaca")));
    }

}
