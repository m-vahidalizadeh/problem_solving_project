package microsoft;

import base.TreeNode;

import java.util.*;

public class MSLowestCommonAncestor {

    /*
    Example 1:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    Output: 6
    Explanation: The LCA of nodes 2 and 8 is 6.

    Example 2:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    Output: 2
    Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node4 = new TreeNode(4);
        root.right = node8;
        root.left = node2;
        node8.right = new TreeNode(9);
        node8.left = new TreeNode(7);
        node2.right = node4;
        node2.left = new TreeNode(0);
        TreeNode node5 = new TreeNode(5);
        node4.right = node5;
        TreeNode node3 = new TreeNode(3);
        node4.left = node3;
        System.out.println(lowestCommonAncestor(root, node2, node8).val);
        System.out.println(lowestCommonAncestor(root, node2, node4).val);
        System.out.println(lowestCommonAncestor(root, node3, node5).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Find the parents of all the nodes.
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        Queue<TreeNode> theNodes = new LinkedList<>();
        Map<Integer, TreeNode> allNodes = new HashMap<>();
        theNodes.add(root);
        while (!theNodes.isEmpty()) {
            TreeNode currentNode = theNodes.poll();
            TreeNode currentNodeLC = currentNode.left;
            TreeNode currentNodeRC = currentNode.right;
            allNodes.put(currentNode.val, currentNode);
            if (currentNodeLC != null) {
                parentsMap.put(currentNodeLC, currentNode);
                theNodes.add(currentNodeLC);
            }
            if (currentNodeRC != null) {
                parentsMap.put(currentNodeRC, currentNode);
                theNodes.add(currentNodeRC);
            }
        }
        // Find the least common parent
        // Find parents of p and sort
        List<Integer> pParentsList = new ArrayList<>();
        TreeNode currentNode = p;
        while (currentNode != root) {
            pParentsList.add(currentNode.val);
            currentNode = parentsMap.get(currentNode);
        }
        pParentsList.add(root.val);
        // Find parents of q and sort
        List<Integer> qParentsList = new ArrayList<>();
        currentNode = q;
        while (currentNode != root) {
            qParentsList.add(currentNode.val);
            currentNode = parentsMap.get(currentNode);
        }
        qParentsList.add(root.val);
        // Find the least common parent
        int leastCommonParentKey = root.val;
        for (int pParent : pParentsList) {
            if (qParentsList.contains(pParent)) {
                leastCommonParentKey = pParent;
                break;
            }
        }
        return allNodes.get(leastCommonParentKey);
    }

}
