package dataStructure.string;

import java.util.Arrays;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * 
 *
 * 提示:
 * 0 < nums.length <= 100
 *
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer45_SortString {
    public String minNumber1(int[] nums) {
        String[] str = new String[nums.length];
        for(int i = 0; i < nums.length; i++) str[i] = String.valueOf(nums[i]);

        // TODO: 注意字符串的排序方法
        Arrays.sort(str, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < nums.length; i++) sb.append(str[i]);
        return sb.toString();
    }

    public String minNumber(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        StringBuffer sb = new StringBuffer();
        for(int x : nums) sb.append(x);
        return sb.toString();
    }

    public void quickSort(int[] nums, int left, int right) {
        if(left >= right) return;
        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while(left < right) {
            while(left < right && smaller(pivot, nums[right])) right--;
            nums[left] = nums[right];
            while(left < right && smaller(nums[left], pivot)) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    public boolean smaller(int a, int b) {
        String sa = String.valueOf(a);
        String sb = String.valueOf(b);
        return (sa + sb).compareTo(sb + sa) <= 0;
    }
}
