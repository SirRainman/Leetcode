package dataStructure.union;

/**
 * 班上有N名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B的朋友，B 是 C的朋友，那么我们可以认为 A 也是 C的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个N * N的矩阵M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出：2 
 * 解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回 2 。
 * 示例 2：
 *
 * 输入：
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出：1
 * 解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
 * 
 *
 * 提示：
 *
 * 1 <= N <= 200
 * M[i][i] == 1
 * M[i][j] == M[j][i]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q547_FriendCircles {
    class Solution {

        int[] parent;
        public int find(int x) {
            if(x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        public int findCircleNum(int[][] M) {
            if(M.length < 1) return 0;

            int ans = 0;
            parent = new int[M.length];
            for(int i = 0; i < parent.length; i++) parent[i] = i;

            for(int i = 0; i < M.length; i++) {
                for(int j = 0; j < M[0].length; j++) {
                    if(M[i][j] == 1) {
                        int p1 = find(i), p2 = find(j);
                        if(p1 == p2) {
                            continue;
                        } else {
                            parent[p1] = p2;
                        }
                    }
                }
            }

            for(int i = 0; i < parent.length; i++) {
                if(parent[i] == i) {
                    ans++;
                }
            }
            return ans;
        }
    }

    // TODO:想一想有没有优化的办法？？？
}
