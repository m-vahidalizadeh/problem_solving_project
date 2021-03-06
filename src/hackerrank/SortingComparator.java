package hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Sorting players based on the score and name.
 */
public class SortingComparator {

    public static void main(String[] args) {
        try (
                Scanner scan = new Scanner(System.in)
        ) {
            int n = scan.nextInt();

            Player[] player = new Player[n];
            Checker checker = new Checker();

            for (int i = 0; i < n; i++) {
                player[i] = new Player(scan.next(), scan.nextInt());
            }
            scan.close();

            Arrays.sort(player, checker);
            for (int i = 0; i < player.length; i++) {
                System.out.printf("%s %s\n", player[i].name, player[i].score);
            }
        }
    }

    static class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    static class Checker implements Comparator<Player> {
        public int compare(Player a, Player b) {
            if (a.score > b.score) {
                return -1;
            } else if (a.score == b.score) {
                int nameComparision = a.name.compareToIgnoreCase(b.name);
                if (nameComparision < 0) {
                    return -1;
                } else if (nameComparision == 0) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

}
