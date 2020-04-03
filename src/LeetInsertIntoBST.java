import base.TreeNode;

public class LeetInsertIntoBST {

    boolean found = false;

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        node4.left = node2;
        node4.right = node7;
        node2.left = node1;
        node2.right = node3;
        LeetInsertIntoBST leetInsertIntoBST = new LeetInsertIntoBST();
        TreeNode result = leetInsertIntoBST.insertIntoBST(node4, 5);
        System.out.println();
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        //Add the node
        boolean added = false;
        TreeNode currentNode = root;
        while (!added) {
            TreeNode left = currentNode.left;
            TreeNode right = currentNode.right;
            int currentVal = currentNode.val;
            if (val < currentVal) {
                if (left == null) {
                    currentNode.left = new TreeNode(val);
                    added = true;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (right == null) {
                    currentNode.right = new TreeNode(val);
                    added = true;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
        return root;
    }

}
