package leetcode.medium;

import java.util.*;

/**
 * Binary Search Tree Iterator II
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * <p>
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
 * int prev() Moves the pointer to the left, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * <p>
 * You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.
 * <p>
 * Follow up: Could you solve the problem without precalculating the values of the tree?
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev", "prev"]
 * [[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
 * Output
 * [null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]
 * <p>
 * Explanation
 * // The underlined element is where the pointer currently is.
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is   [3, 7, 9, 15, 20]
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.hasNext(); // return true
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 9
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 20
 * bSTIterator.hasNext(); // return false
 * bSTIterator.hasPrev(); // return true
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 9
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 106
 * At most 105 calls will be made to hasNext, next, hasPrev, and prev.
 */
public class BSTIterator {

    TreeNode[] nodes;
    int n;
    int nMinusOne;
    int curr;

    public BSTIterator(TreeNode root) {
        List<TreeNode> dfsTraversal = dfs(root);
        n = dfsTraversal.size();
        nMinusOne = n - 1;
        nodes = new TreeNode[n];
        int i = 0;
        for (TreeNode node : dfsTraversal) nodes[i++] = node;
        curr = -1;
    }

    public boolean hasNext() {
        return curr < nMinusOne;
    }

    public int next() {
        return nodes[++curr].val;
    }

    public boolean hasPrev() {
        return curr > 0;
    }

    public int prev() {
        return nodes[--curr].val;
    }

    private List<TreeNode> dfs(TreeNode node) {
        if (node == null) return Collections.emptyList();
        List<TreeNode> result = new ArrayList<>(dfs(node.left));
        result.add(node);
        result.addAll(dfs(node.right));
        return result;
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node15 = new TreeNode(15);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        node7.left = node3;
        node7.right = node15;
        node15.left = node9;
        node15.right = node20;
        BSTIterator b = new BSTIterator(node7);
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.prev());
        System.out.println(b.next());
        System.out.println(b.hasNext());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.hasNext());
        System.out.println(b.hasPrev());
        System.out.println(b.prev());
        System.out.println(b.prev());
    }

    public static class TreeNode {
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
