package dataStructure.string;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-03-24 14:07
 **/
public class Q125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] S = s.toCharArray();
        int left = 0, right = S.length - 1;
        while(left < right) {
            if(isValid(S[left]) && isValid(S[right])) {
                if(getValid(S[left]) != getValid(S[right])) return false;
                right--;
                left++;
            }
            if(!isValid(S[left])) left++;
            if(!isValid(S[right]))right--;
        }
        return true;
    }

    public boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public int getValid(char c) {
        return (c >= 'A' && c <= 'Z') ? c + 32 : c;
    }
}
