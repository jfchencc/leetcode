class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int leftHighest = 0, rightHighest = 0;
        int res = 0;
        while (l < r) {
            leftHighest = Math.max(leftHighest, height[l]);
            rightHighest = Math.max(rightHighest, height[r]);      
            if (leftHighest < rightHighest) {
                res += leftHighest - height[l++];
            } else {
                res += rightHighest - height[r--];
            }
        }
        return res;
    }
}