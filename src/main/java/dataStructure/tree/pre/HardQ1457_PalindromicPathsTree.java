package dataStructure.tree.pre;

import dataStructure.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一棵二叉树，每个节点的值为 1 到 9 。
 * 我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 * 请你返回从根到叶子节点的所有路径中伪回文路径的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ1457_PalindromicPathsTree {
    int count = 0;
    public int pseudoPalindromicPaths1 (TreeNode root) {
        dfs(root, new HashSet<>());
        return count;
    }

    // TODO: 用set存储
    //  偶数次从set移除
    //  奇数次往set添加
    //  只有set.SIZE() 在0-1之间才是回文
    public void dfs(TreeNode root, Set<Integer> path) {
        if(root == null) return ;
        if(root.left == null && root.right == null ) {
            if(path.size() == 0 || (path.size() <= 2 && path.contains(root.val))) count++;
            return;
        }

        if(path.contains(root.val)) path.remove(root.val);
        else path.add(root.val);

        dfs(root.left, path);
        dfs(root.right, path);

        if(path.contains(root.val)) path.remove(root.val);
        else path.add(root.val);
    }

    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root, 0);
        return count;
    }

    // TODO: 这个有点秀
    //  设path = nums[0, 0, 0, 0, 0, 0, 0, 0, 0]
    //  nums[9] 代表9出现的次数
    //  现在只需要判断1-9这九个数字是否出现了奇数次，所以只需要存0或1就可以，通过异或解决存储判断的问题
    //      (1 << root.val); 表示root.val 在第几位上出现
    //      path ^ path 表示该位置代表的数字出现的奇数或者是偶数
    //      (path & (path - 1)) == 0 判断每个位置上的数字是否全部都为1
    public void dfs(TreeNode root, int path) {
        if(root == null) return ;
        path = path ^ (1 << root.val); // node节点的val为几就左移几位
        if(root.left == null && root.right == null) {
            if(path == 0 || (path & (path - 1)) == 0) count++;
            return ;
        }
        dfs(root.left, path);
        dfs(root.right, path);
    }
}
