package leetcode.companies.microsoft;

/**
 * Implement strStr()
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class NeedleInHaystack {

    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (n == 0) return m == 0 ? 0 : -1;
        for (int i = 0; i < n - m + 1; i++) {
            if (doesMatch(haystack, i, needle, m)) return i;
        }
        return -1;
    }

    private boolean doesMatch(String haystack, int s, String needle, int m) {
        for (int i = 0; i < m; i++) if (haystack.charAt(s + i) != needle.charAt(i)) return false;
        return true;
    }

    public static void main(String[] args) {
        NeedleInHaystack n = new NeedleInHaystack();
        System.out.println(n.strStr("hello", "ll"));
        System.out.println(n.strStr("aaaaaa", "bba"));
    }

}
