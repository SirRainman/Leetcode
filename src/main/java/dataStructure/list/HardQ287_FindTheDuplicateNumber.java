package dataStructure.list;

/**
 * 给定一个包含n + 1 个整数的数组nums ，其数字都在 1 到 n之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 *
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 *
 * 提示：
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * 
 *
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以在不修改数组 nums 的情况下解决这个问题吗？
 * 你可以只用常量级 O(1) 的额外空间解决这个问题吗？
 * 你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ287_FindTheDuplicateNumber {
    // TODO：map做法，只不过是把nums数组本身当作map了
    public int findDuplicate1(int[] nums) {
        for(int i = 0; i < nums.length; i++) nums[i]--;
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] != i) {
                if(nums[nums[i]] == nums[i]) return nums[i] + 1;
                else swap(nums, i, nums[i]);
            }
        }
        for(int x : nums) System.out.print(x + " ");
        return -1;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    // TODO: 二分
    //  mid 是可能出现的重复的数
    //  count[i] 是nums数组中小于等于mid的数的个数，count具有单调性
    //      如果count <= mid 说明重复的元素大于mid
    //      如果count > mid 说明重复的元素小于mid
    public int findDuplicate2(int[] nums) {
        int len = nums.length, n = len - 1;
        int left = 1, right = n;
        while(left < right) {
            int mid = left + right >> 1;
            int count = 0;
            for(int i = 0; i < len; i++) {
                if(nums[i] <= mid) count++;
            }
            if(count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // TODO：二进制，统计每一位上1出现的次数，如果比正常的多说明是重复数字造成的
    public int findDuplicate3(int[] nums) {
        int[] bitCount = new int[32];
        int[] normalCount = new int[32];
        for(int x : nums) {
            countBit(bitCount, x);
        }
        for(int n = 1; n <= nums.length - 1; n++) {
            countBit(normalCount, n);
        }
        int res = 0;
        for(int i = 0; i < 32; i++) {
            if(bitCount[i] > normalCount[i]) {
                res += (1 << i);
            }
        }
        return res;
    }

    public void countBit(int[] bitCount, int x) {
        int cur = 0;
        while(x > 0) {
            bitCount[cur++] += x & 1;
            x = x >> 1;
        }
    }

    // TODO: 怎么就能想到环形链表的呢？？
    //  我们对 nums 数组建图，每个位置 i 连一条 i → nums[i] 的边。
    //  由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，
    //  且我们要找到的 target 就是这个环的入口，那么整个问题就等价于 Q142.环形链表 II。
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
