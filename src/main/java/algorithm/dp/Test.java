package algorithm.dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-09 11:27
 **/
public class Test {
    static int n;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(list, Collections.reverseOrder());
        for(int i : list) System.out.println(i);
    }
}
