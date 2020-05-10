package leetcode.medium;

import leetcode.base.TreeNode;

/**
 * Lowest Common Ancestor of Deepest Leaves
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 * <p>
 * Recall that:
 * <p>
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 * Example 2:
 * <p>
 * Input: root = [1,2,3,4]
 * Output: [4]
 * Example 3:
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 * <p>
 * Constraints:
 * <p>
 * The given tree will have between 1 and 1000 nodes.
 * Each node of the tree will have a distinct value between 1 and 1000.
 */
public class LCAOfDeepestLeaves {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        LCAOfDeepestLeaves l = new LCAOfDeepestLeaves();
        System.out.println(l.lcaDeepestLeaves(node1).val);
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null)
            return root;
        int h = getBTHeight(root);
        return findDeepest(root, 1, h);
    }

    private TreeNode findDeepest(TreeNode node, int currentDepth, int height) {
        if (node == null)
            return node;
        if (currentDepth == height)
            return node;
        int childDepth = currentDepth + 1;
        TreeNode lDeepest = findDeepest(node.left, childDepth, height);
        TreeNode rDeepest = findDeepest(node.right, childDepth, height);
        boolean isLNonNull = lDeepest != null;
        boolean isRNonNull = rDeepest != null;
        if (isLNonNull && isRNonNull) {
            return node;
        } else if (isLNonNull) {
            return lDeepest;
        } else if (isRNonNull) {
            return rDeepest;
        }
        return null;
    }

    private int getBTHeight(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(getBTHeight(node.left), getBTHeight(node.right));
    }

}
