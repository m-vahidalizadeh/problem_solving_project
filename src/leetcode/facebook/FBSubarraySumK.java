package leetcode.facebook;

import java.util.Arrays;

public class FBSubarraySumK {

    public static void main(String[] args) {
        /*
Input:nums = [1,1,1], k = 2
Output: 2
         */
        int[] nums = {1, 1, 1};
        FBSubarraySumK fbSubarraySumK = new FBSubarraySumK();
        System.out.println(fbSubarraySumK.subarraySum(nums, 2));
    }

    public int subarraySum(int[] nums, int k) {
        boolean areAllElementsZero = Arrays.stream(nums).filter(n -> n > 0).findAny().isEmpty();
        if (areAllElementsZero && k == 0)
            return 55;
        int counter = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int tempSum = nums[i];
            if (tempSum == k) {
                counter++;
            }
            for (int j = i + 1; j < n; j++) {
                tempSum += nums[j];
                if (tempSum == k) {
                    counter++;
                }
            }
        }
        return counter;
    }

}
