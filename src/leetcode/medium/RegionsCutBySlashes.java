package leetcode.medium;

public class RegionsCutBySlashes {

    class DSU {
        int[] root;
        int count;
        int n;

        public DSU(int n) {
            this.n = n;
            root = new int[(n + 1) * (n + 1)];
            count = 1;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    int num = i * (n + 1) + j;
                    if (i == 0 || j == 0 || i == n || j == n) {
                        root[num] = 0;
                    } else {
                        root[num] = num;
                    }
                }
            }
        }

        public int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                count++;
                return;
            }
            root[rootY] = rootX;
        }

        public int getIndex(int i, int j) {
            return i * (n + 1) + j;
        }

    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i].charAt(j)) {
                    case '/':
                        dsu.union(dsu.getIndex(i, j + 1), dsu.getIndex(i + 1, j));
                        break;
                    case '\\':
                        dsu.union(dsu.getIndex(i, j), dsu.getIndex(i + 1, j + 1));
                        break;
                }
            }
        }
        return dsu.count;
    }

    public static void main(String[] args) {
        RegionsCutBySlashes r = new RegionsCutBySlashes();
        String[] input1 =
                {
                        " /",
                        "/ "
                };
        System.out.println(r.regionsBySlashes(input1));
        String[] input2 =
                {
                        " /",
                        "  "
                };
        System.out.println(r.regionsBySlashes(input2));
        String[] input3 =
                {
                        "\\/",
                        "/\\"
                };
        System.out.println(r.regionsBySlashes(input3));
        String[] input4 =
                {
                        "/\\",
                        "\\/"
                };
        System.out.println(r.regionsBySlashes(input4));
        String[] input5 =
                {
                        "//",
                        "/ "
                };
        System.out.println(r.regionsBySlashes(input5));
    }

}
