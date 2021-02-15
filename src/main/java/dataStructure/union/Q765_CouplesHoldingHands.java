package dataStructure.union;

/**
 * @program: Leetcode
 * @description:
 * 
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 一次交换可选择任意两人，让他们站起来交换座位。
 * 人和座位用0到2N-1的整数表示，情侣们按顺序编号，第一对是(0, 1)，第二对是(2, 3)，以此类推，最后一对是(2N-2, 2N-1)。
 * 这些情侣的初始座位row[i]是由最初始坐在第 i 个座位上的人决定的。
 *
 * 示例 1:
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 *
 * 示例 2:
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 * len(row) 是偶数且数值在[4, 60]范围内。
 * 可以保证row 是序列0...len(row)-1的一个全排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author: Rain
 * @create: 2021-02-14 23:06
 **/
public class Q765_CouplesHoldingHands {

    // n 个人每个人都有自己的id，如果两个人是情侣则他们的id是相同的
    // 如果相邻的两个人是情侣，在并查集中他们的id是相同的
    // 如果两个人不是情侣，则说明这是两对情侣，他们的位置需要调整，将所有“错付”的情侣的id放到一个连通分量中，
    //  最小的调整次数就是并查集中连通分量的大小减一

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int couples = n / 2;

        UnionFind uf = new UnionFind(couples);
        for(int i = 0; i < n; i += 2) {
            int a = row[i] / 2;
            int b = row[i + 1] / 2;
            uf.union(a, b);
        }

        return couples - uf.getIsland();
    }

    class UnionFind {
        private int island;
        private int[] parent;

        public UnionFind(int n) {
            island = n;
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int a) {
            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return ;
            parent[pa] = pb;
            island--;
        }

        public int getIsland() {
            return island;
        }
    }
}
