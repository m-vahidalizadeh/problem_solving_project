package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LuckBalance {

    static int luckBalance(int k, int[][] contests) {
        List<Integer> importantContests = new ArrayList<>();
        int summationOfNotImportantContests = 0;
        for (int i = 0; i < contests.length; i++) {
            if (contests[i][1] == 1) {
                importantContests.add(contests[i][0]);
            } else {
                summationOfNotImportantContests += contests[i][0];
            }
        }
        Collections.sort(importantContests, Collections.reverseOrder());
        Iterator<Integer> i = importantContests.iterator();
        while (i.hasNext()) {
            if (k > 0) {
                summationOfNotImportantContests += i.next();
                k--;
            } else {
                summationOfNotImportantContests -= i.next();
            }

        }
        return summationOfNotImportantContests;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/lb.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[][] contests = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] contestsRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int contestsItem = Integer.parseInt(contestsRowItems[j]);
                    contests[i][j] = contestsItem;
                }
            }

            int result = luckBalance(k, contests);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
