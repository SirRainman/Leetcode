package algorithm.AAAcontest.pdd0831;

import java.util.*;

/**
 * 设字符串 s 长为len
 * 已知：
 * 1. i 与 len - 1 - i 这两个位置上的字符可以换位
 * 2. s1 s2 在比较时，字典序较小的排在前面
 *
 * 问：输出多个字符串后，排序是什么样的
 */
public class Test3 {
    public static int compareTo(String str1, String str2) {
        return str1.compareTo(str2);
    }

    public static String swap(String str, int i, int j) {
        char[] strArray = str.toCharArray(); // 转换成数组
        char temp = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = temp;
        return String.valueOf(strArray);
    }

    private static String toMinString(String str) {
        char[] s = str.toCharArray();
        int len = str.length();
        int m = (len - 1) / 2;
        for (int i = 0; i <= m; i++) {
            if (s[i] > s[len - 1 - i]) {
                char temp = s[i];
                s[i] = s[len - 1 - i];
                s[len - 1 - i] = temp;
            }
        }
        return String.valueOf(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = sc.next();
        }

        Map<String, String> map = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(strs[i], toMinString(strs[i]));
            indexMap.put(strs[i], i);
        }

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // int len1 = o1.length();
                // for (int i = 0; i < len1 / 2; i++) {
                //     String temp = swap(o1, i, len1 - i - 1);
                //     if (compareTo(o1, temp) > 0) {
                //         o1 = temp;
                //     }
                // } //找到o1最小
                // o1 = toMinString(o1);

                // int len2 = o2.length();
                // for (int i = 0; i < len2 / 2; i++) {
                //     String temp = swap(o2, i, len2 - i - 1);
                //     if (compareTo(o2, temp) > 0) {
                //         o2 = temp;
                //     }
                // }//o2最小
                // o2 = toMinString(o2);

                // if (compareTo(o1, o2) < 0) {
                //     return -1;
                // } else if (compareTo(o2, o1) < 0) {
                //     return 1;
                // } else {
                //     return 0;
                // }

                String min1 = map.get(o1);
                String min2 = map.get(o2);
                if (min1.equals(min2)) {
                    return indexMap.get(o1) - indexMap.get(o2);
                } else {
                    return min1.compareTo(min2);
                }
            }
        });

        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }

    }
}