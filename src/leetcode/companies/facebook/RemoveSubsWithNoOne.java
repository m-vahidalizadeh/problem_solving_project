package leetcode.companies.facebook;

/**
 * 814. Binary Tree Pruning
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 *
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 *
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 *
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 *
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 * Example 3:
 * Input: [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 *
 * Note:
 *
 * The binary tree will have at most 200 nodes.
 * The value of each node will only be 0 or 1.
 */
public class RemoveSubsWithNoOne {

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

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        return !dfs(root) ? null : root; // Check if the root is 0 and there is no 1 in the sub-trees
    }

    private boolean dfs(TreeNode node) {
        if (node == null) return false;
        boolean left = dfs(node.left); // check if there is any 1 in the left sub
        if (!left) node.left = null;
        boolean right = dfs(node.right); // check if there is any 1 in the right sub
        if (!right) node.right = null;
        return left || right || node.val == 1; // Return if there exists any 1 in this sub (including its root)
    }

}
