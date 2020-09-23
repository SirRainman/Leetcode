package dataStructure.tree.in.bst;

import dataStructure.TreeNode;

import java.util.*;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q653_TwoSumIV_InputisaBST {
    // TODO：当查看是否存在的时候要想到map 和 set
    Set<Integer> set = new HashSet<>();
    public boolean findTarget2(TreeNode root, int k) {
        if(root == null) return false;
        if(set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    // TODO：注意如何在有序数组里找到两数之和
    public boolean findTarget3(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while(p != null || !stack.isEmpty()) {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            nums.add(p.val);
            p = p.right;
        }

        int l = 0, r = nums.size() - 1;
        while(l < r) {
            int sum = nums.get(l) + nums.get(r);
            if(sum == k) return true;
            else if (sum < k) l++;
            else r--;
        }
        return false;
    }


    // TODO: 注意中序剪枝了
    Deque<Integer> stack = new LinkedList<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        boolean left = findTarget(root.left, k);
        if(root.val * 2 < k) {
            stack.push(root.val);
        } else {
            while(!stack.isEmpty() && stack.peek() + root.val > k) stack.pop();
            if(!stack.isEmpty() && stack.peek() + root.val == k) return true;
        }
        boolean right = findTarget(root.right, k);
        return left || right;
    }
}
