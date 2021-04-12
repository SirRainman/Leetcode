package algorithm.double_pointer.slidingWindow;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @program: Leetcode
 * @description:
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得nums [i] 和nums [j]的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * 如果存在则返回 true，不存在返回 false。
 *
 * 示例1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-30 11:40
 **/
public class Q220_ContainsDuplicate3 {
    // TODO：超时
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        int left = 0, right = 0;
        while(right < n) {
            if(check(nums, left, right, t)) return true;
            if(right - left >= k) {
                left++;
            }
            right++;
        }
        return false;
    }

    private boolean check(int[] nums, int left, int right, int t) {
        for(int i = left; i < right; i++) {
            if(Math.abs((long)nums[right] - nums[i]) <= t) return true;
        }
        return false;
    }

    // TODO: 二叉排序树，在窗口中维护一个有序数组
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        int left = 0, right = 0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        while(right < n) {
            Integer next = treeSet.ceiling(nums[right]);
            if(next != null && (long)next - nums[right] <= t) return true;
            Integer pre = treeSet.floor(nums[right]);
            if(pre != null && (long)nums[right] - pre <= t) return true;
            treeSet.add(nums[right]);
            while(right - left >= k) {
                treeSet.remove(nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }


    // TODO:
    //  设计一些桶，让他们分别包含区间 ..., [0,t], [t+1, 2t+1], ......,[0,t],[t+1,2t+1],...。
    //      1.把桶来当做窗口，于是每次只需要检查 x 所属的那个桶和相邻桶中的元素就可以了。
    //      2.桶里只需要包含最多一个元素就可以了，因为如果任意一个桶中包含了两个元素，那么这也就是意味着这两个元素是 足够接近的 了，
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        HashMap<Long, Long> bucket = new HashMap<>();
        long size = (long) t + 1;
        int left = 0, right = 0;
        while(right < n) {
            long id = getBucketID(size, nums[right]);
            if(bucket.containsKey(id)) return true;
            if(bucket.containsKey(id - 1) && Math.abs(bucket.get(id - 1) - nums[right]) <= t) return true;
            if(bucket.containsKey(id + 1) && Math.abs(bucket.get(id + 1) - nums[right]) <= t) return true;
            bucket.put(id, (long)nums[right]);
            if(right - left >= k) {
                bucket.remove(getBucketID(size, nums[left]));
                left++;
            }
            right++;
        }
        return false;
    }

    private long getBucketID(long size, long num) {
        // Get the ID of the bucket from element value x and bucket width w
        // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
        return num < 0 ? (num + 1) / size - 1 : num / size;
    }
}