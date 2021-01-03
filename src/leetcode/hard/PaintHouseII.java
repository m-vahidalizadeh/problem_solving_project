package leetcode.hard;

import java.util.Arrays;

/**
 * 65. Paint House II
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Example:
 *
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 *              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 */
public class PaintHouseII {

    public class House{
        int j;
        int cost;
        public House(int j,int cost){
            this.j=j;
            this.cost=cost;
        }
    }

    public int minCostII(int[][] costs) {
        int n=costs.length;
        if(n==0) return 0;
        int m=costs[0].length;
        House[] houses=new House[m];
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++) houses[j]=new House(j,costs[i-1][j]);
            Arrays.sort(houses,(a,b)->a.cost-b.cost);
            for(int j=0;j<m;j++){
                int k=0;
                while(houses[k].j==j) k++;
                costs[i][j]+=houses[k].cost;
            }
        }
        int min=Integer.MAX_VALUE;
        for(int j=0;j<m;j++) min=Math.min(min,costs[n-1][j]);
        return min;
    }

}
