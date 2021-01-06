package dataStructure.tree.post;

import dataStructure.TreeNode;

/**
 * 给定两个非空二叉树 s 和 t，检验s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q572_SubTreeOfAnotherTree {

    // TODO: 注意这里是判断t是不是s的一个子树
    //  如何判断t是不是s的一个子结构？？？？QOffer26
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null || t == null ) return false;
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null) return q == null;
        if(q == null) return p == null;
        return p.val == q.val && isSameTree(p.left, q.left ) && isSameTree(p.right, q.right);
    }
}
