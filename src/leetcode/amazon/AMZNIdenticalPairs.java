package leetcode.amazon;

import java.util.HashMap;
import java.util.Map;

public class AMZNIdenticalPairs {

    public static void main(String[] args) {
        /*
Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
         */
        int[] nums = new int[]{3, 3, 7, 7, 10, 11, 11};
        AMZNIdenticalPairs amznIdenticalPairs = new AMZNIdenticalPairs();
        System.out.println(amznIdenticalPairs.singleNonDuplicate(nums));
    }

    public int singleNonDuplicate(int[] nums) {
        int maxVisited = nums[0];
        Map<Integer, Integer> frequency = new HashMap<>();
        int n = nums[nums.length - 1];
        for (int i = 0; i <= nums.length - 1; i++) {
            int currentElement = nums[i];
            if (frequency.containsKey(currentElement)) {
                int currentFreq = frequency.get(currentElement);
                frequency.put(currentElement, currentFreq + 1);
            } else {
                frequency.put(currentElement, 1);
            }
            if (maxVisited < currentElement) {
                if (frequency.get(maxVisited) < 2)
                    return maxVisited;
                maxVisited = currentElement;
            }
        }
        for (Map.Entry<Integer, Integer> e : frequency.entrySet()) {
            int key = e.getKey();
            int value = e.getValue();
            if (value < 2)
                return key;
        }
        return -1;
    }

}
