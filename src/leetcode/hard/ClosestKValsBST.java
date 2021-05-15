package leetcode.hard;

import java.util.*;

/**
 * 272. Closest Binary Search Tree Value II
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
 *
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Example 1:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 * Example 2:
 *
 * Input: root = [1], target = 0.000000, k = 1
 * Output: [1]
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104.
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 *
 * Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class ClosestKValsBST {

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

    double target;
    Stack<Integer> smallerS;
    Queue<Integer> biggerQ;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null) return Collections.emptyList();
        smallerS = new Stack<>(); // Kepps
        biggerQ = new LinkedList<>();
        this.target = target;
        dfs(root);
        List<Integer> res = new ArrayList<>();
        while (k > 0) {
            if (!smallerS.isEmpty() && !biggerQ.isEmpty()) {
                if (Math.abs(smallerS.peek() - target) <= Math.abs(biggerQ.peek() - target)) res.add(smallerS.pop());
                else res.add(biggerQ.poll());
            } else if (!smallerS.isEmpty()) res.add(smallerS.pop());
            else if (!biggerQ.isEmpty()) res.add(biggerQ.poll());
            k--; // Next round
        }
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (root.val > target) biggerQ.add(root.val);
        else smallerS.push(root.val);
        dfs(root.right);
    }

}
