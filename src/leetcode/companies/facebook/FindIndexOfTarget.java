package leetcode.companies.facebook;

import java.util.*;

/**
 * Random Pick Index
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * <p>
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * <p>
 * Example:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * <p>
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */
public class FindIndexOfTarget {

    Map<Integer, List<Integer>> indexesMap;
    Random random;

    public FindIndexOfTarget(int[] nums) {
        indexesMap = new HashMap<>();
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int numsI = nums[i];
            if (indexesMap.containsKey(numsI)) {
                List<Integer> existingList = indexesMap.get(numsI);
                existingList.add(i);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                indexesMap.put(numsI, newList);
            }
        }
    }

    public int pick(int target) {
        List<Integer> tempList = indexesMap.get(target);
        return tempList.get(random.nextInt(tempList.size()));
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        FindIndexOfTarget f = new FindIndexOfTarget(nums);
        System.out.println(f.pick(3));
        System.out.println(f.pick(3));
        System.out.println(f.pick(3));
        System.out.println(f.pick(1));
    }

}
