import base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class FBCompleteTree {

    /*
    public class base.TreeNode {

        Integer val;
        base.TreeNode left;
        base.TreeNode right;

        base.TreeNode(Integer x) {
            val = x;
        }

        base.TreeNode(base.TreeNode treeNode) {
            if (treeNode != null) {
                this.val = treeNode.val;
                this.left = new base.TreeNode(treeNode.left);
                this.right = new base.TreeNode(treeNode.right);
            }
        }

    }
     */

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        FBCompleteTree completeTree = new FBCompleteTree();
        System.out.println(completeTree.isCompleteTree(node1));
    }

    public boolean isCompleteTree(TreeNode root) {
        int nodeCount = countNodes(root);
        // The index of the root is always zero.
        return isComplete(root, nodeCount, 0);
    }

    /**
     * Tells us if a binary tree is complete or not.
     *
     * @param node      The current node
     * @param nodeCount The node counts of the tree
     * @param index     The index of the node
     * @return If a binary tree is complete or not
     */
    private boolean isComplete(TreeNode node, int nodeCount, int index) {
        // Null is a complete binary tree
        if (node == null)
            return true;
        // In a complete binary tree, index of all the nodes should be less than nodes count.
        if (index >= nodeCount)
            return false;
        /*
        The index of the left child is 2*i+1 and the right child is 2*i+2 in a binary tree.
         */
        return isComplete(node.left, nodeCount, 2 * index + 1)
                &&
                isComplete(node.right, nodeCount, 2 * index + 2);
    }

    /**
     * Counts the number of nodes.
     *
     * @param root The root of the tree
     * @return The number of nodes in the tree
     */
    private int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int counter = 0;
        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            counter++;
            TreeNode l = currentNode.left;
            if (l != null) {
                q.add(l);
            }
            TreeNode r = currentNode.right;
            if (r != null) {
                q.add(r);
            }
        }
        return counter;
    }

}
