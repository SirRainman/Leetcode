package algorithm.math.test;

/**
 * 有两个容量分别为x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的z升水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 */
public class HardQ365_WaterJugProblem {
    class Solution {
        public int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        // TODO:想一想为什么可以用裴蜀定理来解决？？？
        public boolean canMeasureWater(int x, int y, int z) {
            if(x == 0 && y == 0) return z == 0;
            if(z > x + y) return false;
            int g = gcd(x, y);
            // TODO: 裴蜀定理成立的前提就是z 一定是 gcd(a,b)的倍数
            return z % g == 0;
        }
    }
}
