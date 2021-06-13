package leetcode.companies.google;

/**
 * 222. Count Complete Tree Nodes
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */
public class CountCompleteTreeNodes {

    public static class TreeNode {
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

    int first;
    int last;
    int d;

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        d = getDepth(root);
        first = 1 << d;
        last = (first << 1) - 1;
        int s = first;
        int e = last;
        while (s < e) {
            int mid = s + ((e - s) / 2) + 1;
            if (!doesExist(mid, root)) e = mid - 1;
            else s = mid;
        }
        return e;
    }

    private boolean doesExist(int target, TreeNode root) {
        if (target > last) return false;
        int s = first;
        int e = last;
        for (int i = 0; i < d; i++) {
            int mid = s + ((e - s) / 2);
            if (target <= mid) {
                e = mid;
                root = root.left;
            } else {
                s = mid + 1;
                root = root.right;
            }
        }
        return root != null;
    }

    private int getDepth(TreeNode root) {
        int d = 0;
        while (root.left != null) {
            d++;
            root = root.left;
        }
        return d;
    }

}
