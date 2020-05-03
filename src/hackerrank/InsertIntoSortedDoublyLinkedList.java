package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class InsertIntoSortedDoublyLinkedList {

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

    // Complete the sortedInsert function below.

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
    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        if(head == null){
            return new DoublyLinkedListNode(data);
        } else if(data < head.data){
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        } else {
            DoublyLinkedListNode current = head;
            DoublyLinkedListNode next = head.next;
            boolean found = false;
            while(!found && next != null){
                if(current.data <= data && data <= next.data){
                    found = true;
                } else{
                    current = next;
                    next = next.next;
                }
            }
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
            if(next == null){
                current.next = newNode;
                newNode.prev = current;
                return head;
            } else {
                current.next = newNode;
                newNode.prev = current;
                newNode.next = next;
                next.prev = newNode;
                return head;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/iisdll.out")));
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

                int data = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                DoublyLinkedListNode llist1 = sortedInsert(llist.head, data);

                printDoublyLinkedList(llist1, " ", bufferedWriter);
                bufferedWriter.newLine();
            }
        }
    }

}
