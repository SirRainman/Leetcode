package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *     / \
 *    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *     根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q98_ValidateBinarySearchTree {

    // TODO：二叉搜索树就是一个有序的列表，直接判断他是否是一个有序的列表就可以判定了
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(!isValidBST(root.left)) return false;
        if(pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }
}
