package algorithm.intervals;

import dataStructure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 *
 * 提示：
 * intervals[i][0] <= intervals[i][1]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        // 根据每个区间的起始点进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                // TODO : 这里可以根据优先级进行相应的排序
                return interval1[0] - interval2[0];
            }
        });

        // 合并区间
        List<int[]> mergedIntervals = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            int currentSize = mergedIntervals.size();
            if(currentSize == 0 || mergedIntervals.get(currentSize - 1)[1] < start) { // 没有交集
                mergedIntervals.add(new int[]{start, end});
            } else { // 更新最远端点
                mergedIntervals.get(currentSize - 1)[1] = Math.max(end, mergedIntervals.get(currentSize - 1)[1]);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}