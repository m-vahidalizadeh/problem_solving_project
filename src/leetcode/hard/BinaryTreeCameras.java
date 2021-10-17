package leetcode.hard;

/**
 * 968. Binary Tree Cameras
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * Example 1:
 *
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
 */
public class BinaryTreeCameras {

    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[1], ans[2]);
    }

    private int[] dfs(TreeNode node) {
        /*
        0) Current node is not monitored
        1) Current node is monitored, but no camera
        2) Current node has camera
         */
        if (node == null) return new int[]{0, 0, Integer.MAX_VALUE / 2};
        int[] ans = new int[3];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        ans[0] = left[1] + right[1]; // Children must not have any camera but they should be monitored.
        int rMin12 = Math.min(right[1], right[2]);
        int lMin12 = Math.min(left[1], left[2]);
        ans[1] = Math.min(left[2] + rMin12, right[2] + lMin12); // Either left child has camera or right.
        ans[2] = 1 + Math.min(left[0], lMin12) + Math.min(right[0], rMin12); // No camera is required on any of its children. They should just be covered.
        return ans;
    }

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

}
