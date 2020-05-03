package leetcode.mixed;

import java.util.HashMap;
import java.util.Map;

public class LeetMinStepsToMakeAnagram {

    public static void main(String[] args) {
    /*
Example 1:
Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

Example 2:
Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.

Example 3:
Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams.

Example 4:
Input: s = "xxyyzz", t = "xxyyzz"
Output: 0

Example 5:
Input: s = "friend", t = "family"
Output: 4
     */
        LeetMinStepsToMakeAnagram leetMinStepsToMakeAnagram = new LeetMinStepsToMakeAnagram();
        System.out.println(leetMinStepsToMakeAnagram.minSteps("bab", "aba"));
        System.out.println(leetMinStepsToMakeAnagram.minSteps("leetcode", "practice"));
        System.out.println(leetMinStepsToMakeAnagram.minSteps("anagram", "mangaar"));
        System.out.println(leetMinStepsToMakeAnagram.minSteps("xxyyzz", "xxyyzz"));
        System.out.println(leetMinStepsToMakeAnagram.minSteps("friend", "family"));
    }

    public int minSteps(String s, String t) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        if (s.equals(t)) {
            return 0;
        }
        Map<Character, Double> freqDiffs = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < n; i++) {
            if (freqDiffs.containsKey(sChars[i])) {
                double newValue = freqDiffs.get(sChars[i]) + 0.5;
                freqDiffs.put(sChars[i], newValue);
                if (newValue == 0) {
                    freqDiffs.remove(sChars[i]);
                }
            } else {
                freqDiffs.put(sChars[i], 0.5);
            }
            if (freqDiffs.containsKey(tChars[i])) {
                double newValue = freqDiffs.get(tChars[i]) - 0.5;
                freqDiffs.put(tChars[i], newValue);
                if (newValue == 0) {
                    freqDiffs.remove(tChars[i]);
                }
            } else {
                freqDiffs.put(tChars[i], -0.5);
            }
        }
        double diffs = 0d;
        for (Map.Entry<Character, Double> fd : freqDiffs.entrySet()) {
            diffs += Math.abs(fd.getValue());
        }
        return (int) diffs;
    }

}
