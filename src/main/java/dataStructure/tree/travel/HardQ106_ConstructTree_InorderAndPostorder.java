package dataStructure.tree.travel;

import dataStructure.TreeNode;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder =   [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ106_ConstructTree_InorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return constructTreeByInAndPost(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }

    // TODO：注意下标
    public TreeNode constructTreeByInAndPost(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if(inLeft > inRight || postRight < postLeft) return null;
        TreeNode root = new TreeNode(postorder[postRight]);
        int rootIndex = findRootInNums(inorder, inLeft, inRight, root.val);
        int leftCount = rootIndex - inLeft;
        root.left = constructTreeByInAndPost(inorder, postorder, inLeft, rootIndex-1, postLeft, postLeft+leftCount-1);
        root.right = constructTreeByInAndPost(inorder, postorder, rootIndex+1, inRight, postLeft+leftCount, postRight-1);
        return root;
    }

    // TODO：用map替代可以加快速度
    public int findRootInNums(int[] inorder, int left, int right, int rootVal) {
        int rootIndex = inorder[left];
        for(int i = left; i <= right; i++) {
            if(inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        return rootIndex;
    }


    // TODO：方法二：迭代方法需要注意
}
