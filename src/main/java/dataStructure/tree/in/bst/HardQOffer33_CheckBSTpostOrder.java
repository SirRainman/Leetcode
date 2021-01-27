package dataStructure.tree.in.bst;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 提示：
 * 数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQOffer33_CheckBSTpostOrder {
    public boolean verifyPostorder1(int[] postorder) {
        return check(postorder, 0, postorder.length - 1);
    }

    public boolean check(int[] postorder, int i, int j) {
        if(i >= j) return true;
        // 从左向右找，找到左子树的分界点
        int p = i;
        while(p < j && postorder[p] < postorder[j]) p++;
        int mid = p;
        // 继续向右找，判断一下是否右边的都比跟节点大
        while(p < j && postorder[p] > postorder[j]) p++;
        return p == j && check(postorder, i, mid - 1) && check(postorder, mid, j - 1);
    }

    // TODO:
    //  后续的遍历顺序 left -> right -> root
    //  反过来看 arr[] = root -> right -> left
    //  如果arr[i] < arr[i+1]，
    //      那么arr[i+1]一定是arr[i]的右子节点
    //  如果arr[i] > arr[i+1]，
    //      那么arr[i+1]一定是arr[0]……arr[i]中某个节点的左子节点，并且这个值是大于arr[i+1]中最小的。
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> stack = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) { //注意for循环是倒叙遍历的
            // 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if(postorder[i] > root) return false;
            //当如果前节点小于栈顶元素，说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
            while(!stack.isEmpty() && postorder[i] < stack.peek()) root = stack.pop();
            stack.push(postorder[i]);
        }
        return true;
    }
}
