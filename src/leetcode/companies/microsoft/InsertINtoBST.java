package leetcode.companies.microsoft;

import javafx.util.Pair;

/**
 * 701. Insert into a Binary Search Tree
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * Example 1:
 *
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *
 * Example 2:
 *
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 * Example 3:
 *
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [0, 10^4].
 * -108 <= Node.val <= 10^8
 * All the values Node.val are unique.
 * -108 <= val <= 10^8
 * It's guaranteed that val does not exist in the original BST.
 */
public class InsertINtoBST {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        Pair<TreeNode,TreeNode> node=new Pair<>(root,null); // [node,parent]
        while(node.getKey()!=null) node=new Pair<>(node.getKey().val<val?node.getKey().right:node.getKey().left,node.getKey());
        if(node.getValue().val<val) node.getValue().right=new TreeNode(val);
        else node.getValue().left=new TreeNode(val);
        return root;
    }

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

}
