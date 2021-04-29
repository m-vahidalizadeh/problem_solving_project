package leetcode.companies.facebook;

public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        int sN = s.length();
        int tN = t.length();
        int diff = sN - tN;
        if (diff < -1 || 1 < diff) return false;
        if (s.isBlank() && t.isBlank()) return false;
        if (s.isBlank() || t.isBlank()) return true;
        int rem = 1;
        int sI = 0;
        if (diff == 0) {
            for (int i = 0; i < tN; i++) {
                if (s.charAt(sI) != t.charAt(i)) {
                    if (rem > 0) rem--;
                    else return false;
                    sI++;
                } else sI++;
            }
        } else if (diff == -1) {
            for (int i = 0; i < tN; i++) {
                if (s.charAt(sI) != t.charAt(i)) {
                    if (rem > 0) rem--;
                    else return false;
                } else sI++;
            }
        } else { // diff==1
            for (int i = 0; i < tN; i++) {
                if (s.charAt(sI) != t.charAt(i)) {
                    if (rem > 0) rem--;
                    else return false;
                    sI++;
                    i--;
                } else sI++;
            }
        }
        return rem == 0;
    }

    public static void main(String[] args) {
        OneEditDistance o = new OneEditDistance();
        System.out.println(o.isOneEditDistance("a", "ac"));
    }

}
