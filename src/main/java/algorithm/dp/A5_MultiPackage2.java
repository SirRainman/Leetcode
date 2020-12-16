package algorithm.dp;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包。
 * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 *
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0<N≤1000
 * 0<V≤2000
 * 0<vi,wi,si≤2000
 * 提示：
 * 本题考查多重背包的二进制优化方法。
 *
 * 输入样例
 * 4 5
 * 1 2 3
 * 2 4 1
 * 3 4 3
 * 4 5 2
 * 输出样例：
 * 10
 *
 * https://www.acwing.com/problem/content/5/
 */
public class A5_MultiPackage2 {
    static int MAX_SIZE = 12000;
    static int N, V;
    static int count;
    static int[] v, w;

    // TODO: 尝试性的进行二维转一维的操作
    //  1 因 f[i, j]     = max( f[i-1, j], f[i-1, j-v] + w, f[i-1, j-2v] + 2w, ..., f[i-1, j - sv] + sw)
    //  2 则 f[i, j-v]   = max(            f[i-1, j-v],     f[i-1, j-2v] + w,  ..., f[i-1, j - (s+1)v] + sw)
    //      一定要注意 公式最后这里 和完全背包不一样，完全背包是可以一直往里放，而多重背包是有限个
    //      即完全背包的s是可以最大往其中放s个，多重背包是只能有s[i]个，可以放多余s个
    //  3 故 f[i, j-v]+w = max(            f[i-1, j-v] + w, f[i-1, j-2v] + 2w, ..., f[i-1, j - sv] + sw, f[i-1, j - (s+1)v] + (s+1)w)
    //  4 即 f[i, j]    != max( f[i-1, j], f[i, j-v]+w)
    //  5 因 max操作不能做减法，所以多了的一项f[i-1, j - (s+1)v] + (s+1)w 去不掉
    public static int getMaxWeight() {
        // TODO: 想一想为什么就转化为了01背包问题？？？
        //  二进制优化的方法
        //  1 假设 假设有一组商品，一共有11个.
        //  2 因为 11=1011(B)=0111(B)+(11−0111(B))=0111(B)+0100(B)
        //  3 又   正常背包的思路下，我们要求出含这组商品的最优解，我们要枚举12次（枚举装0，1，2....12个）
        //  4 所以 现在，如果我们把这11个商品分别打包成含商品个数为1个，2个，4个，4个（分别对应0001,0010,0100,0100）的四个”新的商品 “,
        //  5 即   现在将问题转化为01背包问题，对于每个商品，我们都只枚举一次，只需要枚举四次 就可以找出这含组商品的最优解。
        //  6 综上  这样就大大减少了枚举次数。
        int[] f = new int[V + 1];
        for(int i = 1; i <= count; i++) {
            for(int j = V; j >= v[i]; j--) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        return  f[V];
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        V = in.nextInt();
        v = new int[MAX_SIZE]; w = new int[MAX_SIZE];
        count = 0;
        // TODO：问：为什么这么划分是合理的？
        //  需要证明打包后的所有小商品，可以拼成任意的小于s[i]数量的包裹
        for(int i = 1; i <= N; i++) {
            int vi = in.nextInt(), wi = in.nextInt(), si = in.nextInt();
            int k = 1;
            while(si >= k) {
                count++;
                v[count] = k * vi;
                w[count] = k * wi;
                si -= k;
                k = k << 1;
            }
            if(si > 0) {
                count++;
                v[count] = si * vi;
                w[count] = si * wi;
            }
        }

        System.out.println(getMaxWeight());
    }
}
