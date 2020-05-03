package leetcode.medium;

/**
 * Maximum Nesting Depth of Two Valid Parentheses Strings
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
 * <p>
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 * <p>
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 * <p>
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length).
 * <p>
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 * <p>
 * Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.
 * <p>
 * Example 1:
 * <p>
 * Input: seq = "(()())"
 * Output: [0,1,1,1,1,0]
 * Example 2:
 * <p>
 * Input: seq = "()(())()"
 * Output: [0,0,0,1,1,0,1,1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= seq.size <= 10000
 */
public class VPS {

    public static void main(String[] args) {
        VPS v = new VPS();
        System.out.println(v.maxDepthAfterSplit("(()())"));
    }

    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int a = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                if (a < b) {
                    a++;
                } else {
                    b++;
                    result[i] = 1;
                }
            } else {
                // ')'
                if (a > b) {
                    a--;
                } else {
                    b--;
                    result[i] = 1;
                }
            }
        }
        return result;
    }

}
