package facebook;

public class FBBulbsToggle {

    public static void main(String[] args) {
    /*
Example:
Input: 3
Output: 1
Explanation:
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].
     */
        FBBulbsToggle fbBulbsToggle = new FBBulbsToggle();
        System.out.println(fbBulbsToggle.bulbSwitch(99999999));
    }

    public int bulbSwitch(int n) {
        int counter = 1;
        if (n < 1) {
            return 0;
        }
        int i = 2;
        int j = i * i;
        while (j <= n) {
            counter++;
            i++;
            j = i * i;
        }
        return counter;
    }

}
