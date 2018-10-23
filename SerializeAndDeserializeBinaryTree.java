/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        
        //BFS, level order
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            root = q.poll();
            if (root == null) sb.append("null ");
            else {
                sb.append(root.val + " ");
                q.offer(root.left);
                q.offer(root.right);
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;  
        
        String[] strs = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        q.offer(root);
        
        for (int i = 1; i < strs.length; i++) {
            TreeNode cur = q.poll(); // cannot use root, since we need return root at the end
            if (!strs[i].equals("null")) { // use equals, not ==
                cur.left = new TreeNode(Integer.parseInt(strs[i]));
                q.offer(cur.left);
            }
            
            if (!strs[++i].equals("null")) { // use equals, not ==
                cur.right = new TreeNode(Integer.parseInt(strs[i]));
                q.offer(cur.right);
            }
        }
        
        return root;
    }
}
    

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));