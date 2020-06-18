package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Clone Binary Tree With Random Pointer
 * A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.
 * <p>
 * Return a deep copy of the tree.
 * <p>
 * The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
 * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy. NodeCopy class is just a clone of Node class with the same attributes and constructors.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [[1,null],null,[4,3],[7,0]]
 * Output: [[1,null],null,[4,3],[7,0]]
 * Explanation: The original binary tree is [1,null,4,7].
 * The random pointer of node one is null, so it is represented as [1, null].
 * The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the array representing the tree.
 * The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the array representing the tree.
 * Example 2:
 * <p>
 * Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Explanation: The random pointer of a node can be the node itself.
 * Example 3:
 * <p>
 * Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Example 4:
 * <p>
 * Input: root = []
 * Output: []
 * Example 5:
 * <p>
 * Input: root = [[1,null],null,[2,null],null,[1,null]]
 * Output: [[1,null],null,[2,null],null,[1,null]]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * Each node's value is between [1, 10^6].
 */
public class CloneBTWithRandomPT {

    Map<Node, NodeCopy> generatedNodesMap = new HashMap<>();

    public NodeCopy copyRandomBinaryTree(Node root) {
        NodeCopy resultRoot = copyTree(root);
        copyRandoms(resultRoot, root);
        return resultRoot;
    }

    private void copyRandoms(NodeCopy resultRoot, Node root) {
        if (root == null) return;
        resultRoot.random = generatedNodesMap.get(root.random);
        copyRandoms(resultRoot.left, root.left);
        copyRandoms(resultRoot.right, root.right);
    }

    public NodeCopy copyTree(Node root) {
        if (root == null) return null;
        NodeCopy clone = new NodeCopy(root.val);
        clone.left = copyRandomBinaryTree(root.left);
        clone.right = copyRandomBinaryTree(root.right);
        generatedNodesMap.put(root, clone);
        return clone;
    }

    public static void main(String[] args) {
        CloneBTWithRandomPT c = new CloneBTWithRandomPT();
        Node node1 = new Node(1);
        Node node4 = new Node(4);
        Node node7 = new Node(7);
        node1.right = node4;
        node4.left = node7;
        node4.random = node7;
        node7.random = node1;
        NodeCopy resultRoot = c.copyRandomBinaryTree(node1);
    }

    public static class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public static class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {
        }

        NodeCopy(int val) {
            this.val = val;
        }

        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

}
