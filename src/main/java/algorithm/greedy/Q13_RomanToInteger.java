package algorithm.greedy;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-10 20:19
 **/
public class Q13_RomanToInteger {
    public int romanToInt(String s) {
        char[] str = s.toCharArray();
        int num = 0, n = str.length;
        for(int i = 0; i < n; i++) {
            if(i < n - 1 && getValue(str[i]) < getValue(str[i + 1]) ) {
                num -= getValue(str[i]);
            } else {
                num += getValue(str[i]);
            }
        }
        return num;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
