package algorithm.diff_prefixSum;

/**
 * @program: Leetcode
 * @description:
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 示例1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-12 11:05
 **/
public class Q134_GasStation {
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++) {
            if(check(gas, cost, i)) return i;
        }
        return -1;
    }

    public boolean check(int[] gas, int[] cost, int start) {
        int n = gas.length, leftGas = 0;
        for(int i = 0; i < n; i++, start = (start + 1) % n) {
            leftGas += gas[start] - cost[start];
            if(leftGas < 0) return false;
        }
        return true;
    }

    // TODO: 将 remain[i] = gas[i] - cost[i] 得出每个站点汽油的净获得量
    //  总油量剩余值的任意部分都需要在X轴以上，且跑到终点时：总剩余汽油量 >= 0。
    //  为了让黑色折线图任意部分都在 X 轴以上，我们需要向上移动黑色折线图，直到所有点都在X轴或X轴以上。
    //  最低点是终点，终点的下一个是起点
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // prefix是积分，寻找最小的积分面积
        int remain = 0, minRemain = Integer.MAX_VALUE, end = 0;
        for(int i = 0; i < n; i++) {
            remain += gas[i] - cost[i];
            if(remain <= minRemain) {
                minRemain = remain;
                end = i;
            }
        }
        return remain >= 0 ? (end + 1) % n : -1;
    }
}
