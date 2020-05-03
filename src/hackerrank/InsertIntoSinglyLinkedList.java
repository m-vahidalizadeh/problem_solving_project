package hackerrank;

import java.io.*;
import java.util.*;

public class InsertIntoSinglyLinkedList {

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

    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        if (head == null) {
            return new SinglyLinkedListNode(data);
        }
        if (position == 0) {
            SinglyLinkedListNode newHead = new SinglyLinkedListNode(data);
            newHead.next = head;
            return newHead;
        }
        SinglyLinkedListNode prevNoe = null;
        SinglyLinkedListNode currentNode = head;
        int index = 0;
        while (index < position) {
            prevNoe = currentNode;
            currentNode = currentNode.next;
            index++;
        }
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
        prevNoe.next = newNode;
        newNode.next = currentNode;
        return head;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/slli.out")))) {
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
