package leetcode.companies.facebook;

public class FBMoveZeros {

    public static void main(String[] args) {
    /*
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
     */
        int[] nums = {0, 0, 1};
        FBMoveZeros fbMoveZeros = new FBMoveZeros();
        fbMoveZeros.moveZeroes(nums);
    }

    public void moveZeroes(int[] nums) {
        int lastZero = nums.length;
        int i = 0;
        while (i < lastZero) {
            if (nums[i] == 0) {
                if (lastZero == nums.length) {
                    for (int j = i; j < nums.length - 1; j++) {
                        nums[j] = nums[j + 1];
                    }
                    nums[nums.length - 1] = 0;
                    lastZero = nums.length - 1;
                } else {
                    for (int j = i; j <= lastZero - 2; j++) {
                        nums[j] = nums[j + 1];
                    }
                    nums[lastZero - 1] = 0;
                    lastZero--;
                }
            }
            if (nums[i] != 0) {
                i++;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            System.out.print(nums[j] + " ");
        }
    }

}
