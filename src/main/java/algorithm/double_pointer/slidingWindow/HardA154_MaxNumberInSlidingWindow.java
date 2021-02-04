package algorithm.double_pointer.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个大小为n≤106的数组。
 * 有一个大小为k的滑动窗口，它从数组的最左边移动到最右边。
 * 您只能在窗口中看到k个数字。
 * 每次滑动窗口向右移动一个位置。
 *
 * 以下是一个例子：
 * 该数组为[1 3 -1 -3 5 3 6 7]，k为3。
 *
 * 窗口位置	最小值	最大值
 * [1 3 -1] -3 5 3 6 7	-1	3
 * 1 [3 -1 -3] 5 3 6 7	-3	3
 * 1 3 [-1 -3 5] 3 6 7	-3	5
 * 1 3 -1 [-3 5 3] 6 7	-3	5
 * 1 3 -1 -3 [5 3 6] 7	3	6
 * 1 3 -1 -3 5 [3 6 7]	3	7
 * 您的任务是确定滑动窗口位于每个位置时，窗口中的最大值和最小值。
 *
 * 输入格式
 * 输入包含两行。
 * 第一行包含两个整数n和k，分别代表数组长度和滑动窗口的长度。
 * 第二行有n个整数，代表数组的具体数值。
 * 同行数据之间用空格隔开。
 *
 * 输出格式
 * 输出包含两个。
 * 第一行输出，从左至右，每个位置滑动窗口中的最小值。
 * 第二行输出，从左至右，每个位置滑动窗口中的最大值。
 *
 * 输入样例：
 * 8 3
 * 1 3 -1 -3 5 3 6 7
 * 输出样例：
 * -1 -3 -3 -3 3 3
 * 3 3 5 5 6 7
 *
 * https://www.acwing.com/problem/content/156/
 */
public class HardA154_MaxNumberInSlidingWindow {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int len = Integer.parseInt(str[0]), k = Integer.parseInt(str[1]);
        int[] nums = new int[len];

        str = br.readLine().split(" ");
        for (int i = 0; i < len; i++) nums[i] = Integer.parseInt(str[i]);

        // TODO:想一想为什么队列的长度不能为窗口的大小 因为front rear是数组下标，且队列并不可以动态的添加或减少元素
        int[] queue = new int[1000010];
        int rear = -1, front = 0;
        // 求窗口之中的最小值
        for (int i = 0; i < len; i++) {
            // 判断队头是否在窗口之外 窗口的起点i - (k - 1)
            if (front <= rear && queue[front] < i - k + 1) front++; //因为每次窗口只移动一位，所以用if
            // 当当前元素比对尾要小时，说明队尾的元素永远不可能是窗口中的最小值
            while (front <= rear && nums[i] <= nums[queue[rear]]) rear--; // 类似单调栈
            // TODO:想一想为什么不直接存值，而是存下标？？？
            queue[++rear] = i;
            if (i >= k - 1) System.out.print(nums[queue[front]] + " ");
        }
        System.out.println();

        // 求窗口之中的最大值
        rear = -1;
        front = 0;
        for (int i = 0; i < len; i++) {
            if (front <= rear && queue[front] < i - k + 1) front++;
            while (front <= rear && nums[i] >= nums[queue[rear]]) rear--;
            queue[++rear] = i;
            if (i >= k - 1) System.out.print(nums[queue[front]] + " ");
        }
    }
}
