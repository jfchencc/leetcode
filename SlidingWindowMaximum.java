class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // monotonic queue, in descending order
        // 比前面大，前面的肯定可以删除。比前面小，不删除，因为这个可能是下一轮的最大
        if (nums == null || nums.length == 0 || k == 0) return new int[0];
        
        int n = nums.length;
        Deque<Integer> q = new LinkedList<>();//store indices not elements
        int[] res = new int[n + 1 - k];
        for (int i = 0; i < n; i++) {
            // beyond window size, remove the first element of the queue
            if (!q.isEmpty() && q.peekFirst() == i - k) q.pollFirst();
            // 比前面大，前面的肯定可以删除。比前面小，不删除，因为这个可能是下一轮的最大
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) q.pollLast();
            q.offerLast(i); // insert the current element index
            
            //add to res
            if (i + 1 - k >= 0) res[i + 1 - k] = nums[q.peek()];
        }
        
        return res;
    }
}