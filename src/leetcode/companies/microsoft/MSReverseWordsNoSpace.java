package leetcode.companies.microsoft;

public class MSReverseWordsNoSpace {


    public static void main(String[] args) {
        /*
        Input: "Let's take LeetCode contest"
        Output: "s'teL ekat edoCteeL tsetnoc"
         */
        System.out.println(reverseWords("Let's take LeetCode contest"));
//        System.out.println(reverse("Let's take LeetCode contest".toCharArray(), 0, 4));
//        System.out.println(reverse("Let's take LeetCode contest".toCharArray(), 6, 9));
//        System.out.println(reverse("Let's take LeetCode contest".toCharArray(), 11, 18));
//        System.out.println(reverse("Let's take LeetCode contest".toCharArray(), 20, 26));
    }

    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int begin = -1;
        int end = -1;
        for (int i = 0; i < chars.length; i++) {
            char tempChar = chars[i];
            if (' ' != tempChar && begin == -1) {
                begin = i;
                end = -1;
            } else if (' ' != tempChar && begin != -1 && i != chars.length - 1) {
                // Skip
            } else if (i == chars.length - 1) {
                end = i;
                chars = reverse(chars, begin, end);
                begin = -1;
                end = -1;
            } else if (' ' == tempChar) {
                end = i - 1;
                chars = reverse(chars, begin, end);
                begin = -1;
                end = -1;
            }
        }
        return String.valueOf(chars);
    }

    public static char[] reverse(char[] input, int begin, int end) {
        for (int i = begin; i <= begin + ((end - begin + 1) / 2); i++) {
            if (i + 1 != end - i + begin) {
                char tempChar = input[i];
                input[i] = input[end - i + begin];
                input[end - i + begin] = tempChar;
            }
        }
        return input;
    }

}
