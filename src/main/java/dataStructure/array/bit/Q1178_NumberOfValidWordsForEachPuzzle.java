package dataStructure.array.bit;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Leetcode
 * @description: 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
 * 单词word中包含谜面puzzle的第一个字母。
 * 单词word中的每一个字母都可以在谜面puzzle中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；
 * 而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
 * <p>
 * 示例：
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为"absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为"actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为"gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * <p>
 * <p>
 * 提示：
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j]都是小写英文字母。
 * 每个puzzles[i]所包含的字符都不重复。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-02-26 11:13
 **/
public class Q1178_NumberOfValidWordsForEachPuzzle {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        // 每一个单词都有自己的mask码，不同的单词的mask码可以相同，通过map将每个单词的mask码记录下来
        Map<Integer, Integer> maskCountMap = new HashMap<>();
        for (String word : words) {
            int mask = getMask(word);
            maskCountMap.put(mask, maskCountMap.getOrDefault(mask, 0) + 1);
        }


        // 计算每个puzzles的mask码，并统计他的子集中，和子元素mask码相同的单词的mask码数
        for (String puzzle : puzzles) {
            int count = 0;
            int mask = getMask(puzzle);
            int first = puzzle.charAt(0) - 'a';
            for (int sub = mask; sub > 0; sub = mask & (sub - 1)) { //枚举mask的全部子集
                if (((sub >> first) & 1) == 1) {
                    count += maskCountMap.getOrDefault(sub, 0);
                }
            }
            res.add(count);
        }
        return res;
    }

    public static int getMask(String str) {
        char[] word = str.toCharArray();
        int mask = 0;
        for (char c : word) {
            mask |= 1 << (c - 'a');
        }
        return mask;
    }

    public static void print(int mask) {
        for (int i = 25; i >= 0; i--) {
            System.out.print((mask >> i) & 1);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String str = "abdz";
        int mask = getMask(str);
        for (int sub = mask; sub > 0; sub = mask & (sub - 1)) {
            print(sub);
        }
    }

    public void bitOperation() {
        //集合A、B，元素c --> int A,B   c = 0 ~ 31
        int A = 0, B = 0, c = 0;
        //A中插入c
        A |= (1 << c);
        //A中去除c
        A &= ~(1 << c);
        A ^= (1 << c);
        //A B 合并
        int merge = A | B;
        //判断B是不是A的子集
        boolean isSub = (A & B) == B;
        //判断c在不在A里
        int isContain = A & (1 << c);
        //lowbit
        int lowbit = c & (-c);
        //枚举A的全部子集
        for (int i = A; i > 0; i = (i - 1) & A) {
            //do something
        }
    }
}
