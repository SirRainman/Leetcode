package dataStructure.union;

import java.util.Scanner;

/**
 * 动物王国中有三类动物A,B,C，这三类动物的食物链构成了有趣的环形。
 *
 * A吃B， B吃C，C吃A。
 *
 * 现有N个动物，以1－N编号。
 *
 * 每个动物都是A,B,C中的一种，但是我们并不知道它到底是哪一种。
 *
 * 有人用两种说法对这N个动物所构成的食物链关系进行描述：
 *
 * 第一种说法是”1 X Y”，表示X和Y是同类。
 *
 * 第二种说法是”2 X Y”，表示X吃Y。
 *
 * 此人对N个动物，用上述两种说法，一句接一句地说出K句话，这K句话有的是真的，有的是假的。
 *
 * 当一句话满足下列三条之一时，这句话就是假话，否则就是真话。
 *
 * 1） 当前的话与前面的某些真的话冲突，就是假话；
 * 2） 当前的话中X或Y比N大，就是假话；
 * 3） 当前的话表示X吃X，就是假话。
 *
 * 你的任务是根据给定的N和K句话，输出假话的总数。
 *
 * 输入格式
 * 第一行是两个整数N和K，以一个空格分隔。
 *
 * 以下K行每行是三个正整数 D，X，Y，两数之间用一个空格隔开，其中D表示说法的种类。
 *
 * 若D=1，则表示X和Y是同类。
 *
 * 若D=2，则表示X吃Y。
 *
 * 输出格式
 * 只有一个整数，表示假话的数目。
 *
 * 数据范围
 * 1≤N≤50000,
 * 0≤K≤100000
 * 输入样例：
 * 100 7
 * 1 101 1
 * 2 1 2
 * 2 2 3
 * 2 3 3
 * 1 1 3
 * 2 3 1
 * 1 5 5
 * 输出样例：
 * 3
 *
 * https://www.acwing.com/problem/content/242/
 */
public class UnresolvedA240_FoodCircle {
    static int[] parent, depth;

    static int find(int x) {
        if(parent[x] != x) {
            int root = find(parent[x]); //找到x的根节点
            depth[x] += depth[parent[x]]; // x的高度 = x的父节点的高度 + x的高度
            parent[x] = root; // 路径压缩，x的父节点更新为root根节点
        }
        return parent[x];
    }

    // TODO:这题完全看不懂啊
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt(), times = in.nextInt();
        parent = new int[size + 1];
        depth = new int[size + 1];
        for(int i = 1; i <= size; i++) {
            parent[i] = i;
            depth[i] = 0;
        }

        // 如果x,y是同类,但是x的捕食域有y,那么错误
        // 如果x,y是同类,但是x的天敌域有y,那么错误
        // 如果x是y的天敌,但是x的同类域中有y,那么错误
        // 如果x是y的天敌,但是x的天敌域中有y,那么错误
        int error = 0;
        while(times-- > 0) {
            int op = in.nextInt(), x = in.nextInt(), y = in.nextInt();
            if(x > size || y > size) {
                error++;
            } else if(op == 1) { // x y是在同一种群
                int parentX = find(x), parentY = find(y);
                if(parentX == parentY && (depth[x] - depth[y]) % 3 != 0) {
                    // 当x和y在同一种群时，如果他们的高度差不能整除3，说明他们有吃与被吃的关系，是假话
                    error++;
                } else if(parentX != parentY){ // 没有处理过x和y的关系
                    parent[parentX] = parentY;
                    // TODO：不太明白
                    depth[parentX] = depth[y] - depth[x];
                }
            } else {
                if(x == y) {
                    error++;
                } else { // x 吃 y
                    int parentX = find(x), parentY = find(y);
                    if(parentX == parentY && (depth[x] - depth[y] - 1) % 3 != 0) {
                        // 如果x吃y，说明：x的同类域中有y为假话，x的天敌域中有y也是假话
                        // TODO：不太明白
                        error++;
                    } else if(parentX != parentY) {
                        // TODO：不太明白，为什么x吃y也要合并？？通过高度来判断是否是同一种群，而不是通过父节点
                        parent[parentX] = parentY;
                        depth[parentX] = depth[y] - depth[x] + 1;
                    }
                }
            }
        }
        System.out.print(error);
    }
}
