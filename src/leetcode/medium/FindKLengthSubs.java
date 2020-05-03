package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string S, return the number of substrings of length K with no repeated characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: S = "havefunonleetcode", K = 5
 * Output: 6
 * Explanation:
 * There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
 * Example 2:
 * <p>
 * Input: S = "home", K = 5
 * Output: 0
 * Explanation:
 * Notice K can be larger than the length of S. In this case is not possible to find any substring.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 10^4
 * All characters of S are lowercase English letters.
 * 1 <= K <= 10^4
 */
public class FindKLengthSubs {

    static class Response {
        boolean noRepeated = false;
        Set<Character> existing;

        Response(boolean noRepeated) {
            this.noRepeated = noRepeated;
        }

        Response(boolean noRepeated, Set<Character> existing) {
            this.noRepeated = noRepeated;
            this.existing = existing;
        }
    }

    public static void main(String[] args) {
        FindKLengthSubs findKLengthSubs = new FindKLengthSubs();
        System.out.println(findKLengthSubs.numKLenSubstrNoRepeats("havefunonleetcode", 5));
        System.out.println(findKLengthSubs.numKLenSubstrNoRepeats("home", 5));
    }

    public int numKLenSubstrNoRepeats(String S, int K) {
        char[] sChars = S.toCharArray();
        int n = sChars.length;
        int numOfSubs = 0;
        if (n < K) {
            return numOfSubs;
        }
        Response lastResponse = new Response(false);
        for (int i = 0; i < n - K + 1; i++) {
            Response res = noRepeatedChars(sChars, i, (i + K), lastResponse);
            if (res.noRepeated) {
                numOfSubs++;
            }
            lastResponse = res;
        }
        return numOfSubs;
    }

    private Response noRepeatedChars(char[] sChars, int start, int end, Response lastRes) {
        if (lastRes.noRepeated) {
            Set<Character> newExisting = lastRes.existing;
            newExisting.remove(sChars[start - 1]);
            if (newExisting.contains(sChars[end - 1])) {
                return new Response(false);
            } else {
                newExisting.add(sChars[end - 1]);
                return new Response(true, newExisting);
            }
        } else {
            return noRepeatedChars(sChars, start, end);
        }
    }

    private Response noRepeatedChars(char[] sChars, int start, int end) {
        Set<Character> existing = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (existing.contains(sChars[i])) {
                return new Response(false);
            } else {
                existing.add(sChars[i]);
            }
        }
        return new Response(true, existing);
    }

}
