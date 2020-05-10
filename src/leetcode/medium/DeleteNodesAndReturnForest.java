package leetcode.medium;

import leetcode.base.TreeNode;

import java.util.*;

/**
 * Delete Nodes And Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        DeleteNodesAndReturnForest d = new DeleteNodesAndReturnForest();
        int[] to_delete = {3, 5};
        List<TreeNode> result = d.delNodes(node1, to_delete);
        for (TreeNode n : result)
            System.out.format("%d ", n.val);
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null)
            return Collections.emptyList();
        List<TreeNode> roots = new ArrayList<>();
        deleteNodesRecursive(null, root, to_delete, roots, null);
        return roots;
    }

    private void deleteNodesRecursive(TreeNode parent, TreeNode node, int[] to_delete, List<TreeNode> roots, Boolean isLeft) {
        if (node == null)
            return;
        boolean shouldBeDeleted = shouldBeDeleted(node.val, to_delete);
        if (shouldBeDeleted) {
            deleteNodesRecursive(null, node.left, to_delete, roots, true);
            deleteNodesRecursive(null, node.right, to_delete, roots, false);
            if (parent != null && isLeft != null) {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else {
            if (parent == null)
                roots.add(node);
            deleteNodesRecursive(node, node.left, to_delete, roots, true);
            deleteNodesRecursive(node, node.right, to_delete, roots, false);
        }
    }

    private boolean shouldBeDeleted(int val, int[] to_delete) {
        for (int e : to_delete) {
            if (val == e)
                return true;
        }
        return false;
    }

}
