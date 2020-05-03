package leetcode.mixed;

import java.util.Arrays;
import java.util.LinkedList;

public class PrintImmutableLLReverse {

    public static void main(String[] args) {
    /*
Example 1:
Input: head = [1,2,3,4]
Output: [4,3,2,1]

Example 2:
Input: head = [0,-4,-1,3,-5]
Output: [-5,3,-1,-4,0]

Example 3:
Input: head = [-2,0,6,4,4,-6]
Output: [-6,4,4,6,0,-2]

     */
        PrintImmutableLLReverse printImmutableLLReverse = new PrintImmutableLLReverse();
        LinkedList<Integer> head = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
        printImmutableLLReverse.printLinkedListInReverse(new ImmutableListNodeImpl(head));
    }

    /**
     * // This is the leetcode.mixed.ImmutableListNode's API interface.
     * // You should not implement it, or speculate about its implementation.
     * interface leetcode.mixed.ImmutableListNode {
     * public void printValue(); // print the value of this node.
     * public leetcode.mixed.ImmutableListNode getNext(); // return the next node.
     * };
     */

    public void printLinkedListInReverse(ImmutableListNode head) {
        ImmutableListNode nextNode = head.getNext();
        if (nextNode != null) {
            printLinkedListInReverse(nextNode);
        }
        head.printValue();
    }

}
