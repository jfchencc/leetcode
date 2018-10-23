class Solution {
    public int minDistance(String word1, String word2) {
        int n = word2.length(), m = word1.length();
        int[] dp = new int[n + 1];
        //initial
        for (int j = 0; j <= n; j++) dp[j] = j;
        
        for (int i = 1; i <= m; i++) {
            int prevDiagonal = dp[0]; // put inside this loop, NOT outside          
            for (int j = 0; j <= n; j++) {
                if (j == 0) dp[j] = i;
                else {        
                    int temp = dp[j];
                    
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[j] = prevDiagonal;
                    else dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prevDiagonal) + 1;
                    
                    prevDiagonal = temp;
                }
            }
        }
        
        return dp[n];
    }
}