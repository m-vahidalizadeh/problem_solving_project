public class AAPLBinaryTargetFinder {

    /*
Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
     */

    public static void main(String[] args) {
        int[] example1 = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(searchRange(example1, 8)[0] + " " + searchRange(example1, 8)[1]);
//        System.out.println(binarySearch(example1, 8, 0, 5));
        int[] example2 = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(searchRange(example2, 6)[0] + " " + searchRange(example2, 6)[1]);
//        System.out.println(binarySearch(example1, 8, 6, 5));
        int[] example3 = new int[]{1, 1, 2};
        System.out.println(searchRange(example3, 1)[0] + " " + searchRange(example3, 1)[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int bSearchResult = binarySearch(nums, target, 0, nums.length - 1);
        if (bSearchResult == -1) {
            return new int[]{-1, -1};
        } else {
            int start = bSearchResult;
            int end = bSearchResult;
            while (start - 1 >= 0 && nums[start - 1] == target) {
                start = start - 1;
            }
            while (end + 1 <= nums.length - 1 && nums[end + 1] == target) {
                end = end + 1;
            }
            return new int[]{start, end};
        }
    }

    public static int binarySearch(int[] nums, int target, int start, int end) {
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }
        if (start < end && start >= 0 && end <= (nums.length - 1)) {
            int length = end - start;
            int mid = start + length / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] < target) {
                    return binarySearch(nums, target, mid + 1, end);
                } else {
                    return binarySearch(nums, target, start, mid - 1);
                }
            }
        } else {
            return -1;
        }
    }

}
