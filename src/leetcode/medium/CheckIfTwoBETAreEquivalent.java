package leetcode.medium;

import java.util.*;

/**
 * 1612. Check If Two Expression Trees are Equivalent
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the '+' operator (i.e. addition).
 * <p>
 * You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression trees are equivalent. Otherwise, return false.
 * <p>
 * Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.
 * <p>
 * Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?
 * <p>
 * Example 1:
 * <p>
 * Input: root1 = [x], root2 = [x]
 * Output: true
 * Example 2:
 * <p>
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,b,c,a]
 * Output: true
 * Explaination: a + (b + c) == (b + c) + a
 * Example 3:
 * <p>
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,b,d,a]
 * Output: false
 * Explaination: a + (b + c) != (b + d) + a
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both trees are equal, odd and, in the range [1, 4999].
 * Node.val is '+' or a lower-case English letter.
 * It's guaranteed that the tree given is a valid binary expression tree.
 */
public class CheckIfTwoBETAreEquivalent {

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean checkEquivalence(Node root1, Node root2) {
        Map<Character, Integer> freq1 = getFreq(root1);
        Map<Character, Integer> freq2 = getFreq(root2);
        for (Map.Entry<Character, Integer> e : freq1.entrySet()) {
            if (!freq2.containsKey(e.getKey()) || !e.getValue().equals(freq2.get(e.getKey()))) return false;
        }
        return true;
    }

    private Map<Character, Integer> getFreq(Node root) {
        Map<Character, Integer> freq = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.poll();
            if (curr.val == '+') {
                stack.push(curr.left);
                stack.push(curr.right);
            } else freq.put(curr.val, freq.getOrDefault(curr.val, 0) + 1);
        }
        return freq;
    }


}
