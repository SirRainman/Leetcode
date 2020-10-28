package dataStructure.tree.post;

import dataStructure.TreeNode;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class HardQ671_SecondMinimumNodeInABinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        return findMin(root, root.val);
    }

    // TODO:
    //  问题可以转化为求左右子树的最小值
    //  前提：每个节点的值都已经是左右子树中最小的值了
    //      如果当前节点的值比最小值大，说明该节点的子树中不可能有值比该节点小，即找到比最小值大的值了，返回
    //      如果当前的值等于最小的值，说明第二小的值可能在这个节点的子树中
    //  注：只有最小的值所在的节点才能走到叶子节点
    public int findMin(TreeNode root, int min) {
        if(root == null) return -1;
        if(root.val > min) return root.val;
        int l = findMin(root.left, min);
        int r = findMin(root.right, min);
        if(l == -1) return r;
        if(r == -1) return l;
        return Math.min(l, r);
    }
}
