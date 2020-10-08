package leetcode.medium;

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
        return getSum(root1) == getSum(root2);
    }

    private int getSum(Node node) {
        if (node == null) return 0;
        Node l = node.left;
        Node r = node.right;
        if (l == null && r == null) return node.val;
        return getSum(l) + getSum(r);
    }

}
