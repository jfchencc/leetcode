class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] dp = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') dp[j] += 1;
                else dp[j] = 0;
            }
            
            maxArea = Math.max(maxArea, largestRectangleArea(dp)); 
        }
        
        return maxArea;
    }
    
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // store the indices
        int maxArea = 0;
        int area = 0;
        int i = 0;
        for (i = 0; i < heights.length;) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) area = heights[top] * i;
                else area = heights[top] * (i - stack.peek() - 1);
                
                maxArea = Math.max(maxArea, area);
            }
        }
        
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (stack.isEmpty()) area = heights[top] * i;
            else area = heights[top] * (i - stack.peek() - 1);
                
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
}