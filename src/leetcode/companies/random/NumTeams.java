package leetcode.companies.random;

/**
 * 1395. Count Number of Teams
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 * Example 1:
 *
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 * Example 2:
 *
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 * Example 3:
 *
 * Input: rating = [1,2,3,4]
 * Output: 4
 *
 * Constraints:
 *
 * n == rating.length
 * 1 <= n <= 200
 * 1 <= rating[i] <= 10^5
 */
public class NumTeams {

    public int numTeams(int[] rating) {
        int count = 0;
        int n = rating.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n) {
            j = i + 1;
            while (j < n) {
                if (rating[i] >= rating[j]) {
                    j++;
                    continue;
                }
                k = j + 1;
                while (k < n) {
                    if (rating[j] >= rating[k]) {
                        k++;
                        continue;
                    }
                    count++;
                    k++;
                }
                j++;
            }
            i++;
        }
        i = n - 1;
        j = n - 1;
        k = n - 1;
        while (i >= 0) {
            j = i - 1;
            while (j >= 0) {
                if (rating[j] <= rating[i]) {
                    j--;
                    continue;
                }
                k = j - 1;
                while (k >= 0) {
                    if (rating[k] <= rating[j]) {
                        k--;
                        continue;
                    }
                    count++;
                    k--;
                }
                j--;
            }
            i--;
        }
        return count;
    }

    public static void main(String[] args) {
        NumTeams n = new NumTeams();
        System.out.println(n.numTeams(new int[]{2, 5, 3, 4, 1}));
        System.out.println(n.numTeams(new int[]{2, 1, 3}));
        System.out.println(n.numTeams(new int[]{1, 2, 3, 4}));
    }

}
