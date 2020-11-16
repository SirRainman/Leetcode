package dataStructure.graph.topsort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为0到numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * 
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <=numCourses <= 10^5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q207_CourseSchedule {
    class Solution {
        int idx = 0;
        int[] e, head, next;
        int[] indegree;

        public void add(int a, int b) {
            e[idx] = b;
            next[idx] = head[a];
            head[a] = idx++;
        }

        public boolean topSort(int numCourses) {
            Deque<Integer> queue = new LinkedList<>();
            int count = 0;
            for(int i = 0; i < numCourses; i++) {
                if(indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            while(!queue.isEmpty()) {
                int u = queue.poll();
                count++;
                for(int i = head[u]; i != -1; i = next[i]) {
                    int v = e[i];
                    indegree[v]--;
                    if(indegree[v] == 0 ) {
                        queue.offer(v);
                    }
                }
            }

            return count == numCourses;
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            e = new int[prerequisites.length];
            next = new int[prerequisites.length];
            head = new int[numCourses];
            Arrays.fill(head, -1);

            indegree = new int[numCourses];

            for(int[] e : prerequisites) {
                int a = e[0];
                int b = e[1];
                add(b, a);
                indegree[a]++;
            }
            return topSort(numCourses);
        }
    }
}
