package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Reserve a doubly linked list.
 */
public class ReverseAdoublylinkedlist {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the reverse function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        DoublyLinkedListNode newHead = null;
        if (head == null || head.next == null) {
            return head;
        } else {
            DoublyLinkedListNode current = head;
            DoublyLinkedListNode next = head.next;
            List<Integer> datas;
            datas = new ArrayList<>();
            datas.add(current.data);
            while (next != null) {
                current = current.next;
                next = next.next;
                datas.add(current.data);
            }
            Collections.reverse(datas);
            DoublyLinkedListNode curentNode = null;
            boolean isHead = true;
            for (Integer data : datas) {
                if (isHead) {
                    DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
                    newHead = newNode;
                    curentNode = newNode;
                    isHead = false;
                } else {
                    DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
                    curentNode.next = newNode;
                    newNode.prev = curentNode;
                    curentNode = newNode;
                }
            }
        }
        return newHead;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/rdll.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                DoublyLinkedList llist = new DoublyLinkedList();

                int llistCount = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int i = 0; i < llistCount; i++) {
                    int llistItem = scanner.nextInt();
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                    llist.insertNode(llistItem);
                }

                DoublyLinkedListNode llist1 = reverse(llist.head);

                printDoublyLinkedList(llist1, " ", bufferedWriter);
                bufferedWriter.newLine();
            }
        }
    }

}
