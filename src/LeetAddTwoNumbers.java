public class LeetAddTwoNumbers {

    public static void main(String[] args) {
        /*
Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
         */
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node2.next = node4;
        node4.next = node3;

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node4_2 = new ListNode(4);
        node5.next = node6;
        node6.next = node4_2;

        LeetAddTwoNumbers leetAddTwoNumbers = new LeetAddTwoNumbers();
        ListNode result = leetAddTwoNumbers.addTwoNumbers(node2, node5);
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s1 = "";
        String s2 = "";
        while (l1 != null) {
            s1 = l1.val + s1;
            l1 = l1.next;
        }
        while (l2 != null) {
            s2 = l2.val + s2;
            l2 = l2.next;
        }
        java.math.BigInteger n1 = new java.math.BigInteger(s1);
        java.math.BigInteger n2 = new java.math.BigInteger(s2);
        String resultS = String.valueOf(n1.add(n2).toString());
        ListNode currentNode = null;
        ListNode root = null;
        for (int i = resultS.length() - 1; i >= 0; i--) {
            String tempS = String.valueOf(resultS.charAt(i));
            if (currentNode == null) {
                ListNode newNode = new ListNode(Integer.valueOf(tempS));
                root = newNode;
                currentNode = newNode;
            } else {
                ListNode newNode = new ListNode(Integer.valueOf(tempS));
                currentNode.next = newNode;
                currentNode = newNode;
            }
        }
        return root;
    }

}
