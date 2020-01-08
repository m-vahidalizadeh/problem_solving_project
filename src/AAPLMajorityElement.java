import java.util.HashMap;
import java.util.Map;

public class AAPLMajorityElement {

    /*
Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
     */

    public static void main(String[] args) {
        int[] example1 = new int[]{3, 2, 3};
        System.out.println(majorityElement(example1));
        int[] example2 = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(example2));
    }

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        int halfN = n / 2;
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int tempElement = nums[i];
            if (frequencies.containsKey(tempElement)) {
                int newFrequency = frequencies.get(tempElement) + 1;
                if (newFrequency > halfN) {
                    return tempElement;
                } else {
                    frequencies.put(tempElement, newFrequency);
                }
            } else {
                int newFrequency = 1;
                if (newFrequency > halfN) {
                    return tempElement;
                } else {
                    frequencies.put(tempElement, newFrequency);
                }
            }
        }
        return -1;
    }

}
