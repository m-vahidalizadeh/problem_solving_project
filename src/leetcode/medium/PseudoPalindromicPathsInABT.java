package leetcode.medium;

import leetcode.base.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Pseudo-Palindromic Paths in a Binary Tree
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 * <p>
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 * <p>
 * Input: root = [9]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 */
public class PseudoPalindromicPathsInABT {

    private class Path {
        Set<Integer> nodesInPath;

        private Path() {
            nodesInPath = new HashSet<>();
        }

        private Path(Path path) {
            nodesInPath = new HashSet<>(path.nodesInPath);
        }

        private void addNode(TreeNode node) {
            Integer nodeVal = node.val;
            if (nodesInPath.contains(nodeVal)) {
                nodesInPath.remove(nodeVal);
            } else {
                nodesInPath.add(nodeVal);
            }
        }

        private boolean isPseudoPalindromic() {
            return nodesInPath.size() <= 1;
        }
    }

    private int countPseudoPalindromicPaths(TreeNode node, Path path) {
        path.addNode(node);
        TreeNode l = node.left;
        TreeNode r = node.right;
        boolean isLNull = l == null;
        boolean isRNull = r == null;
        if (isLNull && isRNull) return path.isPseudoPalindromic() ? 1 : 0;
        else if (isLNull) return countPseudoPalindromicPaths(r, new Path(path));
        else if (isRNull) return countPseudoPalindromicPaths(l, new Path(path));
        return countPseudoPalindromicPaths(l, new Path(path)) + countPseudoPalindromicPaths(r, new Path(path));
    }

    /**
     * Gets the number of pseudo palindromic paths in a binary tree.
     *
     * @param root The root of the binary tree
     * @return The number of pseudo palindromic paths in a binary tree
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) return 0;
        return countPseudoPalindromicPaths(root, new Path());
    }

    public static void main(String[] args) {
        PseudoPalindromicPathsInABT p = new PseudoPalindromicPathsInABT();
        /*
            2
            /\
           3  1
          /\   \
         3  1   1
         */
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3_1 = new TreeNode(3);
        TreeNode node1_1 = new TreeNode(1);
        TreeNode node1_2 = new TreeNode(1);
        node2.left = node3;
        node2.right = node1;
        node3.left = node3_1;
        node3.right = node1_1;
        node1.right = node1_2;
        System.out.println(p.pseudoPalindromicPaths(node2));
    }

}
