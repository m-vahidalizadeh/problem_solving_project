package google;

public class GOOGLHowManySubsequences {

    public static void main(String[] args) {
        GOOGLHowManySubsequences googlHowManySubsequences = new GOOGLHowManySubsequences();
        System.out.println(googlHowManySubsequences.shortestWay("xyz", "xzyxz"));
    }

    public int shortestWay(String source, String target) {
        boolean found = false;
        int backwardIndex = target.length() - 1;
        int index = 0;
        int numberOfSubs = 0;
        while (!found) {
            if (index > target.length() - 1) {
                return numberOfSubs;
            }
            if (backwardIndex < index) {
                return -1;
            }
            String tempSubstring = target.substring(index, backwardIndex + 1);
            boolean isSubsequence = isSubsequence(tempSubstring, source, tempSubstring.length(), source.length());
            if (isSubsequence) {
                numberOfSubs++;
                if (backwardIndex == target.length() - 1) {
                    return numberOfSubs;
                }
                index = backwardIndex + 1;
                backwardIndex = target.length() - 1;
            } else {
                backwardIndex--;
                if (backwardIndex < 0) {
                    return -1;
                }
            }
        }
        return -1;
    }

    public boolean isSubsequence(String A, String B, int m, int n) {
        if (m == 0)
            return true;
        if (n == 0)
            return false;
        if (A.charAt(m - 1) == B.charAt(n - 1))
            return isSubsequence(A, B, m - 1, n - 1);
        return isSubsequence(A, B, m, n - 1);
    }

}
