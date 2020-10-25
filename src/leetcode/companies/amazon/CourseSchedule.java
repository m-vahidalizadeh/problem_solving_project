package leetcode.companies.amazon;

import java.util.*;

/**
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule {

    public class Node {
        Set<Integer> in;
        Set<Integer> out;

        public Node() {
            in = new HashSet<>();
            out = new HashSet<>();
        }
    }

    Map<Integer, Node> nodes;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> path = new ArrayList<>();
        nodes = new HashMap<>();
        for (int i = 0; i < numCourses; i++) nodes.put(i, new Node());
        Queue<Integer> available = new LinkedList<>();
        for (int[] dependency : prerequisites) {
            int course = dependency[0];
            int pre = dependency[1];
            nodes.get(pre).out.add(course);
            nodes.get(course).in.add(pre);
        }
        for (int i = 0; i < numCourses; i++) {
            if (nodes.get(i).in.isEmpty()) available.add(i);
        }
        while (!available.isEmpty()) {
            int currCourse = available.poll();
            path.add(currCourse);
            if (path.size() == numCourses) return true;
            for (int o : nodes.get(currCourse).out) {
                nodes.get(o).in.remove(currCourse);
                if (nodes.get(o).in.isEmpty()) available.add(o);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule c = new CourseSchedule();
        System.out.println(c.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(c.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

}
