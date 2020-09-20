package leetcode.companies.google;

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
public class DeleteNodesInBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    Set<Integer> toDeleteSet;
    List<TreeNode> roots;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        toDeleteSet = new HashSet<>();
        roots = new ArrayList<>();
        for (int d : to_delete) toDeleteSet.add(d);
        deleteRec(root, null, true, false, false);
        return roots;
    }

    private void deleteRec(TreeNode node, TreeNode parent, boolean isParentDeleted, boolean isRight, boolean isLeft) {
        if (node == null) return;
        if (toDeleteSet.contains(node.val)) {
            if (parent != null) {
                if (isLeft) parent.left = null;
                if (isRight) parent.right = null;
            }
            deleteRec(node.left, null, true, false, true);
            deleteRec(node.right, null, true, true, false);
        } else {
            if (isParentDeleted) {
                roots.add(node);
            }
            deleteRec(node.left, node, false, false, true);
            deleteRec(node.right, node, false, true, false);
        }
    }

    public static void main(String[] args) {
        DeleteNodesInBT d = new DeleteNodesInBT();
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
        System.out.println(d.delNodes(node1, new int[]{3, 5}));
    }

}
