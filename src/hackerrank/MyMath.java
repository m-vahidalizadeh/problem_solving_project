package hackerrank;

public class MyMath {

    public PerformOperation isOdd() {
        PerformOperation p = new PerformOperation();
        p.setOdd(true);
        return p;
    }

    public PerformOperation isPrime() {
        PerformOperation p = new PerformOperation();
        p.setPrime(true);
        return p;
    }

    public PerformOperation isPalindrome() {
        PerformOperation p = new PerformOperation();
        p.setPalindrome(true);
        return p;
    }

    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

}
