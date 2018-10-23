class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        
        Set<Character> letter = new HashSet<>();
        for (String w : words) {
            for (char c : w.toCharArray()) letter.add(c);
        }
        
        // building the graph by comparing adjacent strings
        Map<Character, Set<Character>> map = new HashMap<>();// key: parent, val: child
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i], b = words[i + 1];
            int j = 0;
            while (j < a.length() && j < b.length()) {
                if (a.charAt(j) != b.charAt(j)) {  
                    Set<Character> set = map.getOrDefault(a.charAt(j), new HashSet<>());
                    set.add(b.charAt(j));
                    map.put(a.charAt(j), set);
                    break;
                }      
                j++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int[] visited = new int[26];
        for (char c : letter) {
            if (!dfs(map, visited, sb, c)) return "";
        }
        
        return sb.toString();
    }
    
    public boolean dfs(Map<Character, Set<Character>> map, int[] visited, StringBuilder sb, char c) {
        if (visited[c - 'a'] == 2) return true; // fully visited
        if (visited[c -'a'] == 1) return false; // cycle existed
        
        visited[c -'a'] = 1;
        Set<Character> neighbor = map.getOrDefault(c, new HashSet<>());
        for (char n : neighbor) {
            if (!dfs(map, visited, sb, n)) return false;
        }
        visited[c - 'a'] = 2;
        sb.insert(0, c);
        return true;
    }
}