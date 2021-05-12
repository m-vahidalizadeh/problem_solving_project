package leetcode.hard;

/**
 * 1231. Divide Chocolate
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 * Constraints:
 *
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 */
public class DivideChocolate {

    public int maximizeSweetness(int[] sweetness, int K) {
        int l = 1;
        int r = 0;
        for (int s : sweetness) r += s;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            int cutsCount = getCutsCount(mid, sweetness);
            if (cutsCount >= K + 1) l = mid;
            else r = mid - 1;
        }
        return l;
    }

    private int getCutsCount(int sweetnessSum, int[] sweetness) {
        int cutsCount = 0;
        int currSweetness = 0;
        for (int s : sweetness) {
            currSweetness += s;
            if (currSweetness >= sweetnessSum) {
                cutsCount++;
                currSweetness = 0;
            }
        }
        return cutsCount;
    }

}
