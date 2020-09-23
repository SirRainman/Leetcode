package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q530_MinimumAbsoluteDifferenceinBST {
    public int getMinimumDifference2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root, pre = null;
        int min = Integer.MAX_VALUE;
        while(p != null || !stack.isEmpty()) {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if(pre != null) min = Math.min(min, Math.abs(pre.val - p.val));
            pre = p;
            p = p.right;
        }
        return min;
    }

    TreeNode pre = null;
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return min;
    }

    public void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        if(pre != null) min = Math.min(min, Math.abs(pre.val - root.val));
        pre = root;
        inOrder(root.right);
    }
}
