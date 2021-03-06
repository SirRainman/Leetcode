package dataStructure.map;

import java.util.*;

/**
 * 给你一个整数数组arr，请你帮忙统计数组中每个数的出现次数。
 *
 * 如果每个数的出现次数都是独一无二的，就返回true；否则返回 false。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 *
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 * 
 *
 * 提示：
 *
 * 1 <= arr.length<= 1000
 * -1000 <= arr[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q1207_UniqueNumberofOccurrences {
    public boolean uniqueOccurrences1(int[] arr) {
        if(arr == null || arr.length < 2) return true;
        int[] count = new int[1001];
        Arrays.sort(arr);


        int i = 0;
        while(i < arr.length) {
            int j = i+1;
            while(j < arr.length && arr[i] == arr[j]) {
                j++;
            }
            if(count[j-i] != 0) {
                return false;
            }
            count[j-i] = j-i;
            i = j;
        }

        return true;
    }

    // TODO:
    //  用 map 来统计 每个树的出现次数
    //  用set来统计每个次数是否出现过
    public boolean uniqueOccurrences2(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<>();
        for(int n : arr) {
            occur.put(n, occur.getOrDefault(n, 0) + 1);
        }
        Set<Integer> times = new HashSet<>();
        for(Map.Entry<Integer, Integer> entry : occur.entrySet()) {
            int value = entry.getValue();
            if(times.contains(value)) {
                return false;
            }
            times.add(value);
        }
        return true;
    }

    public boolean uniqueOccurrences(int[] arr) {
        int[] occur = new int[2010];
        boolean[] times = new boolean[2010];
        for(int n : arr) {
            occur[n+1000]++;
        }
        for(int time : occur) {
            if(time == 0) continue;
            if(times[time] == true) {
                return false;
            } else {
                times[time] = true;
            }
        }
        return true;
    }
}
