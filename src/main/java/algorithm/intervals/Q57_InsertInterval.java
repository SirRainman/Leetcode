package algorithm.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  示例1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
 * 
 *
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q57_InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int left = newInterval[0], right = newInterval[1];
        boolean isInserted = false;
        for(int[] in : intervals) {
            if(right < in[0]) { // 没交集, [left, right] 在in区间的左侧
                if(!isInserted) {
                    merged.add(new int[]{left, right});
                    isInserted = true;
                }
                merged.add(in);
            } else if(in[1] < left){ // 没交集, [left, right] 在in区间的右侧
                merged.add(in);
            } else { // 有交集，求并集
                left = Math.min(left, in[0]);
                right = Math.max(right, in[1]);
            }
        }
        if(!isInserted) merged.add(new int[]{left, right});
        return merged.toArray(new int[merged.size()][]);
    }
}
