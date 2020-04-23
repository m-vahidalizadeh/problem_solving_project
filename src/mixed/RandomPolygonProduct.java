package mixed;

import java.util.HashSet;
import java.util.Set;

public class RandomPolygonProduct {

    Set<Integer> triangulated = new HashSet<>();

    public static void main(String[] args) {
        int[] A = {1, 3, 1, 4, 1, 5};
        RandomPolygonProduct randomPolygonProduct = new RandomPolygonProduct();
        System.out.println(randomPolygonProduct.minScoreTriangulation(A));
    }

    public int minScoreTriangulation(int[] A) {
        int seed = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            Integer prev = getPrev(A, i + seed);
            Integer next = getNext(A, i + seed);
            int current = getCurrent(A, i + seed);
            if (prev != null && next != null) {
                sum += A[prev] * A[current] * A[next];
                triangulated.add(current);
            }
        }
        int firstSum = sum;
        seed = 1;
        sum = 0;
        triangulated = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            Integer prev = getPrev(A, i + seed);
            Integer next = getNext(A, i + seed);
            int current = getCurrent(A, i + seed);
            if (prev != null && next != null) {
                sum += A[prev] * A[current] * A[next];
                triangulated.add(current);
            }
        }
        int secondSum = sum;
        seed = 2;
        sum = 0;
        triangulated = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            Integer prev = getPrev(A, i + seed);
            Integer next = getNext(A, i + seed);
            int current = getCurrent(A, i + seed);
            if (prev != null && next != null) {
                sum += A[prev] * A[current] * A[next];
                triangulated.add(current);
            }
        }
        return Math.min(Math.min(firstSum, secondSum), sum);
    }

    public int getCurrent(int[] A, int i) {
        return i % (A.length);
    }

    public Integer getPrev(int[] A, int i) {
        int prev = i == 0 ? A.length - 1 : i - 1;
        return triangulated.contains(prev) ? null : prev;
    }

    public Integer getNext(int[] A, int i) {
        int next = (i + 1) % (A.length);
        return triangulated.contains(next) ? null : next;
    }

}
