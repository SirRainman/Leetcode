package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 给定一个整数 n，生成所有由 1 ...n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 * 输入：3
 * 输出：
 * [
 *  [1,null,3,2],
 *  [3,2,null,1],
 *  [3,1,null,null,2],
 *  [2,1,3],
 *  [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 
 *
 * 提示：
 * 0 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-30 17:20
 **/
public class Q95_UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new LinkedList<>();
        return buildBST(1, n);
    }

    // TODO:表示当前值的集合为 [start,end]，返回序列 [start,end] 生成的所有可行的二叉搜索树。
    //  枚举 [start,end] 中的值 i 为当前二叉搜索树的根，那么序列划分为了 [start,i−1] 和 [i+1,end] 两部分。
    public List<TreeNode> buildBST(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if(start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for(int i = start; i <= end; i++) {
            List<TreeNode> left = buildBST(start, i - 1);
            List<TreeNode> right = buildBST(i + 1, end);
            for(TreeNode l : left) {
                for(TreeNode r : right) {
                    TreeNode root = new TreeNode(i, l, r);
                    allTrees.add(root);
                }
            }
        }
        return allTrees;
    }
}
