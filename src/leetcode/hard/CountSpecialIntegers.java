package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CountSpecialIntegers {

    public int countSpecialNumbers(int N) {
        ArrayList<Integer> L = new ArrayList<>();
        for (int x = N + 1; x > 0; x /= 10) L.add(0, x % 10);
        int res = 0, n = L.size();
        for (int i = 1; i < n; i++) res += 9 * A(9, i - 1);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); j++) {
                if (!seen.contains(j)) res += A(9 - i, n - i - 1);
            }
            if (seen.contains(L.get(i))) break;
            seen.add(L.get(i));
        }
        return res;
    }

    public int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }

}
