package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Build Binary Expression Tree From Infix Expression
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).
 * <p>
 * For each internal node with operator o, the infix expression that it represents is (A o B), where A is the expression the left subtree represents and B is the expression the right subtree represents.
 * <p>
 * You are given a string s, an infix expression containing operands, the operators described above, and parentheses '(' and ')'.
 * <p>
 * Return the binary expression tree, which its in-order traversal reproduce s.
 * <p>
 * Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first, and multiplication and division happen before addition and subtraction.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "2-3/(5*2)+1"
 * Output: [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
 * Example 2:
 * <p>
 * Input: s = "3*4-2*5"
 * Output: [-,*,*,3,4,2,5]
 * Example 3:
 * <p>
 * Input: s = "1+2+3+4+5"
 * Output: [+,+,5,+,4,null,null,+,3,null,null,1,2]
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of digits and the characters '+', '-', '*', '/', '(', and ')'.
 * Operands in s are exactly 1 digit.
 * It is guaranteed that s is a valid expression.
 */
public class BuildBinaryTreeFromInfixExpression {

    public Node expTree(String s) {
        Deque<Node> operators = new ArrayDeque<>();
        Deque<Node> operands = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                int start = i + 1;
                int open = 1;
                do {
                    i++;
                    if (s.charAt(i) == '(') open++;
                    else if (s.charAt(i) == ')') open--;
                } while (open != 0);
                Node rec = expTree(s.substring(start, i++));
                operands.push(rec);
            } else if (c >= '0' && c <= '9') {
                operands.push(new Node(c));
                i++;
            } else if (!operators.isEmpty() && (c == '+' || c == '-')) {
                Node root = operators.pop();
                root.right = operands.pop();
                root.left = operands.pop();
                operands.push(root);
            } else {
                operators.push(new Node(c));
                i++;
            }
        }
        while (!operators.isEmpty()) {
            Node root = operators.pop();
            root.right = operands.pop();
            root.left = operands.pop();
            operands.push(root);
        }
        return operands.pop();
    }

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

}
