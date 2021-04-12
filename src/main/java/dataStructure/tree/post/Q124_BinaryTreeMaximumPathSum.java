package dataStructure.tree.post;

import dataStructure.TreeNode;

/**
 * @program: Leetcode
 * @description:
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * 
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-26 15:36
 **/
public class Q124_BinaryTreeMaximumPathSum {

    private int res = Integer.MIN_VALUE;

    // TODO: 对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
    //  1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
    //  2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
    public int maxPathSum(TreeNode root) {
        getMaxPathSum(root);
        return res;
    }

    // 返回：其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
    public int getMaxPathSum(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(0, getMaxPathSum(root.left));
        int right = Math.max(0, getMaxPathSum(root.right));
        // 判断：左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
        res = Math.max(res, root.val + left + right);
        return root.val + Math.max(left, right);
    }
}
