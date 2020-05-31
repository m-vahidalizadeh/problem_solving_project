package leetcode.companies.bloomberg;

import leetcode.base.TreeNode;

/**
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */
public class UniValueSubtree {

    class Count {
        int count;
    }

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        Count c = new Count();
        countUnivalSubtreesRec(root,c);
        return c.count;
    }

    public boolean countUnivalSubtreesRec(TreeNode node, Count c) {
        if (node == null) return true;
        boolean left = countUnivalSubtreesRec(node.left, c);
        boolean right = countUnivalSubtreesRec(node.right, c);
        if (!left || !right) return false;
        else if (node.left != null && node.left.val != node.val) return false;
        else if (node.right != null && node.right.val != node.val) return false;
        c.count++;
        return true;
    }

    public static void main(String[] args) {
        TreeNode nodeA = new TreeNode(5);
        TreeNode nodeB = new TreeNode(1);
        TreeNode nodeC = new TreeNode(5);
        TreeNode nodeD = new TreeNode(5);
        TreeNode nodeE = new TreeNode(5);
        TreeNode nodeF = new TreeNode(5);
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.right = nodeF;
        UniValueSubtree u = new UniValueSubtree();
        System.out.println(u.countUnivalSubtrees(nodeA));
    }

}
