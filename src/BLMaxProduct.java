import java.util.Collections;
import java.util.PriorityQueue;

public class BLMaxProduct {

    public static void main(String[] args) {
        /*
Example 1:
Input: [1,2,3]
Output: 6

Example 2:
Input: [1,2,3,4]
Output: 24
         */
        int[] example1 = new int[]{1, 2, 3};
        System.out.println(maximumProduct(example1));
        int[] example2 = new int[]{1, 2, 3, 4};
        System.out.println(maximumProduct(example2));
        int[] example3 = new int[]{-4, -3, -2, -1, 60};
        System.out.println(maximumProduct(example3));
    }

    public static int maximumProduct(int[] nums) {
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        boolean hasNegativeNumbers = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                hasNegativeNumbers = true;
            }
            maxPq.add(nums[i]);
            minPq.add(nums[i]);
        }
        int result = 1;
        int firstMax = maxPq.peek();
        for (int i = 0; i < 3; i++) {
            result *= maxPq.poll();
        }
        if (hasNegativeNumbers) {
            int tempResult = firstMax;
            for (int i = 0; i < 2; i++) {
                tempResult *= minPq.poll();
            }
            if (tempResult > result) {
                return tempResult;
            }
        }
        return result;
    }

}
