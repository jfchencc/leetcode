class Solution {
    public int firstMissingPositive(int[] nums) {
        // 把每个数放在应该放的位置上
        // time O(n), space O(1)
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) { 
                // <= 0 和 >= nums.length 不用管，因为肯定不会是答案
                swap(nums, i, nums[i] - 1);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1; // i + 1 是本来应该的数 
        }
        
        return nums.length + 1;
    }
    
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}