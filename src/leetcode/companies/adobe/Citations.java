package leetcode.companies.adobe;

import java.util.Arrays;

/**
 * H-Index
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * Example:
 * <p>
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class Citations {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] < h) {
                break;
            } else {
                h++;
            }
        }
        return --h;
    }

    public static void main(String[] args) {
        Citations c = new Citations();
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println(c.hIndex(citations1));
        int[] citations2 = {10, 9, 8, 9, 10};
        System.out.println(c.hIndex(citations2));
    }

}
