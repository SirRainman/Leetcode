package dataStructure.stack.monotonousStack;

/**
 * @program: Leetcode
 * @description:
 * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-25 20:54
 **/
public class HardQ42_TrappingRainWater {
    // TODO:
    //  如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
    //  如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，因此我们可以弹出栈顶元素并且累加答案到 res 。
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        int top = -1;
        int[] stack = new int[n];
        for(int i = 0; i < n; i++) {
            while(top > -1 && height[i] > height[stack[top]]) {
                int last = stack[top--];
                if(top == -1) break;
                int distance = i - stack[top] - 1;
                int bounded_height = Math.min(height[i], height[stack[top]]) - height[last];
                res += distance * bounded_height;
            }
            stack[++top] = i;
        }
        return res;
    }

    // TODO：动态规划
    //  朴素的做法是对于数组 height 中的每个元素，分别向左和向右扫描并记录左边和右边的最大高度，然后计算每个下标位置能接的雨水量。
    public int trap2(int[] height) {
        int n = height.length;
        if(n <= 1) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }


        int res = 0;
        for(int i = 0; i < n; i++) { // 把每个地方的上方的雨水加在一起
            res += Math.min(leftMax[i], rightMax[i]) - height[i]; // 判断他的上否是否有雨水
        }
        return res;
    }

    // TODO：双指针
    public int trap3(int[] height) {
        int n = height.length;
        if(n <= 2) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = n - 1;
        int res = 0;
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(height[left] > height[right]) { // 计算每个节点的上方的雨水的数量
                res += Math.min(leftMax, rightMax) - height[right];
                right--;
            } else {
                res += Math.min(leftMax, rightMax) - height[left];
                left++;
            }
        }
        return res;
    }
}
