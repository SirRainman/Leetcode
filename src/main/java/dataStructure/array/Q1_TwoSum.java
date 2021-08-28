package dataStructure.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * */

public class Q1_TwoSum {

    /**
     * 数组的方式
     */
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i , j};
                }
            }
        }

        return indices;
    }

    /**
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。
     * 如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     * */
    public int[] twoSum2(int[] nums, int target) {
        int[] indices = new int[2];

        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            // TODO: dataStructure.map.containsKey的时间复杂度是多少？？？
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return indices;
    }

    public static void main(String[] args) {
        Q1_TwoSum q = new Q1_TwoSum();
        System.out.println(Arrays.toString(q.twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }
}

