package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Two cats go to catch a mouse.
 */
public class CatsAndAMouse {

    static String catAndMouse(int x, int y, int z) {
        int catADistance = Math.abs(x - z);
        int catBDistance = Math.abs(y - z);
        if (catADistance > catBDistance) {
            return "Cat B";
        } else if (catADistance < catBDistance) {
            return "Cat A";
        }
        return "Mouse C";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/cam.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String[] xyz = scanner.nextLine().split(" ");
                int x = Integer.parseInt(xyz[0]);
                int y = Integer.parseInt(xyz[1]);
                int z = Integer.parseInt(xyz[2]);
                String result = catAndMouse(x, y, z);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }

}
