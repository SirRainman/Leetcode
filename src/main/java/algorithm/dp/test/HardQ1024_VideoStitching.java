package algorithm.dp.test;

import java.util.Arrays;

/**
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为T秒的体育赛事。
 * 这些片段可能有所重叠，也可能长度不一。
 * 视频片段clips[i]都用区间进行表示：开始于clips[i][0]并于clips[i][1]结束。
 * 我们甚至可以对这些片段自由地再剪辑，例如片段[0, 7]可以剪切成[0, 1] + [1, 3] + [3, 7]三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。
 * 返回所需片段的最小数目，如果无法完成该任务，则返回-1 。
 * 
 * 示例 1：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 
 * 示例 2：
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 
 * 示例 3：
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 
 * 示例 4：
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 * 
 * 
 * 提示：
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <=clips[i][1] <= 100
 * 0 <= T <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1024_VideoStitching {
    // TODO: 
    //  集合划分：dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量
    //  属性：min
    public int videoStitching1(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                // 假如该子区间可以覆盖到i
                if (clip[0] < i && clip[1] >= i) {
                    // dp[i]取从子区间起点到i
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

    // TODO: 记录每个节点可以走到的最远的距离
    public int videoStitching2(int[][] clips, int T) {
        int[] farthest = new int[T];
        for(int[] clip : clips) {
            if(clip[0] < T) {
                // 记录以其为左端点的子区间中最远的右端点
                farthest[clip[0]] = Math.max(farthest[clip[0]], clip[1]);
            }
        }
        int res = 0, pre = 0, mostFar = 0;
        for(int i = 0; i < T; i++) {
            // 枚举每一个位置，假设当枚举到位置 i 时，记左端点不大于 i 的所有子区间的最远右端点为 mostFar
            mostFar = Math.max(mostFar, farthest[i]);
            // 如果走到了能走到的最远端，但是还没到T，说明走不到T
            if(i == mostFar) return -1;
            // pre是上一个能走到到最远端
            if(i == pre) {
                res++;
                pre = mostFar;
            }
        }
        return res;
    }
}
