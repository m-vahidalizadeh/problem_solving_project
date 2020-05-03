package hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Sample input:
 * 5
 * amy 100
 * david 100
 * heraldo 50
 * aakansha 75
 * aleksa 150
 */

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player a, Player b) {
        int scoreCmp = b.score - a.score;
        if (scoreCmp != 0) {
            return scoreCmp;
        }
        return a.name.compareTo(b.name);
    }
}

public class JavaComparator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
