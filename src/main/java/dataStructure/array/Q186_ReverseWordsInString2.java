package dataStructure.array;

/**
 * @program: Leetcode
 * @description:
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例：
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 * 单词的定义是不包含空格的一系列字符
 * 输入字符串中不会包含前置或尾随的空格
 * 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用O(1) 额外空间复杂度的原地解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-31 14:07
 **/
public class Q186_ReverseWordsInString2 {
    public void reverseWords(char[] s) {
        int n = s.length;
        reverse(s, 0, n - 1);
        int begin = 0;
        for(int i = 1; i < n; i++) {
            if(s[i] == ' ') {
                reverse(s, begin, i - 1);
                begin = i + 1;
            }
        }
        reverse(s, begin, n - 1);
    }

    private void reverse(char[] s, int left, int right) {
        while(left < right) {
            char t = s[left];
            s[left] = s[right];
            s[right] = t;
            left++;
            right--;
        }
    }
}
