package leetcode.hard;

import java.util.TreeSet;

/**
 * 3013. Divide an Array Into Subarrays With Minimum Cost II
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
 *
 * The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
 *
 * You need to divide nums into k disjoint contiguous
 * subarrays
 * , such that the difference between the starting index of the second subarray and the starting index of the kth subarray should be less than or equal to dist. In other words, if you divide nums into the subarrays nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
 *
 * Return the minimum possible sum of the cost of these subarrays.
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
 * Output: 5
 * Explanation: The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2]. This choice is valid because ik-1 - i1 is 5 - 2 = 3 which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
 * It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.
 * Example 2:
 *
 * Input: nums = [10,1,2,2,2,1], k = 4, dist = 3
 * Output: 15
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10], [1], [2], and [2,2,1]. This choice is valid because ik-1 - i1 is 3 - 1 = 2 which is less than dist. The total cost is nums[0] + nums[1] + nums[2] + nums[3] which is 10 + 1 + 2 + 2 = 15.
 * The division [10], [1], [2,2,2], and [1] is not valid, because the difference between ik-1 and i1 is 5 - 1 = 4, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 4 subarrays at a cost lower than 15.
 * Example 3:
 *
 * Input: nums = [10,8,18,9], k = 3, dist = 1
 * Output: 36
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10], [8], and [18,9]. This choice is valid because ik-1 - i1 is 2 - 1 = 1 which is equal to dist.The total cost is nums[0] + nums[1] + nums[2] which is 10 + 8 + 18 = 36.
 * The division [10], [8,18], and [9] is not valid, because the difference between ik-1 and i1 is 3 - 1 = 2, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 36.
 *
 * Constraints:
 *
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 3 <= k <= n
 * k - 2 <= dist <= n - 2
 */
public class DivideAnArrayIntoSubarraysWithMinCostII {

    public long minimumCost(int[] nums, int k, int dist) {
        long result = Long.MAX_VALUE, windowSum = 0L;
        TreeSet<Integer> leastElements = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]), nonLeastElements = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        for (int right = 1; right <= dist + 1; right++) { // window size=dist+1-1=dist - first element of second sub to first element of kth sub
            leastElements.add(right);
            windowSum += nums[right];
        }
        while (leastElements.size() > k - 1) { // make sure only k-1 least elements are in leastElements. nums[0] is always included (ignore nums[0]) hunt next k-1 least
            int i = leastElements.pollLast();
            windowSum -= nums[i];
            nonLeastElements.add(i);
        }
        result = Math.min(result, windowSum); // compare current window sum to min
        for (int left = 1, right = left + dist + 1; left + dist + 1 < nums.length; left++, right++) { // Advance left pointer and remove left element from leastElements till you hit the end of array
            nonLeastElements.add(right); // add right to the non least elements
            if (leastElements.contains(left)) { // left element is in least elements
                int nonLeastElementsMinIndex = nonLeastElements.pollFirst(); // find non least elements min index
                leastElements.remove(left);
                leastElements.add(nonLeastElementsMinIndex); // add non least elements min index to least elements
                windowSum = windowSum - nums[left] + nums[nonLeastElementsMinIndex];
            } else { // left element is in non least elements
                nonLeastElements.remove(left);
                int nonLeastElementsMinIndex = nonLeastElements.first(), leastElementsMaxIndex = leastElements.last();
                if (nums[nonLeastElementsMinIndex] < nums[leastElementsMaxIndex]) { // make sure the least k-1 elements are in least elements
                    windowSum = windowSum - nums[leastElementsMaxIndex] + nums[nonLeastElementsMinIndex]; // adjust window sum (curr least k-1 elements)
                    leastElements.remove(leastElementsMaxIndex); // remove least elements max
                    leastElements.add(nonLeastElementsMinIndex); // add non least elements min to least elements
                    nonLeastElements.remove(nonLeastElementsMinIndex); // remove non least elements min
                    nonLeastElements.add(leastElementsMaxIndex); // add least elements max to non least elements
                }
            }
            result = Math.min(result, windowSum); // compare current window sum to min
        }
        return result + nums[0]; // nums[0] is always included for the first window. Result is the k-1 least elements after breaking into k subs
    }

}
