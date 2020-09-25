package leetcode.medium;

/**
 * Minimum Operations to Make Array Equal
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e. 0 <= i < n).
 * <p>
 * In one operation, you can select two indices x and y where 0 <= x, y < n and subtract 1 from arr[x] and add 1 to arr[y] (i.e. perform arr[x] -=1 and arr[y] += 1). The goal is to make all the elements of the array equal. It is guaranteed that all the elements of the array can be made equal using some operations.
 * <p>
 * Given an integer n, the length of the array. Return the minimum number of operations needed to make all the elements of arr equal.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: arr = [1, 3, 5]
 * First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
 * In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].
 * Example 2:
 * <p>
 * Input: n = 6
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^4
 */
public class MinOperations {

    public int minOperations(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) sum += Math.abs(n - (2 * i + 1));
        return sum / 2;
    }

    public static void main(String[] args) {
        MinOperations m = new MinOperations();
        System.out.println(m.minOperations(3));
        System.out.println(m.minOperations(6));
    }

}
