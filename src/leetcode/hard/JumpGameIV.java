package leetcode.hard;

import java.util.*;

/**
 * 1345. Jump Game IV
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 *
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * Example 3:
 *
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * Example 4:
 *
 * Input: arr = [6,1,9]
 * Output: 2
 * Example 5:
 *
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 104
 * -108 <= arr[i] <= 108
 */
public class JumpGameIV {

    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int n = arr.length;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(arr[i], x -> new ArrayList<>());
            indexMap.get(arr[i]).add(i);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n - 1) return steps;
                visited.add(curr);
                if (curr + 1 < n && !visited.contains(curr + 1)) q.add(curr + 1);
                if (curr - 1 >= 0 && !visited.contains(curr - 1)) q.add(curr - 1);
                if (indexMap.containsKey(arr[curr])) {
                    for (int nei : indexMap.get(arr[curr])) {
                        if (curr != nei && !visited.contains(nei)) q.add(nei);
                    }
                    indexMap.remove(arr[curr]);
                }
            }
            steps++;
        }
        return -1;
    }

}
