package amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AMZNConnectRightBT {

    public static void main(String[] args) {
        /*
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point
to its next right node, just like in Figure B. The serialized output is in level order as connected by the next
pointers, with '#' signifying the end of each level.
         */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Node root = connect(node1);
        System.out.println();
    }

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Map<Node, Integer> nodeLevels = new HashMap<>();
        nodeLevels.put(root, 0);
        Queue<Node> nodesQueue = new LinkedList<>();
        nodesQueue.add(root);
        while (!nodesQueue.isEmpty()) {
            Node currentNode = nodesQueue.poll();
            int currentNodeLevel = nodeLevels.get(currentNode);
            if (nodesQueue.isEmpty()) {
                currentNode.next = null;
            } else {
                Node nextNode = nodesQueue.peek();
                int nextNodeLevel = nodeLevels.get(nextNode);
                if (nextNodeLevel == currentNodeLevel) {
                    currentNode.next = nextNode;
                }
            }
            Node leftChild = currentNode.left;
            Node rightChild = currentNode.right;
            if (leftChild != null && rightChild != null) {
                int childNodeLevels = currentNodeLevel + 1;
                nodeLevels.put(leftChild, childNodeLevels);
                nodeLevels.put(rightChild, childNodeLevels);
                nodesQueue.add(leftChild);
                nodesQueue.add(rightChild);
            }
        }
        return root;
    }

}
