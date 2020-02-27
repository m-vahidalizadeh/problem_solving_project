import javax.print.DocFlavor;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RandAverageTopFiveScores {

    public static void main(String[] args) {
        int[][] items = new int[][]{{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        RandAverageTopFiveScores randAverageTopFiveScores = new RandAverageTopFiveScores();
        int[][] result = randAverageTopFiveScores.highFive(items);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + " -> " + result[i][1]);
        }
    }

    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> idToScoreHeapMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            if (idToScoreHeapMap.containsKey(items[i][0])) {
                idToScoreHeapMap.get(items[i][0]).add(items[i][1]);
            } else {
                PriorityQueue<Integer> tempPQ = new PriorityQueue<>(Comparator.reverseOrder());
                tempPQ.add(items[i][1]);
                idToScoreHeapMap.put(items[i][0], tempPQ);
            }
        }
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (Map.Entry<Integer, PriorityQueue<Integer>> e : idToScoreHeapMap.entrySet()) {
            int id = e.getKey();
            PriorityQueue<Integer> pq = e.getValue();
            double sum = 0.0;
            for (int i = 0; i < 5; i++) {
                sum = sum + pq.poll();
            }
            Double avg = Math.floor(sum / 5);
            resultMap.put(id, avg.intValue());
        }
        int resultSize = resultMap.size();
        int[][] resultArray = new int[resultSize][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> e : resultMap.entrySet()) {
            int id = e.getKey();
            int avg = e.getValue();
            resultArray[index][0] = id;
            resultArray[index][1] = avg;
            index++;
        }
        return resultArray;
    }

}
