package leetcode.companies.google;

/**
 * Candy
 * There are N children standing in a line. Each child is assigned a rating value.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 * <p>
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;
        int nMinusOne = n - 1;
        int[] interesting = new int[n]; // 0=not interesting, 1=left interesting, 2=right interesting, 3 both interesting
        int[] candies = new int[n];
        int sum = n;
        for (int i = 0; i < n; i++) {
            int l = i == 0 ? Integer.MAX_VALUE : ratings[i - 1];
            int r = i == nMinusOne ? Integer.MAX_VALUE : ratings[i + 1];
            if (ratings[i] > l && ratings[i] > r) interesting[i] = 3;
            else if (ratings[i] > l) interesting[i] = 1;
            else if (ratings[i] > r) interesting[i] = 2;
            candies[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (interesting[i] == 1 || interesting[i] == 3) {
                int max = candies[i - 1] + 1;
                sum += max - candies[i];
                candies[i] = max;
            }
        }
        for (int i = nMinusOne; i >= 0; i--) {
            if (interesting[i] == 2) {
                int max = candies[i + 1] + 1;
                sum += max - candies[i];
                candies[i] = max;
            } else if (interesting[i] == 3) {
                int max = Math.max(candies[i + 1], candies[i - 1]) + 1;
                sum += max - candies[i];
                candies[i] = max;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Candy c = new Candy();
        System.out.println(c.candy(new int[]{1, 0, 2}));
        System.out.println(c.candy(new int[]{1, 2, 2}));
        System.out.println(c.candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(c.candy(new int[]{1, 3, 4, 5, 2}));
    }

}
