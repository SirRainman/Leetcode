package algorithm.binarySearch;

import dataStructure.TreeNode;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
 *
 * 示例:
 *
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ222_CountCompleteTreeNodes {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    int count = 0;

    public int depth(TreeNode root) {
        int depth = 0;
        TreeNode cur = root;
        while (cur != null) {
            depth++;
            cur = cur.left;
        }
        return depth;
    }

    public int countNodes2(TreeNode root) {

        TreeNode cur = root;
        while (cur != null) {
            int leftDepth = depth(cur.left);
            int rightDepth = depth(cur.right);
            if (leftDepth == rightDepth) { // TODO: 如果左右的高度相同，说明左边的树一定是满的
                count += Math.pow(2, leftDepth); // 2 ^ d - 1 之所以没有减1是因为算上了当前的跟节点
                cur = cur.right;
            } else { // TODO: 如果不相同说明，右边的一定是满的
                count += Math.pow(2, rightDepth);
                cur = cur.left;
            }
            //System.out.println(leftDepth + " " + rightDepth);
        }
        return count;
    }










    // TODO：判断第d层是否存在第idx个节点
    public boolean isExist(TreeNode root, int d, int idx) {
        int left = 0, right = (int) Math.pow(2, d) - 1;
        int pivot = 0;
        TreeNode cur = root;
        while (d-- > 0) {
            pivot = left + (right - left) / 2;
            // 判断idx在左右哪一个半区
            if (idx <= pivot) {
                right = pivot;
                cur = cur.left;
            } else {
                left = pivot + 1;
                cur = cur.right;
            }
        }
        return cur != null;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        // 拿到左子树的高度
        int d = depth(root.left);
        if (d == 0) return 1;
        // 最后一行的节点从0 到 2^d-1
        int left = 1, right = (int) Math.pow(2, d) - 1;
        int pivot;

        // 二分搜索
        while (left <= right) {
            pivot = left + (right - left) / 2;
            // 判断pivot是否在最后一行出现
            if (isExist(root, d, pivot)) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
            //System.out.println(pivot);
        }
        return (int) Math.pow(2, d) - 1 + left;
    }

}
