package leetcode.companies.google;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 857. Minimum Cost to Hire K Workers
 * There are n workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly k workers to form a paid group.  When hiring a group of k workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 * Note:
 *
 * 1 <= k <= n <= 10000, where n = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10-5 of the correct answer will be considered correct.
 */
public class HireWorkers {

    public class Worker {
        int wage;
        int quality;
        double ratio;

        public Worker(int wage, int quality) {
            this.wage = wage;
            this.quality = quality;
            this.ratio = (double) wage / quality;
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++) workers[i] = new Worker(wage[i], quality[i]);
        Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int sumQ = 0;
        double res = Double.MAX_VALUE;
        for (Worker worker : workers) {
            pq.add(worker.quality);
            sumQ += worker.quality;
            if (pq.size() > K) sumQ -= pq.poll();
            if (pq.size() == K) {
                res = Math.min(res, sumQ * worker.ratio);
            }
        }
        return res;
    }

}
