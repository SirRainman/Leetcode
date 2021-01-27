package dataStructure.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个由一些多米诺骨牌组成的列表dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
 * 在0 <= i < j < dominoes.length的前提下，找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。
 *
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1128_NumberOfEquivalentDominoPairs {
    // TODO: 注意是怎么将横纵坐标变成一个唯一的标识的！！！
    //  1 「等价」的定义是，在允许翻转两个二元对的的情况下，使它们的元素一一对应相等。
    //  于是我们不妨直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。
    //  这样两个二元对「等价」当且仅当两个二元对完全相同。
    //  2 注意到二元对中的元素均不大于 99，因此我们可以将每一个二元对拼接成一个两位的正整数，即 x + 10y。
    //  这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
    public int numEquivDominoPairs1(int[][] dominoes) {
        int[] count = new int[100];
        int res = 0;
        for(int[] d : dominoes) {
            int val = (d[0] < d[1]) ? d[0] + d[1] * 10 : d[1] + d[0] * 10;
            res += count[val];
            count[val]++;
        }
        return res;
    }

    public int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] d : dominoes) {
            int val = (d[0] < d[1]) ? d[0] + d[1] * 10 : d[1] + d[0] * 10;
            res += map.getOrDefault(val, 0);
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return res;
    }
}
