package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MarsExploration {

    static int marsExploration(String s) {
        int counter = 0;
        int index = 0;
        String[] pattern = {"S", "O", "S"};
        for (int i = 0; i < s.length(); i++) {
            if (!pattern[index].equals(String.valueOf(s.charAt(i)))) {
                counter++;
            }
            index = (index + 1) % 3;
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/me.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String s = scanner.nextLine();

            int result = marsExploration(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
