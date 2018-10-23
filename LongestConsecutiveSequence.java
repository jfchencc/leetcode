class Solution {
    public int longestConsecutive(int[] nums) {
        // O(n) time use HashSet
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);
        
        // delete the consecutive seq
        for (int i = 0; i < nums.length; i++) {
            int low = nums[i] - 1, high = nums[i] + 1;
            while (set.contains(low)) {
                set.remove(low);
                low--;
            }
            while (set.contains(high)) {
                set.remove(high);
                high++;
            }
            max = Math.max(max, high - low - 1);
        }
        
        return max;
    }
}