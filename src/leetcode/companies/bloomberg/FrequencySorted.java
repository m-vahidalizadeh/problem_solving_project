package leetcode.companies.bloomberg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Sort Characters By Frequency
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * "tree"
 * <p>
 * Output:
 * "eert"
 * <p>
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * <p>
 * Input:
 * "cccaaa"
 * <p>
 * Output:
 * "cccaaa"
 * <p>
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 * <p>
 * Input:
 * "Aabb"
 * <p>
 * Output:
 * "bbAa"
 * <p>
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class FrequencySorted {

    public String frequencySort(String s) {
        if (s.isBlank()) return s;
        int maxFreq = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int newFreq = freqMap.getOrDefault(c, 0) + 1;
            maxFreq = Math.max(newFreq, maxFreq);
            freqMap.put(c, newFreq);
        }
        Map<Integer, Set<Character>> freqCharMap = new HashMap<>();
        for (Map.Entry<Character, Integer> e : freqMap.entrySet()) {
            if (freqCharMap.containsKey(e.getValue())) {
                freqCharMap.get(e.getValue()).add(e.getKey());
            } else {
                Set<Character> newSet = new HashSet<>();
                newSet.add(e.getKey());
                freqCharMap.put(e.getValue(), newSet);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = maxFreq; i >= 1; i--) {
            if (freqCharMap.containsKey(i)) {
                Set<Character> charsWithThisFreq = freqCharMap.get(i);
                for (char c : charsWithThisFreq) {
                    for (int j = 0; j < i; j++) sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FrequencySorted f = new FrequencySorted();
        System.out.println(f.frequencySort("tree"));
        System.out.println(f.frequencySort("cccaaa"));
        System.out.println(f.frequencySort("Aabb"));
    }

}
