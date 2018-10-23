class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
        int[] count = new int[256];
        int res = 0;
        int i = 0; // left pointer
        int uniqueChars = 0; // window constraint
        
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) uniqueChars++;
            while (i <= j && uniqueChars > 2) {
                // move left pointer
                if (--count[s.charAt(i++)] == 0) uniqueChars--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}