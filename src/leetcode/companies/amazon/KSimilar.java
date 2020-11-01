package leetcode.companies.amazon;

import java.util.*;

/**
 * 854. K-Similar Strings
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: 1
 * Example 2:
 *
 * Input: A = "abc", B = "bca"
 * Output: 2
 * Example 3:
 *
 * Input: A = "abac", B = "baca"
 * Output: 2
 * Example 4:
 *
 * Input: A = "aabc", B = "abca"
 * Output: 2
 * Note:
 *
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class KSimilar {

    public int kSimilarity(String A, String B) {
        Map<String, Integer> swapCountMap = new HashMap<>();
        swapCountMap.put(A, 0);
        Queue<String> q = new LinkedList<>();
        q.add(A);
        while (!q.isEmpty()) {
            String curr = q.poll();
            if (curr.equals(B)) return swapCountMap.get(curr);
            for (String n : getNeighbors(curr, B)) {
                if (!swapCountMap.containsKey(n)) {
                    swapCountMap.put(n, swapCountMap.get(curr) + 1);
                    q.add(n);
                }
            }
        }
        return -1;
    }

    private List<String> getNeighbors(String curr, String B) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (curr.charAt(i) == B.charAt(i)) i++;
        char cI = curr.charAt(i);
        StringBuilder sb = new StringBuilder(curr);
        for (int j = i + 1; j < curr.length(); j++) {
            if (curr.charAt(j) == B.charAt(i)) {
                char cJ = curr.charAt(j);
                sb.setCharAt(i, cJ);
                sb.setCharAt(j, cI);
                result.add(sb.toString());
                sb.setCharAt(i, cI);
                sb.setCharAt(j, cJ);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        KSimilar k = new KSimilar();
        System.out.println(k.kSimilarity("ab", "ba"));
        System.out.println(k.kSimilarity("abc", "bca"));
        System.out.println(k.kSimilarity("abac", "baca"));
        System.out.println(k.kSimilarity("aabc", "abca"));
        System.out.println(k.kSimilarity("abccaacceecdeea", "bcaacceeccdeaae"));
    }

}
