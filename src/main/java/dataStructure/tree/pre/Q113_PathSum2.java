package dataStructure.tree.pre;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明:叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q113_PathSum2 {
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target, 0, new LinkedList<>());
        return res;
    }

    private void dfs(TreeNode root, int target, int sum, List<Integer> path) {
        if(root == null) return;
        path.add(root.val);
        sum += root.val;
        if(root.left == null && root.right == null && sum == target) res.add(new LinkedList<>(path));
        dfs(root.left, target, sum, path);
        dfs(root.right, target, sum, path);
        path.remove(path.size() - 1);
    }
}
