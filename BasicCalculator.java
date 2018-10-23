class Solution {
    public int calculate(String s) {
        int res = 0, prevSign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    i++;
                }
                res += prevSign * num;
            } else if (s.charAt(i) == '+') {
                prevSign = 1;
            } else if (s.charAt(i) == '-') {
                prevSign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(prevSign);
                res = 0;
                prevSign = 1;
            } else if (s.charAt(i) == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }
}