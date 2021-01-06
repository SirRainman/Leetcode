package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *   2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 * 
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QOffer54_KthLargestElementInBST {
    // TODO: 类似二分的思想
    public int kthLargest1(TreeNode root, int k) {
        if(k == 0) return root.val;
        int c = count(root.right);
        if(k == c + 1) return root.val;
        else if(k < c + 1) return kthLargest(root.right, k);
        else return kthLargest(root.left, k - c - 1);
    }
    public int count(TreeNode root) {
        if(root == null) return 0;
        return count(root.left) + count(root.right) + 1;
    }

    // TODO：利用二叉搜索树的特性
    int res = 0, count = 0;
    public int kthLargest(TreeNode root, int k) {
        count = k;
        inOrder(root);
        return res;
    }
    public void inOrder(TreeNode root) {
        if(root == null) return ;
        inOrder(root.right);
        if(--count == 0) {
            res = root.val;
            return ;
        }
        inOrder(root.left);
    }
}
