import base.TreeNode;

import java.util.PriorityQueue;

public class UberFindSmallestInBT {

    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        /*
Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
         */

        TreeNode node3_1 = new TreeNode(3);
        TreeNode node1_1 = new TreeNode(1);
        TreeNode node4_1 = new TreeNode(4);
        TreeNode node2_1 = new TreeNode(2);
        node3_1.right = node4_1;
        node3_1.left = node1_1;
        node1_1.right = node2_1;
        System.out.println(kthSmallest(node3_1, 1));

        pq = new PriorityQueue<>();
        TreeNode node5_2 = new TreeNode(5);
        TreeNode node3_2 = new TreeNode(3);
        TreeNode node6_2 = new TreeNode(6);
        TreeNode node2_2 = new TreeNode(2);
        TreeNode node4_2 = new TreeNode(4);
        TreeNode node1_2 = new TreeNode(1);
        node5_2.left = node3_2;
        node5_2.right = node6_2;
        node3_2.left = node2_2;
        node3_2.right = node4_2;
        node2_2.left = node1_2;
        System.out.println(kthSmallest(node5_2, 3));
    }

    public static int kthSmallest(TreeNode root, int k) {
        // Fill the pq- dfs
        dfs(root);
        // Return the kth smallest
        for (int i = 1; i <= k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }

    public static void dfs(TreeNode root) {
        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;
        pq.add(root.val);
        if (leftChild != null) {
            dfs(leftChild);
        }
        if (rightChild != null) {
            dfs(rightChild);
        }
    }

}
