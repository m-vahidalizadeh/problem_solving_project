package leetcode.hard;

/**
 * 1467. Probability of a Two Boxes Having The Same Number of Distinct Balls
 * Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.
 *
 * All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).
 *
 * Please note that the two boxes are considered different. For example, if we have two balls of colors a and b, and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).
 *
 * We want to calculate the probability that the two boxes have the same number of distinct balls.
 *
 * Example 1:
 *
 * Input: balls = [1,1]
 * Output: 1.00000
 * Explanation: Only 2 ways to divide the balls equally:
 * - A ball of color 1 to box 1 and a ball of color 2 to box 2
 * - A ball of color 2 to box 1 and a ball of color 1 to box 2
 * In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
 * Example 2:
 *
 * Input: balls = [2,1,1]
 * Output: 0.66667
 * Explanation: We have the set of balls [1, 1, 2, 3]
 * This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equale probability (i.e. 1/12):
 * [1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
 * After that we add the first two balls to the first box and the second two balls to the second box.
 * We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
 * Probability is 8/12 = 0.66667
 * Example 3:
 *
 * Input: balls = [1,2,1,2]
 * Output: 0.60000
 * Explanation: The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
 * Probability = 108 / 180 = 0.6
 * Example 4:
 *
 * Input: balls = [3,2,1]
 * Output: 0.30000
 * Explanation: The set of balls is [1, 1, 1, 2, 2, 3]. It is hard to display all the 60 possible random shuffles of this set but it is easy to check that 18 of them will have the same number of distinct colors in each box.
 * Probability = 18 / 60 = 0.3
 * Example 5:
 *
 * Input: balls = [6,6,6,6,6,6]
 * Output: 0.90327
 *
 * Constraints:
 *
 * 1 <= balls.length <= 8
 * 1 <= balls[i] <= 6
 * sum(balls) is even.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class DistributeBalls {

    double[] facts;
    int sum;
    int[] balls;

    public double getProbability(int[] balls) {
        this.balls = balls;
        for (int ball : balls) sum += ball;
        facts = new double[sum + 1];
        facts[0] = 1;
        for (int i = 1; i < facts.length; i++) facts[i] = facts[i - 1] * i;
        return rec(0, new int[balls.length], new int[balls.length], 0, 0) / permutation(balls);
    }

    private double rec(int index, int[] A, int[] B, int sumA, int sumB) {
        if (sumA > sum / 2 || sumB > sum / 2) return 0;
        if (index == balls.length) {
            int ca = 0;
            for (int a : A) {
                if (a > 0) ca++;
            }
            int cb = 0;
            for (int b : B) {
                if (b > 0) cb++;
            }
            return ca == cb ? permutation(A) * permutation(B) : 0;
        }
        double count = 0;
        for (int i = 0; i <= balls[index]; i++) {
            A[index] = i;
            B[index] = balls[index] - i;
            count += rec(index + 1, A, B, sumA + i, sumB + balls[index] - i);
        }
        return count;
    }

    private double permutation(int[] arr) {
        int count = 0;
        for (int a : arr) count += a;
        double res = facts[count];
        for (int a : arr) res /= facts[a];
        return res;
    }

}
