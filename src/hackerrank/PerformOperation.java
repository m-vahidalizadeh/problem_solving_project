package hackerrank;

public class PerformOperation {

    private boolean isOdd = false;
    private boolean isPrime = false;
    private boolean isPalindrome = false;

    public void setOdd(boolean odd) {
        isOdd = odd;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public void setPalindrome(boolean palindrome) {
        isPalindrome = palindrome;
    }

    public boolean check(int num) {
        if (isOdd) {
            return isOdd(num);
        } else if (isPrime) {
            return isPrime(Long.valueOf(num));
        } else if (isPalindrome) {
            return isPalindrome(String.valueOf(num));
        }
        return false;
    }

    private boolean isOdd(int num) {
        return !(num % 2 == 0);
    }

    private boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < (n / 2); ++i) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

}
