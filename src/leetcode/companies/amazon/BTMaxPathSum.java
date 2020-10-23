package leetcode.companies.amazon;

/**
 * 124. Binary Tree Maximum Path Sum
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any node sequence from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Example 2:
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class BTMaxPathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int l = dfs(node.left);
        int r = dfs(node.right);
        int localMax = Math.max(node.val, Math.max(node.val + l, node.val + r));
        max = Math.max(max, Math.max(localMax, node.val + l + r));
        return localMax;
    }

}
