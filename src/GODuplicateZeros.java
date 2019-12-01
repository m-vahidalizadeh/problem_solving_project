import java.util.Arrays;

public class GODuplicateZeros {

    public static void main(String[] args) {
        int[] input = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        System.out.println("Before: ");
        Arrays.stream(input).forEach(e -> System.out.print(" " + e));
        duplicateZeros(input);
        System.out.println();
        System.out.println("After: ");
        Arrays.stream(input).forEach(e -> System.out.print(" " + e));
    }

    public static void duplicateZeros(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0 && i + 1 < n) {
                for (int j = n - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[i + 1] = 0;
                i++;
            }
        }
    }

}
