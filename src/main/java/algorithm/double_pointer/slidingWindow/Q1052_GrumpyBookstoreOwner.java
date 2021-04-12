package algorithm.double_pointer.slidingWindow;

/**
 * @program: Leetcode
 * @description:
 *
 * 今天，书店老板有一家店打算试营业customers.length分钟。
 * 每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。
 * 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * 
 *
 * 提示：
 * 1 <= X <=customers.length ==grumpy.length <= 20000
 * 0 <=customers[i] <= 1000
 * 0 <=grumpy[i] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Rain
 * @create: 2021-02-23 10:52
 **/
public class Q1052_GrumpyBookstoreOwner {

    // TODO:
    //  1.先把确定满意的顾客加起来。
    //  2.然后滑动窗口计算可以额外增加的最大满意顾客数。
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // res=总人数，count=窗口内生气时的顾客总人数，maxCount=窗口内最大生气时的总人数
        int res = 0, saved = 0, maxSaved = 0;
        int left = 0, right = 0;
        while(right < customers.length) {
            if(grumpy[right] == 0) res += customers[right];
            else saved += customers[right];
            if(right - left + 1 > X) {
                if(grumpy[left] == 1) saved -= customers[left];
                left++;
            }
            maxSaved = Math.max(maxSaved, saved);
            right++;
        }
        return res + maxSaved;
    }
}
