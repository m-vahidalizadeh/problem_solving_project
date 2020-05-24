package leetcode.medium;

import leetcode.base.TreeNode;

public class FlipEquivalentBT {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Boolean areRootsEqual = areNodesEqual(root1, root2);
        if (areRootsEqual != null && !areRootsEqual) return false; // Roots are not equal
        else if (areRootsEqual == null) return true; // Both of the roots are null
        // None of the roots are null
        if (!doHaveSameChildren(root1, root2)) {
            TreeNode temp = root1.left;
            root1.left = root1.right;
            root1.right = temp;
        }
        return flipEquiv(root1.right, root2.right) && flipEquiv(root1.left, root2.left);
    }

    private Boolean areNodesEqual(TreeNode node1, TreeNode node2) {
        boolean node1IsNull = node1 == null;
        boolean node2IsNull = node2 == null;
        if (node1IsNull && node2IsNull) return null;
        else if (node1IsNull || node2IsNull) return false;
        return node1.val.equals(node2.val);
    }

    private boolean doHaveSameChildren(TreeNode node1, TreeNode node2) {
        Boolean sameRight = areNodesEqual(node1.right, node2.right);
        Boolean sameLeft = areNodesEqual(node1.left, node2.left);
        return (sameLeft == null || sameLeft) // Both children are null or have same val
                && (sameRight == null || sameRight);
    }

    public static void main(String[] args) {
        // Build tree 1
        TreeNode node1_1 = new TreeNode(1);
        TreeNode node1_2 = new TreeNode(2);
        TreeNode node1_3 = new TreeNode(3);
        TreeNode node1_4 = new TreeNode(4);
        TreeNode node1_5 = new TreeNode(5);
        TreeNode node1_6 = new TreeNode(6);
        TreeNode node1_7 = new TreeNode(7);
        TreeNode node1_8 = new TreeNode(8);
        node1_1.left = node1_2;
        node1_1.right = node1_3;
        node1_2.left = node1_4;
        node1_2.right = node1_5;
        node1_5.left = node1_7;
        node1_5.right = node1_8;
        node1_3.left = node1_6;
        // Build tree 2
        TreeNode node2_1 = new TreeNode(1);
        TreeNode node2_2 = new TreeNode(2);
        TreeNode node2_3 = new TreeNode(3);
        TreeNode node2_4 = new TreeNode(4);
        TreeNode node2_5 = new TreeNode(5);
        TreeNode node2_6 = new TreeNode(6);
        TreeNode node2_7 = new TreeNode(7);
        TreeNode node2_8 = new TreeNode(8);
        node2_1.left = node2_3;
        node2_1.right = node2_2;
        node2_2.left = node2_4;
        node2_2.right = node2_5;
        node2_5.left = node2_8;
        node2_5.right = node2_7;
        node2_3.right = node2_6;
        FlipEquivalentBT f = new FlipEquivalentBT();
        System.out.println(f.flipEquiv(node1_1, node2_1));
    }

}
