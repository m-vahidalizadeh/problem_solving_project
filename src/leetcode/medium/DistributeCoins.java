package leetcode.medium;

import leetcode.base.TreeNode;

/**
 * Distribute Coins in Binary Tree
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * <p>
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 * <p>
 * Return the number of moves required to make every node have exactly one coin.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * Example 2:
 * <p>
 * Input: [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
 * Example 3:
 * <p>
 * Input: [1,0,2]
 * Output: 2
 * Example 4:
 * <p>
 * Input: [1,0,0,null,3]
 * Output: 4
 * <p>
 * Note:
 * <p>
 * 1<= N <= 100
 * 0 <= node.val <= N
 */
public class DistributeCoins {

    static class Result {
        int moves = 0;
    }

    private int distributeCoinsRecursive(TreeNode root, Result result) {
        if (root == null)
            return 0;
        int l = distributeCoinsRecursive(root.left, result);
        int r = distributeCoinsRecursive(root.right, result);
        result.moves += Math.abs(l) + Math.abs(r);
        return root.val - 1 + l + r;
    }

    public int distributeCoins(TreeNode root) {
        Result result = new Result();
        distributeCoinsRecursive(root, result);
        return result.moves;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(0);
        TreeNode c = new TreeNode(0);
        a.left = b;
        a.right = c;
        DistributeCoins d = new DistributeCoins();
        System.out.println(d.distributeCoins(a));
    }

}
