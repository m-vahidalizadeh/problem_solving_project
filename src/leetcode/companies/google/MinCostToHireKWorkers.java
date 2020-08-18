package leetcode.companies.google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Minimum Cost to Hire K Workers
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * <p>
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 * <p>
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 * <p>
 * Example 1:
 * <p>
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 * <p>
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 * <p>
 * Note:
 * <p>
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 */
public class MinCostToHireKWorkers {

    static class Worker implements Comparable<Worker> {
        int quality;
        int wage;
        double ratio;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            ratio = (double) wage / quality;
        }

        @Override
        public int compareTo(Worker o) {
            return Double.compare(this.ratio, o.ratio);
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++) workers[i] = new Worker(quality[i], wage[i]);
        Arrays.sort(workers);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int sumQ = 0;
        double result = Double.MAX_VALUE;
        for (Worker w : workers) {
            pq.add(w.quality);
            sumQ += w.quality;
            if (pq.size() > K) {
                sumQ -= pq.poll();
            }
            if (pq.size() == K) result = Math.min(result, sumQ * w.ratio);
        }
        return result;
    }

}
