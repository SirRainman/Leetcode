package dataStructure.array.bit;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: Leetcode
 * @description:
 * 我们有一个非负整数数组A。
 * 对于每个（连续的）子数组B =[A[i], A[i+1], ..., A[j]] （i <= j），
 * 我们对B中的每个元素进行按位或操作，获得结果A[i] | A[i+1] | ... | A[j]。
 * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
 *
 * 示例 1：
 * 输入：[1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-ors-of-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-28 14:47
 **/
public class Q898_BitwiseORSubarrays {
    public int subarrayBitwiseORs1(int[] arr) {
        Set<Integer> res = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            int or = 0;
            for(int j = i; j < arr.length; j++) {
                or |= arr[j];
                res.add(or);
            }
        }
        return res.size();
    }

    // TODO: 用一个集合 cur 存储以 j 为结尾的 result 值，即所有满足 i <= j 的 A[i] | ... | A[j] 的值。
    public static int subarrayBitwiseORs2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);
        for(int x : arr) {
            Set<Integer> s = new HashSet<>(); // 保存以当前元素为结尾的 或 值
            for(int pre : cur) s.add(x | pre);
            s.add(x);
            cur = s;
            set.addAll(cur);
        }
        return set.size();
    }

    // TODO: 通过记录A[i]前最大可以置位的bit，全部置位后即可跳出循环
    public static int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        int preOR = 0;
        for(int i = 0; i < n; i++) {
            int or = 0;
            for(int j = i; j >= 0; j--) {
                 or |= arr[j];
                 set.add(or);
                 if((preOR & or) == preOR) break;
            }
            preOR |= arr[i];
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4};
        System.out.println(subarrayBitwiseORs(arr));
    }
}
