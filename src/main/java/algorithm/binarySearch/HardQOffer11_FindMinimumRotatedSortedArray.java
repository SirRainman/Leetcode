package algorithm.binarySearch;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQOffer11_FindMinimumRotatedSortedArray {
    // TODO: 排序数组的查找问题首先考虑使用 二分法 解决，其可将 遍历法 的 线性级别 时间复杂度降低至 对数级别 。
    //  根据旋转数组的定义，左半边的元素 >= 右半边的元素
    //  设x为右半边左边界
    //  nums[mid] > nums[right] 说明中间元素在 x左半边，mid肯定不是右半边的左边界 left = mid + 1
    //  nums[mid] < nums[right] 说明中间元素在 x右半边，mid可能是右半边的左边界 right = mid
    //  nums[mid] = nums[right] 这时不能确定中间元素在哪一个区间，right = right - 1 ???
    //      无法判定mid在左（右）排序数组，自然也无法通过二分法安全地缩小区间，因为其会导致旋转点 x 不在区间 [i,j] 内。
    //      示例一 [1, 0, 1, 1, 1]：旋转点 x = 1 ，因此 m = 2 在 右排序数组 中。
    //      示例二 [1, 1, 1, 0, 1]：旋转点 x = 3 ，因此 m = 2 在 左排序数组 中
    //          1. x < right，right = right - 1 没问题
    //          2. x = right，right = right - 1 会越过x
    //              1. 由于 nums[mid] = nums[right] = nums[x] <= nums[left]
    //              2. 又因为 left <= mid < right，因此 mid < right = x，即此时 mid 一定在左排序数组中，因此 nums[mid] ≥ nums[left]
    //              3. 则有nums[left] = nums[mid] ，且区间 [left, mid] 内所有元素值相等
    //              4. 此时，执行right-- 后虽然丢失了旋转点 x ，
    //                  但之后区间 [left, right] 只包含左排序数组，二分下去返回的一定是本轮的 nums[left] ，
    //                  而nums[left] 与 nums[x] 相等。
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(numbers[mid] > numbers[right]) left = mid + 1;
            else if(numbers[mid] < numbers[right]) right = mid;
            else right--;
        }
        return numbers[left];
    }
}
