package algorithm.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @program: Leetcode
 * @description:
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 *
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *
 * 提示：
 * 1 <=intervals.length <= 104
 * 0 <= starti < endi <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-30 21:59
 **/
public class Q253_MeetingRooms2 {
    // TODO: 贪心的使用会议室
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
        for(int i = 0; i < intervals.length; i++) {
            if(!heap.isEmpty() && intervals[i][0] >= heap.peek()) heap.poll();
            heap.offer(intervals[i][1]);
        }
        return heap.size();
    }
}
