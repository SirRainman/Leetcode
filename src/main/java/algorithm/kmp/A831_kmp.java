package algorithm.kmp;

import java.io.*;

/**
 * 给定一个模式串S，以及一个模板串P，所有字符串中只包含大小写英文字母以及阿拉伯数字。
 * 模板串P在模式串S中多次作为子串出现。
 * 求出模板串P在模式串S中所有出现的位置的起始下标。
 *
 * 输入格式
 * 第一行输入整数N，表示字符串P的长度。
 * 第二行输入字符串P。
 * 第三行输入整数M，表示字符串S的长度。
 * 第四行输入字符串S。
 *
 * 输出格式
 * 共一行，输出所有出现位置的起始下标（下标从0开始计数），整数之间用空格隔开。
 *
 * 数据范围
 * 1≤N≤105
 * 1≤M≤106
 * 输入样例：
 * 3
 * aba
 * 5
 * ababa
 * 输出样例：
 * 0 2
 *
 *
 * https://www.acwing.com/problem/content/833/
 */
public class A831_kmp {
    // TODO:
    //  1 先想怎么暴力
    //  2 优化
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int paternLen = Integer.parseInt(br.readLine());
        char[] patern = br.readLine().toCharArray();
        int strLen = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();

        // TODO：计算next数组，想一想有没有优化方案？？？
        //  想一想为什么next[0]会从0开始？
        //  为什么会从1开始？？？
        //  为什么从-1开始？？？
        int[] next = new int[paternLen];
        for(int i = 1, j = 0; i < paternLen; i++) {
            while(j > 0 && patern[i] != patern[j]) j = next[j - 1];
            if(patern[i] == patern[j]) j++;
            // TODO：next[i] = j 表示（以i-j为起点，i为终点）的后缀 = （以0为起点，j为终点）的前缀 相等，且此字符串的长度最长。
            //  Partial Match Table中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度。
            next[i] = j;
        }

        for(int i = 0, j = 0; i < strLen; i++) {
            while(j > 0 && str[i] != patern[j]) j = next[j - 1];
            if(str[i] == patern[j]) j++;
            if(j == paternLen) { // 成功的匹配
                bw.write(i - paternLen + 1 + " ");
                j = next[j - 1];
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
