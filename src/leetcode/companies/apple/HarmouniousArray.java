package leetcode.companies.apple;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Harmonious Subsequence
 * We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * <p>
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * <p>
 * <p>
 * Note: The length of the input array will not exceed 20,000.
 */
public class HarmouniousArray {

    public int findLHS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < n; i++) updateFrequency(nums[i], frequencies);
        int maxLength = 0;
        for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
            int eValue = e.getValue();
            if (frequencies.containsKey(e.getKey() - 1)) {
                int tempTotal = eValue + frequencies.get(e.getKey() - 1);
                if (tempTotal > maxLength) maxLength = tempTotal;
            }
            if (frequencies.containsKey(e.getKey() + 1)) {
                int tempTotal = eValue + frequencies.get(e.getKey() + 1);
                if (tempTotal > maxLength) maxLength = tempTotal;
            }
        }
        return maxLength;
    }

    private void updateFrequency(int input, Map<Integer, Integer> map) {
        if (map.containsKey(input)) {
            map.put(input, map.get(input) + 1);
        } else {
            map.put(input, 1);
        }
    }

    public static void main(String[] args) {
        HarmouniousArray h = new HarmouniousArray();
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(h.findLHS(nums));
    }

}
