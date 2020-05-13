package leetcode.medium;

import leetcode.base.TreeNode;

/**
 * Two Sum BSTs
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 * <p>
 * Example 1:
 * <p>
 * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 * Output: true
 * Explanation: 2 and 3 sum up to 5.
 * Example 2:
 * <p>
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * Each tree has at most 5000 nodes.
 * -10^9 <= target, node.val <= 10^9
 */
public class TwoSumBSTs {

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        int sum = root1.val + root2.val;
        if (sum == target) return true;
        if (sum > target) return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        else return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
    }

    public static void main(String[] args) {
        TreeNode t1_1 = new TreeNode(1);
        TreeNode t1_2 = new TreeNode(2);
        TreeNode t1_4 = new TreeNode(4);
        t1_2.left = t1_1;
        t1_2.right = t1_4;
        TreeNode t2_0 = new TreeNode(0);
        TreeNode t2_1 = new TreeNode(1);
        TreeNode t2_3 = new TreeNode(3);
        t2_1.left = t2_0;
        t2_1.right = t2_3;
        TwoSumBSTs t = new TwoSumBSTs();
        System.out.println(t.twoSumBSTs(t1_2, t2_1, 5));
    }

}
