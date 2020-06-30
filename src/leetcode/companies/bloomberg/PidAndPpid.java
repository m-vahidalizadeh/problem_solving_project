package leetcode.companies.bloomberg;

import java.util.*;

/**
 *Kill Process
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 *
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 *
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
 *
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 *
 * Example 1:
 *
 * Input:
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation:
 *            3
 *          /   \
 *         1     5
 *              /
 *             10
 * Kill 5 will also kill 10.
 * Note:
 *
 * The given kill id is guaranteed to be one of the given PIDs.
 * n >= 1.
 */
public class PidAndPpid {

    Map<Integer, Node> nodes;

    class Node {
        int val;
        List<Node> children;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        nodes = new HashMap<>();
        Node root = null;
        int n = pid.size();
        for (int i = 0; i < n; i++) {
            Node curr = getNode(pid.get(i));
            Node parent = getNode(ppid.get(i));
            if (parent.val != 0) {
                parent.children.add(curr);
            }
        }
        List<Integer> result = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(getNode(kill));
        result.add(kill);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            for (Node c : curr.children) {
                result.add(c.val);
                q.add(c);
            }
        }
        return result;
    }

    private Node getNode(int i) {
        if (nodes.containsKey(i)) return nodes.get(i);
        Node temp = new Node(i);
        nodes.put(i, temp);
        return temp;
    }

}
