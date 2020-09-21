import java.util.Arrays;

public class Test {

    @org.junit.Test
    public static void aaa(String[] args) {
        maxSumRangeQuery(new int[] {1,2,3,4,5}, new int[][] {{1, 3}, {0, 1}});
    }

    public static int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(nums);
        int[] times = new int[nums.length];
        for(int i = 0; i < requests.length; i++) {
            for(int j = requests[i][0]; j <= requests[i][1]; j++) {
                times[j]++;
                System.out.println();
            }
        }
        Arrays.sort(times);
        int sum = 0;
        int p = nums.length-1;
        for(int i = nums.length-1; i >= 0; i--) {
            if(times[i] > 0) {
                sum += times[i] * nums[p];
                sum = sum % (10^9 + 7);
                System.out.println(nums[p]);
                p--;
            }
        }

        return sum;
    }
}
