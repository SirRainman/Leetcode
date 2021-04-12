package dataStructure.stack;

/**
 * @program: Leetcode
 * @description:
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
 * 之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *
 * 提示：
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-27 14:38
 **/
public class Q1047_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String S) {
        char[] str = S.toCharArray();
        int n = str.length, top = -1;
        char[] stack = new char[n];
        for(int i = 0; i < n; i++) {
            if(top > -1 && stack[top] == str[i]) top--;
            else stack[++top] = str[i];
        }
        StringBuilder sd = new StringBuilder();
        for(int i = 0; top > -1 && i <= top; i++) sd.append(stack[i]);
        return sd.toString();
    }
}
