package tree;

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
    int curCount = 0, maxCount = 0;
    int resSize = 0;
    TreeNode pre = null;

    public int[] findMode(TreeNode root) {
        // 第一遍确定共有几个众数
        inOrder(root);
        pre = null;
        ans = new int[resSize];
        curCount = 0;
        resSize = 0;
        // 第二遍往数组里面添加元素
        inOrder(root);

        return ans;
    }

    public void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        if(pre != null && pre.val == root.val) {
            curCount++;
        } else {
            curCount = 1;
        }
        if(curCount > maxCount) {
            maxCount = curCount;
            resSize = 1;
        } else if(curCount == maxCount) {
            if(ans != null) {
                ans[resSize] = root.val;
            }
            resSize++;
        }
        pre = root;
        inOrder(root.right);
    }

    error;
}
