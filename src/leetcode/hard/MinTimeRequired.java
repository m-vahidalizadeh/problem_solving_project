package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1723. Find Minimum Time to Finish All Jobs
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 *
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 *
 * Return the minimum possible maximum working time of any assignment.
 *
 * Example 1:
 *
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * Example 2:
 *
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 *
 * Constraints:
 *
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 107
 */
public class MinTimeRequired {

    int ans;
    int[] workers;
    int[] jobs;
    int n;
    int k;

    public int minimumTimeRequired(int[] jobs, int k) {
        ans = Integer.MAX_VALUE;
        n = jobs.length;
        this.workers = new int[n];
        this.jobs = jobs;
        this.k = k;
        backTrack(0, 0);
        return ans;
    }

    private void backTrack(int i, int currMaxTime) {
        if (i == n) {
            ans = Math.min(ans, currMaxTime);
            return;
        }
        if (currMaxTime >= ans) return;
        Set<Integer> visited = new HashSet<>();
        for (int j = 0; j < k; j++) {
            if (!visited.contains(workers[j])) {
                workers[j] += jobs[i];
                backTrack(i + 1, Math.max(currMaxTime, workers[j]));
                workers[j] -= jobs[i];
            }
            visited.add(workers[j]);
        }
    }

}
