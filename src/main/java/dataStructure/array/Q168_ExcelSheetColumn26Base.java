package dataStructure.array;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * 例如，
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB 
 *     ...
 * 示例 1:
 *
 * 输入: 1
 * 输出: "A"
 *
 * 示例2:
 * 输入: 28
 * 输出: "AB"
 *
 * 示例3:
 * 输入: 701
 * 输出: "ZY"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q168_ExcelSheetColumn26Base {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while(n > 0) {
            // TODO：想一想为什么要减一？？
            //  因为 Excel 取值范围为 1~26，故可将 26 进制 逻辑上的 个位、十位、百位…均减 1 映射到 0~25 即可，最后转换为字符
            n--;
            sb.append((char)('A' + n % 26));
            n /= 26;
        }
        return sb.toString();
    }
}
