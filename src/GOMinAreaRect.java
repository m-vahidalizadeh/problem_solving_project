import java.util.*;

public class GOMinAreaRect {

    public static void main(String[] args) {
        int[][] input = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(minAreaRect(input));
    }

    public static int minAreaRect(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        Set<Pair> pointsSet = new HashSet<>();
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (points[i][j] == 1) {
                    pointsSet.add(new Pair(i + 1, j + 1));
                    if (rows.containsKey(i + 1)) {
                        Set<Integer> currentSet = rows.get(i + 1);
                        currentSet.add(j + 1);
                    } else {
                        Set<Integer> currentSet = new HashSet<>();
                        currentSet.add(j + 1);
                        rows.put(i + 1, currentSet);
                    }
                }
            }
        }

        int minArea = Integer.MAX_VALUE;
        boolean foundRec = false;
        Iterator<Pair> it = pointsSet.iterator();
        while (it.hasNext()) {
            Pair currentPair = it.next();
            int x = currentPair.x;
            int y = currentPair.y;
            if (x != y) {
                Set<Integer> js = rows.get(x);
                Iterator<Integer> itrj = js.iterator();
                while (itrj.hasNext()) {
                    int currentJ = itrj.next();
                    for (int i = x - 1 + 1; i < n; i++) {
                        if (points[i][y - 1] == 1 && points[i][currentJ] == 1) {
                            foundRec = true;
                            int currentArea = Math.abs((x - 1) - i) * Math.abs((y - 1) - currentJ);
                            if (currentArea < minArea) {
                                minArea = currentArea;
                            }
                        }
                    }
                }
            }
        }



        Iterator<Map.Entry<Integer, Set<Integer>>> itRows = rows.entrySet().iterator();
        while (itRows.hasNext()){
            Map.Entry<Integer, Set<Integer>> next = itRows.next();
            int currentX = next.getKey();
            Set<Integer> currentYs = next.getValue();
            if(currentYs.size()>1){
                Iterator<Integer> ySIterator = currentYs.iterator();
                while(ySIterator.hasNext()){
                    Integer nextY = ySIterator.next();

                }
            }
        }


        if (foundRec) {
            return minArea;
        }
        return 0;
    }

}
