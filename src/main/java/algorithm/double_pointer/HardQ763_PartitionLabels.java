package algorithm.double_pointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。
 * 我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1：
 * 输入：S = "ababcbaca defegde hijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ763_PartitionLabels {

    public List<Integer> partitionLabels1(String S) {
        List<Integer> res = new ArrayList<>();
        if(S == null) return res;
        char[] s = S.toCharArray();
        int[] farthest = new int[26];
        // 1 确定每一个字母能走到的最远端
        for(int i = 0; i < s.length; i++) {
            farthest[s[i] - 'a'] = i;
        }
        int left = 0, right = farthest[s[0] - 'a'];
        while(left < s.length) {
            // 确定下来每一个小区间
            right = farthest[s[left] - 'a'];
            for(int i = left + 1; i < right; i++) {
                // 判断区间是否扩大
                if(farthest[s[i] - 'a'] > right) {
                    right = farthest[s[i] - 'a'];
                }
            }
            res.add(right - left + 1);
            // 更新区间的左端点
            left = right + 1;
        }
        return res;
    }
}
