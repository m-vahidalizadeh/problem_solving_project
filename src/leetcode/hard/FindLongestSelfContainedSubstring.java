package leetcode.hard;

/**
 * 3104. Find Longest Self-Contained Substring
 * Solved
 * Hard
 *
 * Hint
 * Given a string s, your task is to find the length of the longest self-contained
 * substring
 *  of s.
 *
 * A substring t of a string s is considered self-contained if t != s and for every character in t, it doesn't exist in the rest of s.
 *
 * Return the length of the longest self-contained substring of s if it exists, otherwise, return -1.
 *
 * Example 1:
 *
 * Input: s = "abba"
 *
 * Output: 2
 *
 * Explanation:
 * Let's check the substring "bb". You can see that no other "b" is outside of this substring. Hence the answer is 2.
 *
 * Example 2:
 *
 * Input: s = "abab"
 *
 * Output: -1
 *
 * Explanation:
 * Every substring we choose does not satisfy the described property (there is some character which is inside and outside of that substring). So the answer would be -1.
 *
 * Example 3:
 *
 * Input: s = "abacd"
 *
 * Output: 4
 *
 * Explanation:
 * Let's check the substring "abac". There is only one character outside of this substring and that is "d". There is no "d" inside the chosen substring, so it satisfies the condition and the answer is 4.
 *
 * Constraints:
 *
 * 2 <= s.length <= 5 * 104
 * s consists only of lowercase English letters.
 */
public class FindLongestSelfContainedSubstring {

    public int maxSubstringLength(String s) {
        int n = s.length(), res = -1, totalCount[] = new int[26];
        for (int i = 0; i < n; i++) totalCount[s.charAt(i) - 'a']++; // Count total number of chars in the String
        for (int i = 1; i <= 26; i++) { // i is the number of unique chars in the sub
            int valid = 0, unique = 0, left = 0, count[] = new int[26];
            for (int right = 0; right < n; right++) {
                count[s.charAt(right) - 'a']++; // Add the char to the sub
                if (count[s.charAt(right) - 'a'] == 1) unique++; // we saw a new char
                if (count[s.charAt(right) - 'a'] == totalCount[s.charAt(right) - 'a']) valid++; // all of this char is in this sub
                for(;unique > i;left++) {
                    count[s.charAt(left) - 'a']--; // Remove the char from sub
                    if (count[s.charAt(left) - 'a'] == 0) unique--; // We lost one of the new chars
                    if (count[s.charAt(left) - 'a'] == totalCount[s.charAt(left) - 'a'] - 1) valid--; // Not all of the char is in this sub anymore
                }
                if (valid == i && right - left + 1 != n) res = Math.max(res, right - left + 1); // make sure it is not the string itself
            }
        }
        return res;
    }

}
