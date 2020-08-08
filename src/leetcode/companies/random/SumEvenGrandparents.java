package leetcode.companies.random;

/**
 * Sum of Nodes with Even-Valued Grandparent
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 * <p>
 * If there are no nodes with an even-valued grandparent, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class SumEvenGrandparents {

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

    public int sumEvenGrandparent(TreeNode root) {
        return getSumRec(root, false, false);
    }

    private int getSumRec(TreeNode node, boolean evenParent, boolean evenGrandParent) {
        if (node == null) return 0;
        boolean isEven = node.val % 2 == 0;
        if (evenGrandParent)
            return node.val + getSumRec(node.left, isEven, evenParent) + getSumRec(node.right, isEven, evenParent);
        return getSumRec(node.left, isEven, evenParent) + getSumRec(node.right, isEven, evenParent);
    }

}
