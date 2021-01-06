package dataStructure.stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对appendTail、deleteHead 进行10000次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer9_ImplementQueueUsingStacks {
    int N = 10010;
    int[] in, out;
    int topIn, topOut;

    public QOffer9_ImplementQueueUsingStacks() {
        in = new int[N];
        out = new int[N];
        topIn = -1;
        topOut = -1;
    }

    public void appendTail(int value) {
        in[++topIn] = value;
    }

    public int deleteHead() {
        if(topOut == -1 && topIn == -1) return -1;
        if(topOut == -1) {
            while(topIn > -1) {
                out[++topOut] = in[topIn--];
            }
        }
        return out[topOut--];
    }
}
