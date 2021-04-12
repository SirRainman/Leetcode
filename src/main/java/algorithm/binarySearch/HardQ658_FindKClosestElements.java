package algorithm.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 给定一个排序好的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length<= 104
 * 数组里的每个元素与x 的绝对值不超过 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-closest-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-30 19:44
 **/
public class HardQ658_FindKClosestElements {
    // TODO: 通过删除节点的方法留下来要确定的节点
    //  重点看下面的二分的方法
    public List<Integer> findClosestElements1(int[] nums, int k, int x) {
        int n = nums.length;
        int removeNums = n - k, left = 0, right = n - 1;
        while(removeNums-- > 0) {
            if(Math.abs(nums[left] - x) <= Math.abs(nums[right] - x)) right--;
            else left++;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = left; i <= right; i++) res.add(nums[i]);
        return res;
    }

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // TODO: 尝试从长度为 k + 1 的连续子区间删除一个元素,从而定位左区间端点的边界值
        //  每次移动的都是区间
        int left = 0, right = nums.length - k;
        while(left < right) {
            int mid = left + right >> 1;
            if(x - nums[mid] <= nums[mid + k] - x) right = mid;
            else left = mid + 1;
        }
        for(int i = left; i < left + k; i++) res.add(nums[i]);
        return res;
    }
}
