import base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MSMoveCoins {

    public static void main(String[] args) {
        // Example 1
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(0);
        TreeNode left = new TreeNode(0);
        root.right = right;
        root.left = left;
        System.out.println(distributeCoins(root));
        // Example 2
        root = new TreeNode(0);
        right = new TreeNode(0);
        left = new TreeNode(3);
        root.right = right;
        root.left = left;
        System.out.println(distributeCoins(root));
    }

    public static int distributeCoins(TreeNode root) {
        int counter = 0;
        Map<TreeNode, TreeNode> parents = new HashMap();
        Queue<TreeNode> bfsNodes = new LinkedList();
        Queue<TreeNode> shouldBeBalanced = new LinkedList<>();
        bfsNodes.add(root);
        parents.put(root, null);
        while (!bfsNodes.isEmpty()) {
            TreeNode currentNode = bfsNodes.poll();
            if (currentNode.val > 1) {
                shouldBeBalanced.add(currentNode);
            }
            TreeNode currentNodeLeft = currentNode.left;
            if (currentNodeLeft != null) {
                bfsNodes.add(currentNodeLeft);
                parents.put(currentNodeLeft, currentNode);
            }
            TreeNode currentNodeRight = currentNode.right;
            if (currentNodeRight != null) {
                bfsNodes.add(currentNodeRight);
                parents.put(currentNodeRight, currentNode);
            }
        }
        while (!shouldBeBalanced.isEmpty()) {
            TreeNode currentNode = shouldBeBalanced.poll();
            if (currentNode.val > 1) {
                TreeNode parent = parents.get(currentNode);
                if (parent != null && parent.val == 0) {
                    counter++;
                    parent.val++;
                    currentNode.val--;
                } else {
                    TreeNode currentNodeLeft = currentNode.left;
                    if (currentNodeLeft != null && currentNodeLeft.val == 0) {
                        counter++;
                        currentNodeLeft.val++;
                        currentNode.val--;
                    } else {
                        TreeNode currentNodeRight = currentNode.right;
                        if (currentNodeRight != null && currentNodeRight.val == 0) {
                            counter++;
                            currentNodeRight.val++;
                            currentNode.val--;
                        } else if (parent != null) {
                            TreeNode parentRight = parent.right;
                            if (parent.val == 1
                                    && parentRight != null && parentRight.val == 0) {
                                counter += 2;
                                parentRight.val++;
                                currentNode.val--;
                            } else {
                                if (parent.val == 1) {
                                    TreeNode parentLeft = parent.left;
                                    if (parentLeft != null && parentLeft.val == 0) {
                                        counter += 2;
                                        parentLeft.val++;
                                        currentNode.val--;
                                    }
                                }
                            }
                        }
                    }
                }
                if (currentNode.val > 1) {
                    shouldBeBalanced.add(currentNode);
                }
            }
        }

        return counter;
    }

}
