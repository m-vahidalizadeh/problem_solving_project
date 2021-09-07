package leetcode.companies.google;

import java.util.*;

/**
 * 894. All Possible Full Binary Trees
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Example 1:
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Example 2:
 *
 * Input: n = 3
 * Output: [[0,0,0]]
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class AllPossibleFBT {

    Map<Integer, List<TreeNode>> trees = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (trees.containsKey(n)) return trees.get(n);
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> leftSubs;
            if (trees.containsKey(i)) leftSubs = trees.get(i);
            else {
                leftSubs = allPossibleFBT(i);
                trees.put(i, leftSubs);
            }
            List<TreeNode> rightSubs; // i to left 1 root and the rest right
            if (trees.containsKey(n - i - 1)) rightSubs = trees.get(n - i - 1);
            else {
                rightSubs = allPossibleFBT(n - i - 1);
                trees.put(n - i - 1, rightSubs);
            }
            for (TreeNode left : leftSubs) {
                for (TreeNode right : rightSubs) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        trees.put(n, res);
        return res;
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
