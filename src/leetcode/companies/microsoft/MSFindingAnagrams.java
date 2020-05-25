package leetcode.companies.microsoft;

import java.util.Arrays;

public class MSFindingAnagrams {

    public static void main(String[] args) {
/*
Example 1:
Input: s = "anagram", t = "nagaram"
Output: true
Example 2:
Input: s = "rat", t = "car"
Output: false
 */
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
    }

    public static boolean isAnagram(String s, String t) {
        char[] sChars = s.toCharArray();
        Arrays.sort(sChars);
        char[] tChars = t.toCharArray();
        Arrays.sort(tChars);
        String sortedS = new String(sChars);
        String sortedT = new String(tChars);
        return sortedS.equals(sortedT);
    }

}
