package leetcode.companies.amazon;

import java.util.*;

/**
 * Group Anagrams
 * Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * <p>
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        for (String s : strs) {
            String sortedS = sortString(s);
            if (anagrams.containsKey(sortedS)) {
                List<String> existingList = anagrams.get(sortedS);
                existingList.add(s);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(s);
                anagrams.put(sortedS, newList);
            }
        }
        return new ArrayList<>(anagrams.values());
    }

    private String sortString(String input) {
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);
        return new String(inputChars);
    }

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        printListOfLists(g.groupAnagrams(strs));
    }

    private static void printListOfLists(List<List<String>> input) {
        for (List<String> l : input) {
            for (String s : l) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

}
