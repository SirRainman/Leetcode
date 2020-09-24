package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q501_FindModeinBinarySearchTree {

    int[] ans;
    int size = 0;
    int count = 0;
    int maxCount = 0;
    TreeNode pre = null;

    public int[] findMode(TreeNode root) {

        // 第一遍确定 数组的大小 + 众数的个数
        inOrder(root);

        ans = new int[size];
        size = 0;
        count = 0;
        pre = null;

        // 第二遍根据第一遍提供的众数的个数 往数组里填数
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);

        if(pre != null && root.val == pre.val) {
            count++;
        } else{
            count = 1;
        }

        if(count > maxCount) {
            maxCount = count;
            // TODO: 更新众数的数目
            size = 1;
        } else if(count == maxCount) {
            if(ans != null ) {
                ans[size] = root.val;
            }
            size++;
        }
        pre = root;

        inOrder(root.right);
    }
}
