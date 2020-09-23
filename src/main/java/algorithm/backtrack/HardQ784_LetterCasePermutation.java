package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 *
 * 输入：S = "12345"
 * 输出：["12345"]
 *
 *
 * 提示：
 *
 * S的长度不超过12。
 * S仅由数字和字母组成。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ784_LetterCasePermutation {
    List<String> ans = new ArrayList<>();
    char[] path;

    public List<String> letterCasePermutation(String S) {
        if (S.length() == 0) return ans;

        // path = new char[S.length()];
        // backtrack2(S, 0);
        path = S.toCharArray();
        backtrack(0);
        return ans;
    }

    public void backtrack(int curIndex) {
        // if (curIndex == path.length) {
        //     ans.add(new String(path));
        //     return;
        // }

        for (int i = curIndex; i < path.length; i++) {
            if (Character.isLetter(path[i])) {
                path[i] = (char) (path[i] ^ 1 << 5);
                backtrack(i + 1);
                path[i] = (char) (path[i] ^ 1 << 5);
            }
        }

        ans.add(new String(path));
    }


    public void backtrack2(String S, int curIndex) {
        if (curIndex == S.length()) {
            ans.add(new String(path));
            return;
        }
        // TODO:
        //  1.为什么这里不用for？？
        //  2.注意这里是两个选择，而不是一个选择
        //  3.大小写转换：大写字符与其对应的小写字符的 ASCII 的差为 32 = 2^5。在编程语言中，可以表示为 1 << 5。
        //      如果是大写字母，则第五位是0，异或之后变为1
        //      如果是小写字母，则第五位是1，异或之后变为0

        // 第一遍
        path[curIndex] = S.charAt(curIndex);
        backtrack2(S, curIndex + 1);

        // 第二遍
        if (Character.isLetter(S.charAt(curIndex))) {
            path[curIndex] = (char) (path[curIndex] ^ 1 << 5);
            backtrack2(S, curIndex + 1);
        }
    }
}
