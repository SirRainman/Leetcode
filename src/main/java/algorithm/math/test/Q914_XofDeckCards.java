package algorithm.math.test;

/**
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有X张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回true。
 *
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 * 
 * 提示：
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Q914_XofDeckCards {
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length < 2) return false;

        // 统计数目
        int[] count = new int[10010];
        for(int x : deck) count[x]++;

        int g = 0;
        for(int c : count) {
            if(c == 1) {
                return false;
            } else if(c > 1) {
                g = gcd(g, c); // 求所有数的最大公因数
                if(g == 1) return false;
            }
        }

        return true;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
