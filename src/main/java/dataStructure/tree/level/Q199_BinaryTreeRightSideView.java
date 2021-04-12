package dataStructure.tree.level;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: Leetcode
 * @description:
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 * 输入:[1,2,3,null,5,null,4]
 * 输出:[1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-03-29 19:24
 **/
public class Q199_BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 1);
        return res;
    }

    public void dfs(List<Integer> res, TreeNode root, int depth) {
        if(root == null) return ;
        if(depth > res.size()) res.add(root.val);
        dfs(res, root.right, depth + 1);
        dfs(res, root.left, depth + 1);
    }

    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.offer(cur);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                cur = queue.poll();
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer((cur.right));
                if(size == 0) res.add(cur.val);
            }
        }
        return res;
    }
}
