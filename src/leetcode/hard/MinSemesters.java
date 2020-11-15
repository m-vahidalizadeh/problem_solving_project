package leetcode.hard;

import java.util.*;

/**
 * 1136. Parallel Courses
 * There are N courses, labelled from 1 to N.
 *
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 *
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 *
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 *
 * Example 1:
 *
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 * Example 2:
 *
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 *
 * Note:
 *
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 */
public class MinSemesters {

    public class Node {
        int val;
        Set<Node> ins;
        Set<Node> outs;

        public Node(int val) {
            this.val = val;
            this.ins = new HashSet<>();
            this.outs = new HashSet<>();
        }
    }

    Map<Integer, Node> courses;

    public int minimumSemesters(int N, int[][] relations) {
        courses = new HashMap<>();
        for (int[] relation : relations) {
            int s = relation[0];
            int e = relation[1];
            Node sNode = getCourse(s);
            Node eNode = getCourse(e);
            sNode.outs.add(eNode);
            eNode.ins.add(sNode);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            Node courseI = getCourse(i);
            if (courseI.ins.isEmpty()) q.add(courseI);
        }
        int l = 0;
        while (!q.isEmpty()) {
            l++;
            Queue<Node> next = new LinkedList<>();
            while (!q.isEmpty()) {
                Node currNode = q.poll();
                for (Node o : currNode.outs) {
                    o.ins.remove(currNode);
                    if (o.ins.isEmpty()) next.add(o);
                }
                visited.add(currNode.val);
            }
            q = next;
        }
        return visited.size() == N ? l : -1;
    }

    private Node getCourse(int id) {
        if (courses.containsKey(id)) return courses.get(id);
        Node node = new Node(id);
        courses.put(id, node);
        return node;
    }

}
