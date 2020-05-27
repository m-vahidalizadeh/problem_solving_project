package leetcode.medium;

import java.util.Stack;

/**
 * Minimum Cost Tree From Leaf Values
 * Given an array arr of positive integers, consider all binary trees such that:
 * <p>
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 * <p>
 * 24            24
 * /  \          /  \
 * 12   4        6    8
 * /  \               / \
 * 6    2             2   4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 * Accepted
 * 27,522
 * Submissions
 * 42,064
 */
public class MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        // Add this for the first element in the array. We just need to add the first element into the stack.
        stack.push(Integer.MAX_VALUE);
        for (int i = 0; i < arr.length; i++) {
            int arrI = arr[i];
            // The cost of removing each element is x*Min(prev(x),next(x))
            while (stack.peek() <= arrI) result += stack.pop() * Math.min(stack.peek(), arrI);
            stack.add(arrI);
        }
        int stackSize = stack.size();
        // Since the last element is extra. We put Integer.MAX_VALUE in the beginning.
        for (int i = 0; i < stackSize - 2; i++) result += stack.pop() * stack.peek();
        return result;
    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues m = new MinimumCostTreeFromLeafValues();
        int[] arr = {6, 2, 4};
        System.out.println(m.mctFromLeafValues(arr));
    }

}
