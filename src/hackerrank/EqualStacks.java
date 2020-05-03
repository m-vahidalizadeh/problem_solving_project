package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class EqualStacks {

    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        int h1Height = getHeight(h1);
        int h2Height = getHeight(h2);
        int h3Height = getHeight(h3);
        if (h1Height == h2Height && h2Height == h3Height) {
            return h1Height;
        } else if (h1Height == 0 || h2Height == 0 || h3Height == 0) {
            return 0;
        } else {
            return getMaxPossibleHeight(h1, h2, h3);
        }
    }

    private static int getMaxPossibleHeight(int[] h1, int[] h2, int[] h3) {
        return Math.max(Math.max(equalStacks(removeLast(h1), h2, h3), equalStacks(h1, removeLast(h2), h3)),
                equalStacks(h1, h2, removeLast(h3)));
    }

    static int[] removeLast(int[] h) {
        return Arrays.copyOfRange(h, 1, h.length);
    }

    static int getHeight(int[] h) {
        return Arrays.stream(h).sum();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/es.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] n1N2N3 = scanner.nextLine().split(" ");

            int n1 = Integer.parseInt(n1N2N3[0].trim());

            int n2 = Integer.parseInt(n1N2N3[1].trim());

            int n3 = Integer.parseInt(n1N2N3[2].trim());

            int[] h1 = new int[n1];

            String[] h1Items = scanner.nextLine().split(" ");

            for (int h1Itr = 0; h1Itr < n1; h1Itr++) {
                int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
                h1[h1Itr] = h1Item;
            }

            int[] h2 = new int[n2];

            String[] h2Items = scanner.nextLine().split(" ");

            for (int h2Itr = 0; h2Itr < n2; h2Itr++) {
                int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
                h2[h2Itr] = h2Item;
            }

            int[] h3 = new int[n3];

            String[] h3Items = scanner.nextLine().split(" ");

            for (int h3Itr = 0; h3Itr < n3; h3Itr++) {
                int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
                h3[h3Itr] = h3Item;
            }

            int result = equalStacks(h1, h2, h3);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
