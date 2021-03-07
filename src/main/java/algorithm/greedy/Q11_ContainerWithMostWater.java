package algorithm.greedy;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-04 11:10
 **/
public class Q11_ContainerWithMostWater {
    // TODO：贪心
    //  哪边低，就移动哪边，因为移动高的肯定不能变得更大（高不变，宽度变小）
    public int maxArea(int[] height) {
        int maxVolume = 0;
        int left = 0, right = height.length - 1;
        while(left < right) {
            maxVolume = Math.max(maxVolume, Math.min(height[left], height[right]) * (right - left));
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxVolume;
    }
}
