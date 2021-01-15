package dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 * 
 *
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer17_1toN {
    // TODO: 当n是一个很大当数应该怎么办？？
    public int[] printNumbers(int n) {
        List<Integer> res = new ArrayList<>();
        dfs(n, 0, new StringBuilder(), res);
        int[] nums = new int[res.size()];
        for(int i = 0; i < res.size(); i++) nums[i] = res.get(i);
        return nums;
    }

    public void dfs(int n, int i, StringBuilder sb, List<Integer> res) {
        if(i == n) {
            while(sb.length() > 0 && sb.toString().charAt(0) == '0') sb.deleteCharAt(0);
            if(sb.length() > 0) res.add(Integer.valueOf(sb.toString()));
            return ;
        }
        for(int j = 0; j < 10; j++) {
            sb.append(j);
            dfs(n, i + 1, sb, res);
            if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        }
    }

    // TODO：怎么设计进位问题？？？
}
