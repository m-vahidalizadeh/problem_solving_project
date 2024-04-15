package leetcode.companies.microsoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1156. Swap For Longest Repeated Character Substring
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given a string text. You can swap two of the characters in the text.
 *
 * Return the length of the longest substring with repeated characters.
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa" with length 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa" with length 6.
 * Example 3:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa" with length is 5.
 *
 * Constraints:
 *
 * 1 <= text.length <= 2 * 10^4
 * text consist of lowercase English characters only.
 */
public class MapRepOpt1 {

    public int maxRepOpt1(String text) {
        Map<Character, List<Integer>> occMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) occMap.computeIfAbsent(text.charAt(i), x -> new ArrayList<>()).add(i);
        return occMap.values().stream().map(this::getMaxSize).mapToInt(i -> i).max().getAsInt();
    }

    private int getMaxSize(List<Integer> indexes) {
        int left = 0, right = 0, max = 0, prevSize = -1, prevRight = -1, prevLeft = -1;
        while (left < indexes.size()) { // End condition is when left equals the size of the indexes
            while (right + 1 < indexes.size() && indexes.get(right + 1) - indexes.get(right) == 1) right++; // find the current consecutive group
            if (prevSize >= 0) { // There is a prev group or not
                int groupsDist = indexes.get(left) - indexes.get(prevRight); // The distance between the end of prev group and the start of the current group
                if (groupsDist == 2) {
                    if (prevLeft > 0 || right < indexes.size() - 1) max = Math.max(max, indexes.get(right) - indexes.get(left) + 1 + prevSize + 1); // You can fill the gap between two groups with a char outside of them from another group
                    else max = Math.max(max, indexes.get(right) - indexes.get(left) + 1 + prevSize); // You have to use a char from one of the groups to fill the gap
                } else if (groupsDist > 2) max = Math.max(max, Math.max(indexes.get(right) - indexes.get(left) + 2, prevSize + 1)); // In this case we can borrow one of the chars from one of the groups for the other one
            } else max = Math.max(max, indexes.get(right) - indexes.get(left) + 1); // ordinary case, just the size of the current group involved
            prevLeft = left; // save current left
            prevRight = right; // save current right
            prevSize = indexes.get(right) - indexes.get(left) + 1; // save the size of the current group
            left = right + 1; // update the left to start a new group
            right = left; // place right at left to start
        }
        return max;
    }

}
