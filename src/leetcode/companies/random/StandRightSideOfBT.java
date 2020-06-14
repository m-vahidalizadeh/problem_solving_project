package leetcode.companies.random;

import leetcode.base.TreeNode;

import java.util.*;

/**
 *Binary Tree Right Side View
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class StandRightSideOfBT {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();
        int max = 0;
        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        nodeLevelMap.put(root, 0);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currNode = q.poll();
            int currLevel = nodeLevelMap.get(currNode);
            rightMap.put(currLevel, currNode.val);
            TreeNode l = currNode.left;
            int childLevel = currLevel + 1;
            if (l != null) {
                q.add(l);
                nodeLevelMap.put(l, childLevel);
                if (max < childLevel) max = childLevel;
            }
            TreeNode r = currNode.right;
            if (r != null) {
                q.add(r);
                nodeLevelMap.put(r, childLevel);
                if (max < childLevel) max = childLevel;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= max; i++) result.add(rightMap.get(i));
        return result;
    }

    public static void main(String[] args) {
        StandRightSideOfBT s = new StandRightSideOfBT();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node2.right = node5;
        node1.right = node3;
        node3.right = node4;
        printNodes(s.rightSideView(node1));
    }

    private static void printNodes(List<Integer> nodes) {
        for (int n : nodes) System.out.print(n + " ");
    }

}
