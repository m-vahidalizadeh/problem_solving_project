package leetcode.companies.google;

import java.util.*;

/**
 * 210. Course Schedule II
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {

    public class Node {
        int inDegree;
        Set<Integer> outNodes;
        int id;

        public Node(int id) {
            this.id = id;
            inDegree = 0;
            outNodes = new HashSet<>();
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Node[] nodes = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) nodes[i] = new Node(i);
        for (int[] pre : prerequisites) { // a<-b: b is pre for a
            int a = pre[0];
            int b = pre[1];
            nodes[a].inDegree++;
            nodes[b].outNodes.add(a);
        }
        Queue<Node> q = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for (Node node : nodes) {
            if (node.inDegree == 0) q.add(node);
        }
        while (!q.isEmpty()) {
            Node curr = q.poll();
            list.add(curr.id);
            for (int out : curr.outNodes) {
                nodes[out].inDegree--;
                if (nodes[out].inDegree == 0) q.add(nodes[out]);
            }
        }
        if (list.size() < numCourses) return new int[]{};
        int[] res = new int[numCourses];
        int i = 0;
        for (int course : list) res[i++] = course;
        return res;
    }

}
