package dataStructure.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，
 * 从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出:3
 *
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出:2
 * 
 * 限制：
 * 1 <= n<= 10^5
 * 1 <= m <= 10^6
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQOffer62_JosephusCircle {
    // TODO: 约瑟夫环问题
    public int lastRemaining1(int n, int m) {
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < n; i++) nums.add(i);

        int idx = 0;
        while(n > 1) {
            idx = (idx + m - 1) % n;
            nums.remove(idx);
            n--;
        }
        return nums.get(0);
    }

    // TODO：看一下最后活着的人在一开始他的索引号是啥
    //  由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。
    //  当我们知道了 f(n - 1, m) 对应的答案 x 之后，
    //  我们也就可以知道，长度为 n 的序列最后一个删除的元素，应当是从 m % n 开始数的第 x 个元素。
    //  因此有 f(n, m) = (m % n + x) % n = (m + x) % n。
    public int lastRemaining2(int n, int m) {
        if (n == 1) return 0;
        int x = lastRemaining(n - 1, m);
        return (m + x) % n;
    }
    // TODO：
    //  https://blog.csdn.net/u011500062/article/details/72855826
    //  每杀掉一个人，下一个人成为头，相当于把数组向前移动M位。
    //  若已知N-1个人时，胜利者的下标位置位f(N-1,M)，
    //  则N个人的时候，就是往后移动M为，(因为有可能数组越界，超过的部分会被接到头上，所以还要模N)，
    //  既f(N,M)=(f(N - 1, M) + M) % n
    public int lastRemaining(int n, int m) {
        int res = 0;
        for(int i = 2; i <= n; i++) { // TODO: 从剩两个人开始计算
            res = (res + m) % i;
        }
        return res;
    }
}
