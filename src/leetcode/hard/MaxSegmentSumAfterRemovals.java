package leetcode.hard;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 2382. Maximum Segment Sum After Removals
 * You are given two 0-indexed integer arrays nums and removeQueries, both of length n. For the ith query, the element in nums at the index removeQueries[i] is removed, splitting nums into different segments.
 *
 * A segment is a contiguous sequence of positive integers in nums. A segment sum is the sum of every element in a segment.
 *
 * Return an integer array answer, of length n, where answer[i] is the maximum segment sum after applying the ith removal.
 *
 * Note: The same index will not be removed more than once.
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
 * Output: [14,7,2,2,0]
 * Explanation: Using 0 to indicate a removed element, the answer is as follows:
 * Query 1: Remove the 0th element, nums becomes [0,2,5,6,1] and the maximum segment sum is 14 for segment [2,5,6,1].
 * Query 2: Remove the 3rd element, nums becomes [0,2,5,0,1] and the maximum segment sum is 7 for segment [2,5].
 * Query 3: Remove the 2nd element, nums becomes [0,2,0,0,1] and the maximum segment sum is 2 for segment [2].
 * Query 4: Remove the 4th element, nums becomes [0,2,0,0,0] and the maximum segment sum is 2 for segment [2].
 * Query 5: Remove the 1st element, nums becomes [0,0,0,0,0] and the maximum segment sum is 0, since there are no segments.
 * Finally, we return [14,7,2,2,0].
 * Example 2:
 *
 * Input: nums = [3,2,11,1], removeQueries = [3,2,1,0]
 * Output: [16,5,3,0]
 * Explanation: Using 0 to indicate a removed element, the answer is as follows:
 * Query 1: Remove the 3rd element, nums becomes [3,2,11,0] and the maximum segment sum is 16 for segment [3,2,11].
 * Query 2: Remove the 2nd element, nums becomes [3,2,0,0] and the maximum segment sum is 5 for segment [3,2].
 * Query 3: Remove the 1st element, nums becomes [3,0,0,0] and the maximum segment sum is 3 for segment [3].
 * Query 4: Remove the 0th element, nums becomes [0,0,0,0] and the maximum segment sum is 0, since there are no segments.
 * Finally, we return [16,5,3,0].
 *
 * Constraints:
 *
 * n == nums.length == removeQueries.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= 109
 * 0 <= removeQueries[i] < n
 * All the values of removeQueries are unique.
 */
public class MaxSegmentSumAfterRemovals {

    public long[] maximumSegmentSum(int[] nums, int[] quer) {
        int n = nums.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[2], a[2]));
        TreeSet<Integer> set = new TreeSet<>();
        long[] arr = new long[n];
        long[] ans = new long[n];
        set.add(-1);
        set.add(n);
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            if (i != 0) arr[i] += arr[i - 1];
        }
        pq.add(new long[]{0, n - 1, arr[n - 1]});
        for (int i = 0; i < n; i++) {
            int num = quer[i];
            set.add(quer[i]);
            int a = set.lower(num), b = set.higher(num);
            if ((a + 1) < num) {
                pq.add(new long[]{(long) a + 1, (long) num - 1, arr[num - 1] - (a == -1 ? 0 : arr[a])});
            }
            if ((num + 1) < b) {
                pq.add(new long[]{(long) num + 1, (long) b - 1, arr[b - 1] - arr[num]});
            }
            while (!pq.isEmpty()) {
                long[] ab = pq.peek();
                int s = (int) ab[0], e = (int) ab[1];
                if (set.higher(s - 1) > e) {
                    ans[i] = pq.peek()[2];
                    break;
                } else pq.remove();
            }
        }
        return ans;
    }

}
