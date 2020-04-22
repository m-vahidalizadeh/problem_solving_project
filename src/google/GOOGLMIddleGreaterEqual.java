package google;

public class GOOGLMIddleGreaterEqual {

    public static void main(String[] args) {
        /*
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
         */
        GOOGLMIddleGreaterEqual googlmIddleGreaterEqual = new GOOGLMIddleGreaterEqual();
//        int[] nums = new int[]{3, 5, 2, 1, 6, 4};
        int[] nums = new int[]{2, 1};
        googlmIddleGreaterEqual.wiggleSort(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    public void wiggleSort(int[] nums) {
        boolean isSorted = false;
        int n = nums.length;
        while (!isSorted) {
            boolean changed = false;
            for (int i = 1; i < n; i += 2) {
                if (i + 1 < n) {
                    if (!(nums[i - 1] <= nums[i] && nums[i] >= nums[i + 1])) {
                        if (nums[i - 1] > nums[i]) {
                            int temp = nums[i - 1];
                            nums[i - 1] = nums[i];
                            nums[i] = temp;
                            changed = true;
                        } else {
                            int temp = nums[i + 1];
                            nums[i + 1] = nums[i];
                            nums[i] = temp;
                            changed = true;
                        }
                    }
                } else {
                    if (nums[i - 1] > nums[i]) {
                        int temp = nums[i - 1];
                        nums[i - 1] = nums[i];
                        nums[i] = temp;
                        changed = true;
                    }
                }
            }
            if (!changed) {
                isSorted = true;
            }
        }
    }

}
