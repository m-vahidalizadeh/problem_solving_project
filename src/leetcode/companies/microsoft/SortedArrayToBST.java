package leetcode.companies.microsoft;

import leetcode.base.TreeNode;

/**
 * Convert Sorted Array to Binary Search Tree
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int l, int h) {
        if (l > h) return null;
        if (l == h) return new TreeNode(nums[l]);
        int mid = (h + l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, l, mid - 1);
        root.right = buildTree(nums, mid + 1, h);
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST s = new SortedArrayToBST();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = s.sortedArrayToBST(nums);
        System.out.println();
    }

}
