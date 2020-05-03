package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BeautifulBinaryString {

    static int beautifulBinaryString(String b) {
        int changes = 0;
        boolean found = true;
        int n = b.length();
        while (found) {
            int result = b.indexOf("010");
            if (result == -1) {
                found = false;
            } else {
                int resultPlusFour = result + 3;
                changes++;
                if (resultPlusFour < n) {
                    if (b.charAt(resultPlusFour) == '1') {
                        b = b.substring(0, result) + "011" + b.substring(resultPlusFour, n);
                    } else {
                        b = b.substring(0, result) + "000" + b.substring(resultPlusFour, n);
                    }
                } else {
                    found = false;
                }
            }
        }
        return changes;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/bbs.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String b = scanner.nextLine();

            int result = beautifulBinaryString(b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
