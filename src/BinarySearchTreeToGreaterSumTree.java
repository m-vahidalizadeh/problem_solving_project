import base.TreeNode;

/**
 * Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
 * <p>
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Reference: LeetCode
 */
public class BinarySearchTreeToGreaterSumTree {

    class Sum {
        public int sum = 0;
    }

    public static void main(String[] args) {
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
        // Example 1
//        base.TreeNode node0 = new base.TreeNode(0);
//        base.TreeNode node1 = new base.TreeNode(1);
//        base.TreeNode node2 = new base.TreeNode(2);
//        base.TreeNode node3 = new base.TreeNode(3);
//        base.TreeNode node4 = new base.TreeNode(4);
//        base.TreeNode node5 = new base.TreeNode(5);
//        base.TreeNode node6 = new base.TreeNode(6);
//        base.TreeNode node7 = new base.TreeNode(7);
//        base.TreeNode node8 = new base.TreeNode(8);
//        node4.left = node1;
//        node4.right = node6;
//        node1.left = node0;
//        node1.right = node2;
//        node2.right = node3;
//        node6.right = node7;
//        node6.left = node5;
//        node7.right = node8;
        // Example 2
//        base.TreeNode node1=new base.TreeNode(1);
//        base.TreeNode node2=new base.TreeNode(2);
//        base.TreeNode node3=new base.TreeNode(3);
//        base.TreeNode node4=new base.TreeNode(4);
//        node3.left=node2;
//        node3.right=node4;
//        node2.left=node1;
        // Example 3
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node4.left = node1;
        node4.right = node6;
        node1.left = node0;
        node1.right = node2;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        node7.right = node8;

        BinarySearchTreeToGreaterSumTree bstToGST = new BinarySearchTreeToGreaterSumTree();
        // the input of this method should be the root of the tree
        TreeNode result = bstToGST.bstToGst(node4);
        System.out.println();
    }

    /**
     * Builds a GST.
     *
     * @param root The root of the binary search tree
     * @return The GST
     */
    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            Sum sum = new Sum();
            buildGST(root, sum);
        }
        return root;
    }

    /**
     * A recursive method to build the Greater Sum Tree.
     *
     * @param currentNode The current node
     * @param sum         The current summation
     */
    private void buildGST(TreeNode currentNode, Sum sum) {
        if (currentNode == null)
            return;
        // Call build GST on the right child
        TreeNode r = currentNode.right;
        buildGST(r, sum);
        // Update the current sum with the value of the current node
        sum.sum = sum.sum + currentNode.val;
        // Update the value of the current node with the new sum
        currentNode.val = sum.sum;
        // Call build GST on the left child with the updated sum
        TreeNode l = currentNode.left;
        buildGST(l, sum);
    }

}
