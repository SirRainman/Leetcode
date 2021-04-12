package dataStructure.array;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例1：
 * 输入：[3,2,3]
 * 输出：3
 *
 * 示例2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * 
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q169_MajorityElement {
    // TODO：排序
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement(int[] nums) {
        return quickSearch(nums, 0, nums.length - 1, nums.length / 2);
    }

    // TODO：复杂度分析
    //  要找下标为k的元素，第一次切分的时候需要遍历整个数组(0 ~ n)找到了下标是j的元素，
    //  假如k比j小的话，那么我们下次切分只要遍历数组(0~k-1)的元素就行，
    //  反之如果k比j大的话，那下次切分只要遍历数组(k+1～n)的元素就行，
    //  总之可以看作每次调用partition遍历的元素数目都是上一次遍历的1/2，
    //  因此时间复杂度是N + N/2 + N/4 + ... + N/N = 2N, 因此时间复杂度是O(N)。
    public int quickSearch(int[] nums, int left, int right, int K) {
        int p = partition(nums, left, right);
        if(p == K) return nums[K];
        return p > K ? quickSearch(nums, left, p - 1, K) : quickSearch(nums, p + 1, right, K);
    }

    //  快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition1(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    // TODO: 为什么这种做法这么慢！！！是哪一个测试用例卡住了？？？？
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while(left < right) {
            while(left < right && pivot <= nums[right]) right--;
            nums[left] = nums[right];
            while(left < right && pivot >= nums[left]) left++;
            nums[right] = nums[left];

        }
        nums[left] = pivot;
        return left;
    }

    // TODO: 摩尔投票法
    //  出现 n / 3 怎么办？
    public int majorityElement3(int[] nums) {
        int card = 0, votes = 0;
        for(int x : nums) {
            if(votes == 0) card = x;
            votes += x == card ? 1 : -1;
        }
        return card;
    }
}
