package leetcode.companies.google;

/**
 * Read N Characters Given Read4 II - Call multiple times
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.
 * <p>
 * Method read4:
 * <p>
 * The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 * <p>
 * Definition of read4:
 * <p>
 * Parameter:  char[] buf4
 * Returns:    int
 * <p>
 * Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
 * Below is a high level example of how read4 works:
 * <p>
 * File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
 * char[] buf = new char[4]; // Create buffer with enough space to store characters
 * read4(buf4); // read4 returns 4. Now buf = "abcd", fp points to 'e'
 * read4(buf4); // read4 returns 1. Now buf = "e", fp points to end of file
 * read4(buf4); // read4 returns 0. Now buf = "", fp points to end of file
 * <p>
 * Method read:
 * <p>
 * By using the read4 method, implement the method read that reads n characters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Definition of read:
 * <p>
 * Parameters:	char[] buf, int n
 * Returns:	int
 * <p>
 * Note: buf[] is destination not source, you will need to write the results to buf[]
 * <p>
 * Example 1:
 * <p>
 * File file("abc");
 * Solution sol;
 * // Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
 * sol.read(buf, 1); // After calling your read method, buf should contain "a". We read a total of 1 character from the file, so return 1.
 * sol.read(buf, 2); // Now buf should contain "bc". We read a total of 2 characters from the file, so return 2.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 * Example 2:
 * <p>
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 4); // After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 * <p>
 * Note:
 * <p>
 * Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
 * The read function may be called multiple times.
 * Please remember to RESET your class variables declared in Solution, as static/class variables are persisted across multiple test cases. Please see here for more details.
 * You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 * It is guaranteed that in a given test case the same buffer buf is called by read.
 */
public class ReadFile {

    int read4(char[] buf4) {
        return 0;
    }

    int i4 = 0;
    int n4 = -1;
    char[] r4 = new char[4];
    int i = 0;

    public int read(char[] buf, int n) {
        int total = n;
        i = 0;
        while (total > 0) {
            if (i4 >= n4) {
                n4 = read4(r4);
                i4 = 0;
            }
            if (n4 == 0) return n - total;
            buf[i++] = r4[i4++];
            if (--total == 0) break;
        }
        return n - total;
    }

}
