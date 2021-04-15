package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 527. Word Abbreviation
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 *
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 * Example:
 *
 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * Note:
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 */
public class WordAbbreviation {

    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict == null) return null;
        Map<String, Integer> abbreviations = new HashMap<>();
        List<String> result = new ArrayList<>();
        int index = 1;
        int n = dict.size();
        for (int i = 0; i < n; i++) {
            result.add(getAbbreviation(dict.get(i), index));
            abbreviations.put(result.get(i), abbreviations.getOrDefault(result.get(i), 0) + 1);
        }
        while (true) {
            boolean noDuplicate = true;
            index++;
            for (int i = 0; i < n; i++) {
                if (abbreviations.get(result.get(i)) > 1) {
                    result.set(i, getAbbreviation(dict.get(i), index));
                    abbreviations.put(result.get(i), abbreviations.getOrDefault(result.get(i), 0) + 1);
                    noDuplicate = false;
                }
            }
            if (noDuplicate) break;
        }
        return result;
    }

    private String getAbbreviation(String str, int index) {
        if (index >= str.length() - 2) return str;
        return str.substring(0, index) + (str.length() - index - 1) + str.substring(str.length() - 1);
    }

}
