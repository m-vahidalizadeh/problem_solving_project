package leetcode.hard;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 2792. Count Nodes That Are Great Enough
 * Solved
 * Hard
 *
 * Topics
 *
 * Hint
 * You are given a root to a binary tree and an integer k. A node of this tree is called great enough if the followings hold:
 *
 * Its subtree has at least k nodes.
 * Its value is greater than the value of at least k nodes in its subtree.
 * Return the number of nodes in this tree that are great enough.
 *
 * The node u is in the subtree of the node v, if u == v or v is an ancestor of u.
 *
 * Example 1:
 *
 * Input: root = [7,6,5,4,3,2,1], k = 2
 * Output: 3
 * Explanation: Number the nodes from 1 to 7.
 * The values in the subtree of node 1: {1,2,3,4,5,6,7}. Since node.val == 7, there are 6 nodes having a smaller value than its value. So it's great enough.
 * The values in the subtree of node 2: {3,4,6}. Since node.val == 6, there are 2 nodes having a smaller value than its value. So it's great enough.
 * The values in the subtree of node 3: {1,2,5}. Since node.val == 5, there are 2 nodes having a smaller value than its value. So it's great enough.
 * It can be shown that other nodes are not great enough.
 * See the picture below for a better understanding.
 *
 * Example 2:
 *
 * Input: root = [1,2,3], k = 1
 * Output: 0
 * Explanation: Number the nodes from 1 to 3.
 * The values in the subtree of node 1: {1,2,3}. Since node.val == 1, there are no nodes having a smaller value than its value. So it's not great enough.
 * The values in the subtree of node 2: {2}. Since node.val == 2, there are no nodes having a smaller value than its value. So it's not great enough.
 * The values in the subtree of node 3: {3}. Since node.val == 3, there are no nodes having a smaller value than its value. So it's not great enough.
 * See the picture below for a better understanding.
 *
 * Example 3:
 *
 * Input: root = [3,2,2], k = 2
 * Output: 1
 * Explanation: Number the nodes from 1 to 3.
 * The values in the subtree of node 1: {2,2,3}. Since node.val == 3, there are 2 nodes having a smaller value than its value. So it's great enough.
 * The values in the subtree of node 2: {2}. Since node.val == 2, there are no nodes having a smaller value than its value. So it's not great enough.
 * The values in the subtree of node 3: {2}. Since node.val == 2, there are no nodes having a smaller value than its value. So it's not great enough.
 * See the picture below for a better understanding.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * 1 <= Node.val <= 10^4
 * 1 <= k <= 10
 */
public class CountNodesThatAreGreatEnough {

    public int countGreatEnoughNodes(TreeNode root, int k) {
        return dfs(root,k).great; // you just need to return the number of greats of the root
    }

    private Ans dfs(TreeNode node,int k){
        if(node==null) return new Ans(); // if null node, return an empty ans
        Ans left=dfs(node.left,k); // explore left branch
        Ans right=dfs(node.right,k); // explore right branch
        Ans ans=new Ans(); // Make a new ans
        ans.maxHeap=left.maxHeap;
        ans.maxHeap.addAll(right.maxHeap); // merge left and right max heaps
        ans.count=left.count+ right.count; // sub count is left count + right count
        ans.great=left.great+ right.great; // number of greats are left greats + right greats
        while(ans.maxHeap.size()>k) ans.maxHeap.poll(); // we only need k least values in the sub
        if(ans.count>=k&&node.val>ans.maxHeap.peek()) ans.great++; // check if the current node is great
        ans.maxHeap.add(node.val); // add the current node val to the max heap
        while(ans.maxHeap.size()>k) ans.maxHeap.poll(); // make sure you send at most k values in the max heap
        ans.count++; // count the current node
        return ans; // return the ans
    }

    class Ans {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // we need a max heap to be able to quickly remove the max if sub size is greater than k
        int count=0,great=0; // count and great are zero for a null node
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
