import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UBERConstructQuadTree {

    Map<Pair, QuadTreeNode> nodes = new HashMap<>();
    Pair[][] gridPairs;

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}};
        UBERConstructQuadTree uberConstructQuadTree = new UBERConstructQuadTree();
        QuadTreeNode result = uberConstructQuadTree.construct(grid);
        System.out.println();
    }

    public QuadTreeNode construct(int[][] grid) {
        gridPairs = new Pair[grid.length][grid[0].length];
        QuadTreeNode root = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean isRoot = root == null;
                boolean isLeaf = isLeaf(i, j, grid);
                boolean val = grid[i][j] == 1;

                QuadTreeNode topLeft = null;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    topLeft = nodes.get(new Pair(i - 1, j - 1));
                    if (topLeft == null) {
                        topLeft = new QuadTreeNode();
                    }
                }

                QuadTreeNode topRight = null;
                if (i - 1 >= 0 && j + 1 <= grid[0].length - 1) {
                    topRight = nodes.get(new Pair(i - 1, j + 1));
                    if (topRight == null) {
                        topRight = new QuadTreeNode();
                    }
                }

                QuadTreeNode bottomLeft = null;
                if (i + 1 <= grid.length - 1 && j - 1 >= 0) {
                    bottomLeft = nodes.get(new Pair(i + 1, j - 1));
                    if (bottomLeft == null) {
                        bottomLeft = new QuadTreeNode();
                    }
                }

                QuadTreeNode bottomRight = null;
                if (i + 1 <= grid.length - 1 && j + 1 <= grid[0].length - 1) {
                    bottomRight = nodes.get(new Pair(i + 1, j + 1));
                    if (bottomRight == null) {
                        bottomRight = new QuadTreeNode();
                    }
                }

                QuadTreeNode currentNode = new QuadTreeNode(val, isLeaf, topLeft, topRight, bottomLeft, bottomRight);
                if (isRoot) {
                    root = currentNode;
                }
                nodes.put(new Pair(i, j), currentNode);

                if (i - 1 >= 0 && j - 1 >= 0) {
                    Pair tempPair = gridPairs[i - 1][j - 1];
                    if (tempPair == null) {
                        tempPair = new Pair(i - 1, j - 1);
                        gridPairs[i - 1][j - 1] = tempPair;
                    }
                    nodes.put(tempPair, topLeft);
                }

                if (i - 1 >= 0 && j + 1 <= grid[0].length - 1) {
                    Pair tempPair = gridPairs[i - 1][j + 1];
                    if (tempPair == null) {
                        tempPair = new Pair(i - 1, j + 1);
                        gridPairs[i - 1][j + 1] = tempPair;
                    }
                    nodes.put(tempPair, topRight);
                }

                if (i + 1 <= grid.length - 1 && j - 1 >= 0) {
                    Pair tempPair = gridPairs[i + 1][j - 1];
                    if (tempPair == null) {
                        tempPair = new Pair(i + 1, j - 1);
                        gridPairs[i + 1][j - 1] = tempPair;
                    }
                    nodes.put(tempPair, bottomLeft);
                }

                if (i + 1 <= grid.length - 1 && j + 1 <= grid[0].length - 1) {
                    Pair tempPair = gridPairs[i + 1][j + 1];
                    if (tempPair == null) {
                        tempPair = new Pair(i + 1, j + 1);
                        gridPairs[i + 1][j + 1] = tempPair;
                    }
                    nodes.put(tempPair, bottomRight);
                }

            }
        }
        return root;
    }

    public boolean isLeaf(int i, int j, int[][] grid) {
        int currentValue = grid[i][j];
        if (i + 1 <= grid.length - 1 && grid[i + 1][j] != currentValue) {
            return true;
        }
        if (i - 1 >= 0 && grid[i - 1][j] != currentValue) {
            return true;
        }
        if (j + 1 <= grid[i].length - 1 && grid[i][j + 1] != currentValue) {
            return true;
        }
        if (j - 1 >= 0 && grid[i][j - 1] != currentValue) {
            return true;
        }
        return false;
    }

}
