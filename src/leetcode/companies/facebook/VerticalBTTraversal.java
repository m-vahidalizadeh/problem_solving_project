package leetcode.companies.facebook;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 *
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 * Example 4:
 *
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class VerticalBTTraversal {

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

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Map<Integer, List<Integer>> columns = new HashMap<>();
        Deque<Integer> qColumn = new ArrayDeque<>();
        Deque<TreeNode> qNode = new ArrayDeque<>();
        qColumn.add(0);
        qNode.add(root);
        int minCol = 0;
        int maxCol = 0;
        while (!qNode.isEmpty()) {
            TreeNode currNode = qNode.poll();
            int currCol = qColumn.poll();
            columns.computeIfAbsent(currCol, a -> new ArrayList<>());
            columns.get(currCol).add(currNode.val);
            if (currNode.left != null) {
                minCol = Math.min(minCol, currCol - 1);
                qColumn.add(currCol - 1);
                qNode.add(currNode.left);
            }
            if (currNode.right != null) {
                maxCol = Math.max(maxCol, currCol + 1);
                qColumn.add(currCol + 1);
                qNode.add(currNode.right);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = minCol; i <= maxCol; i++) {
            result.add(columns.get(i));
        }
        return result;
    }

}
