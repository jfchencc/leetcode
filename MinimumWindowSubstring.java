class Solution {
    public String minWindow(String s, String t) {
        int[] count = new int[256];
        for (char c : t.toCharArray()) count[c]++;
        
        int from = 0;// start point for the result
        int res = Integer.MAX_VALUE;
        int total = t.length();
        for (int j = 0, i = 0; j < s.length(); j++) {
            // this character is in t (if more than needed, won't update total but update count)
            if (count[s.charAt(j)]-- > 0) total--;
            while (total == 0) { // valid window
                if (j - i + 1 < res) {
                    res = j - i + 1;
                    from = i;
                }
                // move left pointer to get a smaller window
                if (++count[s.charAt(i++)] > 0) total++;
            }
        }
        
        return res == Integer.MAX_VALUE ? "" : s.substring(from, from + res);
    }
}