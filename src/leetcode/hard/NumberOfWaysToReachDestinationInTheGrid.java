package leetcode.hard;

/**
 * 2912. Number of Ways to Reach Destination in the Grid
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given two integers n and m which represent the size of a 1-indexed grid. You are also given an integer k, a 1-indexed integer array source and a 1-indexed integer array dest, where source and dest are in the form [x, y] representing a cell on the given grid.
 *
 * You can move through the grid in the following way:
 *
 * You can go from cell [x1, y1] to cell [x2, y2] if either x1 == x2 or y1 == y2.
 * Note that you can't move to the cell you are already in e.g. x1 == x2 and y1 == y2.
 * Return the number of ways you can reach dest from source by moving through the grid exactly k times.
 *
 * Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, m = 2, k = 2, source = [1,1], dest = [2,2]
 * Output: 2
 * Explanation: There are 2 possible sequences of reaching [2,2] from [1,1]:
 * - [1,1] -> [1,2] -> [2,2]
 * - [1,1] -> [2,1] -> [2,2]
 * Example 2:
 *
 * Input: n = 3, m = 4, k = 3, source = [1,2], dest = [2,3]
 * Output: 9
 * Explanation: There are 9 possible sequences of reaching [2,3] from [1,2]:
 * - [1,2] -> [1,1] -> [1,3] -> [2,3]
 * - [1,2] -> [1,1] -> [2,1] -> [2,3]
 * - [1,2] -> [1,3] -> [3,3] -> [2,3]
 * - [1,2] -> [1,4] -> [1,3] -> [2,3]
 * - [1,2] -> [1,4] -> [2,4] -> [2,3]
 * - [1,2] -> [2,2] -> [2,1] -> [2,3]
 * - [1,2] -> [2,2] -> [2,4] -> [2,3]
 * - [1,2] -> [3,2] -> [2,2] -> [2,3]
 * - [1,2] -> [3,2] -> [3,3] -> [2,3]
 *
 * Constraints:
 *
 * 2 <= n, m <= 10^9
 * 1 <= k <= 10^5
 * source.length == dest.length == 2
 * 1 <= source[1], dest[1] <= n
 * 1 <= source[2], dest[2] <= m
 */
public class NumberOfWaysToReachDestinationInTheGrid {

    public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
        long o=0,i1=0,i2=0,f=0,mod=(long)(1e9+7),res=0;
        if(source[0]==dest[0]&&source[1]==dest[1]) f++;
        else if(source[0]==dest[0]) i1++;
        else if(source[1]==dest[1]) i2++;
        else o++;
        if(k==1) return (int)(i1+i2);
        for(int i=0;i<k-2;i++){
            long oo = o*(m+n-4)%mod, oi1 = o, oi2 = o,i1o = i1*(n-1)%mod, i1i1 = i1*(m-2)%mod, i1f = i1,i2o = i2*(m-1)%mod, i2i2 = i2*(n-2)%mod, i2f = i2,fi1 = f*(m-1)%mod, fi2 = f*(n-1)%mod;
            o = ((oo + i1o)%mod + i2o)%mod;
            i1 = ((oi1 + i1i1)%mod + fi1)%mod;
            i2 = ((oi2 + i2i2)%mod + fi2)%mod;
            f = (i1f + i2f)%mod;
        }
        res = (res + o*2)%mod;
        res = (res + i1*(m-2)%mod)%mod;
        res = (res + i2*(n-2)%mod)%mod;
        res = (res + f*(m+n-2)%mod)%mod;
        return (int) (res);
    }

}
