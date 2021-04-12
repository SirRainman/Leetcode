package dataStructure.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description: 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * 
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-26 15:56
 **/
public class HardQ32_LongestValidParentheses {
    public int longestValidParentheses1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[] isValid = new boolean[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (str[i] == '(') {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                int last = stack.pop();
                isValid[last] = true;
                isValid[i] = true;
            }
        }
        int len = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (isValid[i]) {
                len++;
            } else {
                maxLen = Math.max(maxLen, len);
                len = 0;
            }
        }
        return Math.max(maxLen, len);
    }

    // TODO：
    //  具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，
    //  这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
    //      对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
    //      对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
    //          如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
    //          如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
    //  我们从前往后遍历字符串并更新答案即可。
    //  需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
    //      为了保持统一，我们在一开始的时候往栈中放入一个值为 −1 的元素。
    public int longestValidParentheses(String s) {
        char[] str = s.toCharArray();
        int n = str.length, maxLen = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1); // 放一个垫底的
        for (int i = 0; i < n; i++) {
            if (str[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 放一个垫底的
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }


    /**
     * @author S.T., Tang
     * @version 1.0
     * @date 2021/3/25 19:19
     */
    public static int solution(int[] A, int k) {
        int n = A.length;
        int[] leftMin = new int[n];
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        int[] rightMax = new int[n];
        int leftMinV = Integer.MAX_VALUE;
        int leftMaxV = Integer.MIN_VALUE;
        int rightMinV = Integer.MAX_VALUE;
        int rightMaxV = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            leftMinV = Math.min(leftMinV, A[i]);
            leftMaxV = Math.max(leftMaxV, A[i]);
            leftMin[i] = leftMinV;
            leftMax[i] = leftMaxV;
        }
        for (int i = n - 1; i >= 0; i--) {
            rightMinV = Math.min(rightMinV, A[i]);
            rightMaxV = Math.max(rightMaxV, A[i]);
            rightMin[i] = rightMinV;
            rightMax[i] = rightMaxV;
        }

        int minAm = Integer.MAX_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            if (i > 0 && i + k < n) {
                int lMax = leftMax[i - 1];
                int lMin = leftMin[i - 1];
                int rMax = rightMax[i + k];
                int rMin = rightMin[i + k];
                int am = Math.max(lMax, rMax) - Math.min(lMin, rMin);
                minAm = Math.min(minAm, am);
            } else if (i == 0) {
                int max = rightMax[i + k];
                int min = rightMin[i + k];
                int am = max - min;
                minAm = Math.min(minAm, am);
            } else if (i == n - k) {
                int max = leftMax[i - 1];
                int min = leftMin[i - 1];
                int am = max - min;
                minAm = Math.min(minAm, am);
            } else {
                System.out.println("i");
            }
        }
        return minAm;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -2, 1, 0, 8, 7, 1};
        int M = 3;
        for(int x : nums) System.out.println(((x % M) + M) % M) ;
    }


}
