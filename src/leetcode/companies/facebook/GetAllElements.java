package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 1305. All Elements in Two Binary Search Trees
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [0, 5000].
 * -105 <= Node.val <= 105
 */
public class GetAllElements {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> t1 = dfs(root1), t2 = dfs(root2), res = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i1 < t1.size() && i2 < t2.size()) {
            if (t1.get(i1) < t2.get(i2)) res.add(t1.get(i1++));
            else res.add(t2.get(i2++));
        }
        while (i1 < t1.size()) res.add(t1.get(i1++));
        while (i2 < t2.size()) res.add(t2.get(i2++));
        return res;
    }

    private ArrayList<Integer> dfs(TreeNode node) {
        if (node == null) return new ArrayList<>();
        ArrayList<Integer> left = dfs(node.left);
        ArrayList<Integer> right = dfs(node.right);
        ArrayList<Integer> res = left;
        res.add(node.val);
        res.addAll(right);
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
