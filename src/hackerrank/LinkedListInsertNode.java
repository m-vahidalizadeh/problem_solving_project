package hackerrank;

import java.io.*;
import java.util.*;

public class LinkedListInsertNode {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the insertNodeAtPosition function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        if (position == 0) {
            SinglyLinkedListNode singlyLinkedListNode = new SinglyLinkedListNode(data);
            singlyLinkedListNode.next = head;
            return singlyLinkedListNode;
        }
        int currentPosition = 0;
        SinglyLinkedListNode currentNode = head;
        SinglyLinkedListNode prevNode = head;
        while (currentPosition < position) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentPosition++;
        }
        SinglyLinkedListNode singlyLinkedListNode = new SinglyLinkedListNode(data);
        prevNode.next = singlyLinkedListNode;
        singlyLinkedListNode.next = currentNode;
        return head;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/llin.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            SinglyLinkedList llist = new SinglyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            int data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int position = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            SinglyLinkedListNode llist_head = insertNodeAtPosition(llist.head, data, position);

            printSinglyLinkedList(llist_head, " ", bufferedWriter);
            bufferedWriter.newLine();
        }
    }

}
