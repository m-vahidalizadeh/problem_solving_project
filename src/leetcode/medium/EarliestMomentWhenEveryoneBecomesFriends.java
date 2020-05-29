package leetcode.medium;

import java.util.Arrays;

public class EarliestMomentWhenEveryoneBecomesFriends {

    /**
     * Union finder.
     */
    public class UnionFinder {
        public int[] parents;
        public int[] sizes;
        public int count;

        /**
         * Initializes the finder
         *
         * @param N The number of nodes
         */
        public UnionFinder(int N) {
            parents = new int[N];
            sizes = new int[N];
            count = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        /**
         * Finds the parent of node i.
         *
         * @param i The node i
         * @return The parent of node i
         */
        public int findParent(int i) {
            while (i != parents[i]) {
                parents[i] = parents[parents[i]];
                i = parents[i];
            }
            return parents[i];
        }

        /**
         * Unions two nodes if they don't have the same root.
         *
         * @param i The node i
         * @param j the node j
         * @return True if count is 1 (all nodes are in one cluster)
         */
        public boolean union(int i, int j) {
            int pI = findParent(i);
            int pJ = findParent(j);
            if (pI == pJ) return false;
            else if (sizes[pI] > sizes[pJ]) {
                parents[pJ] = pI;
                sizes[pI] += sizes[pJ];
            } else {
                parents[pI] = pJ;
                sizes[pJ] += sizes[pI];
            }
            return --count == 1;
        }
    }

    /**
     * Returns the moment that all the nodes become part of one cluster.
     *
     * @param logs the logs 2D array
     * @param N    the number of nodes
     * @return The moment that all the nodes become par of one cluster
     */
    public int earliestAcq(int[][] logs, int N) {
        UnionFinder uf = new UnionFinder(N);
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < logs.length; i++) {
            if (uf.union(logs[i][1], logs[i][2])) return logs[i][0];
        }
        return -1;
    }

    public static void main(String[] args) {
        EarliestMomentWhenEveryoneBecomesFriends e = new EarliestMomentWhenEveryoneBecomesFriends();
        int[][] logs = {{20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5}, {20190224, 2, 4}, {20190301, 0, 3}, {20190312, 1, 2}, {20190322, 4, 5}};
        System.out.println(e.earliestAcq(logs, 6));
    }

}
