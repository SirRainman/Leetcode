package dataStructure.tree.trie;

import java.util.*;

/**
 * @program: Leetcode
 * @description:
 * 给一非空的单词列表，返回前k个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 * 
 * 注意：
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 *
 * 扩展练习：
 * 尝试以O(n log k) 时间复杂度和O(n) 空间复杂度解决。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-05-21 11:30
 **/
public class Q692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>((o1, o2) -> {
            if (map.get(o1) == map.get(o2)) return o2.compareTo(o1);
            else return map.get(o1) - map.get(o2);
        });
        for (String w : map.keySet()) {
            heap.offer(w);
            if(heap.size() > k) heap.poll();
        }
        List<String> res = new ArrayList<>(k);
        while (heap.size() > 0) {
            res.add(heap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
