package leetcode.companies.adobe;

/**
 * Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class CommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) return "";
        if (n == 1) return strs[0];
        return findPrefixRec(strs, n, 0);
    }

    private String findPrefixRec(String[] strs, int n, int currIndex) {
        Character c = null;
        for (int i = 0; i < n; i++) {
            String currentString = strs[i];
            if (currIndex >= strs[i].length()) return currIndex == 0 ? "" : currentString.substring(0, currIndex);
            if (c == null) {
                c = currentString.charAt(currIndex);
            } else {
                if (currentString.charAt(currIndex) != c) return currentString.substring(0, currIndex);
            }
        }
        return findPrefixRec(strs, n, currIndex + 1);
    }

    public static void main(String[] args) {
        CommonPrefix c = new CommonPrefix();
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(c.longestCommonPrefix(strs1));
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(c.longestCommonPrefix(strs2));
        String[] strs3 = {"", "b"};
        System.out.println(c.longestCommonPrefix(strs3));
    }

}
