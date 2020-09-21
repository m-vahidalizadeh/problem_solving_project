package leetcode.companies.google;

/**
 * Binary String With Substrings Representing 1 To N
 * Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "0110", N = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: S = "0110", N = 4
 * Output: false
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 1000
 * 1 <= N <= 10^9
 */
public class QuerySubstring {

    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N; i++) {
            if (!S.contains(Integer.toBinaryString(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        QuerySubstring q = new QuerySubstring();
        System.out.println(q.queryString("0110", 3));
        System.out.println(q.queryString("0110", 4));
    }

}
