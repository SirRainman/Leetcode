package algorithm.double_pointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q763_PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        if(S == null || S.length() == 0) return ans;

        int[] farthest = new int[26];
        char[] s = S.toCharArray();
        // 1 确定每一个字母能走到的最远端
        for(int i = 0; i < s.length; i++) {
            farthest[s[i] - 'a'] = i;
        }

        // 2 重新遍历字符串
        int start = 0, end;
        while(start < s.length) {
            end = farthest[s[start] - 'a'];
            // 确定下来每一个小区间
            for(int i = start; i < end; i++) {
                // 判断区间是否扩大
                if(farthest[s[i] - 'a'] > end) {
                    end = farthest[s[i] - 'a'];
                    System.out.println(end);
                }
            }

            ans.add(end - start + 1);

            // 更新区间的左端点
            start = end+1;
        }

        return ans;
    }
}
