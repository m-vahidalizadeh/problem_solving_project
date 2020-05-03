package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Solves the grading student problem.
 */
public class GradingStudents {
    static int failure_threshold = 38;

    static int[] gradingStudents(int[] grades) {
        int n = grades.length;
        int[] result = new int[n];
        IntStream.range(0, n).forEach(i -> {
            Double grade = Double.valueOf(grades[i]);
            if (grade < failure_threshold) {
                result[i] = grade.intValue();
            } else {
                int nextMultipleOfFive = ((Double) (Math.ceil(grade / 5) * 5)).intValue();
                if ((nextMultipleOfFive - grade) < 3) {
                    result[i] = nextMultipleOfFive;
                } else {
                    result[i] = grade.intValue();
                }
            }
        });
        return result;
    }

    public static void main(String[] args) throws IOException {

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/gs.out")));
             Scanner scanner = new Scanner(System.in)) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            int[] grades = new int[n];
            for (int gradesItr = 0; gradesItr < n; gradesItr++) {
                int gradesItem = Integer.parseInt(scanner.nextLine().trim());
                grades[gradesItr] = gradesItem;
            }
            int[] result = gradingStudents(grades);
            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.newLine();
        }
    }
}
