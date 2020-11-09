package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildBT {

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

    int pIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        pIndex = postorder.length - 1;
        return rec(inorder, postorder);
    }

    private TreeNode rec(int[] inorder, int[] postorder) {
        if (pIndex < 0) return null;
        int rootVal = postorder[pIndex--];
        int rootIndex = findIndex(inorder, rootVal);
        TreeNode root = new TreeNode(rootVal);
        int[] r = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        if (r.length > 0) root.right = rec(r, postorder);
        int[] l = Arrays.copyOfRange(inorder, 0, rootIndex);
        if (l.length > 0) root.left = rec(l, postorder);
        return root;
    }

    private int findIndex(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        BuildBT b = new BuildBT();
        TreeNode t = b.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println();
    }

}
