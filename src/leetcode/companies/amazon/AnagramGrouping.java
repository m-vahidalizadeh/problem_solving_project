package leetcode.companies.amazon;

import java.util.*;

/**
 * 49. Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lower-case English letters.
 */
public class AnagramGrouping {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String sortedString = sortString(s);
            groups.computeIfAbsent(sortedString, a -> new ArrayList<>());
            groups.get(sortedString).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private String sortString(String s) {
        char[] sChars = s.toCharArray();
        Arrays.sort(sChars);
        return new String(sChars);
    }

    public static void main(String[] args) {
        AnagramGrouping a = new AnagramGrouping();
        System.out.println(a.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(a.groupAnagrams(new String[]{""}));
        System.out.println(a.groupAnagrams(new String[]{"a"}));
    }

}
