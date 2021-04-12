package dataStructure.array;

/**
 * @program: Leetcode
 * @description:
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-word-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-30 15:41
 **/
public class Q243_ShortestWordDistance {
    // TODO:
    //  记录两个下标 i1 和 i2 来显著提高暴力的时间复杂度，我们保存 word1 和 word2 的 最近 出现位置。
    //  每次我们发现一个新的单词出现位置，我们不需要遍历整个数组去找到另一个单词，因为我们已经记录了最近出现位置的下标。
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int p1 = -1, p2 = -1, res = 10000;
        for(int i = 0; i < wordsDict.length; i++) {
            if(wordsDict[i].equals(word1)) {
                p1 = i;
                if(p2 >= 0) res = Math.min(res, i - p2);
            } else if(wordsDict[i].equals(word2)) {
                p2 = i;
                if(p1 >= 0) res = Math.min(res, i - p1);
            }
        }
        return res;
    }
}
