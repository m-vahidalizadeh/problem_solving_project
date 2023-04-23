package leetcode.companies.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 528. Random Pick with Weight
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * Example 1:
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 * Example 2:
 *
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 *
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 * Constraints:
 *
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 104 times.
 */
public class PickIndex {

    Random random;
    List<int[]> list;
    int end;

    public PickIndex(int[] w) {
        random = new Random();
        list = new ArrayList<>();
        int s = 0;
        int e = -1;
        for (int i = 0; i < w.length; i++) {
            e = s + w[i] - 1;
            list.add(new int[]{i, s, e});
            s = e + 1;
        }
        end = e;
        list.sort((a, b) -> a[2] - b[2]);
    }

    public int pickIndex() {
        int rand = random.nextInt(end + 1);
        int s = 0, e = list.size() - 1;
        while (s < e) {
            int mid = (s + e) / 2;
            if (rand >= list.get(mid)[1] && rand <= list.get(mid)[2]) return list.get(mid)[0];
            else if (rand > list.get(mid)[2]) s = mid + 1;
            else e = mid - 1;
        }
        return list.get(e)[0];
    }

}
