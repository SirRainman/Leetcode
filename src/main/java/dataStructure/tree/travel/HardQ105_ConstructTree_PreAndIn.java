package dataStructure.tree.travel;

import dataStructure.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder =  [9,3,15,20,7]
 * 返回如下的二叉树：
 * 
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HardQ105_ConstructTree_PreAndIn {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexOfInorder = new HashMap<>();
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            indexOfInorder.put(inorder[i], i);
        }
        return constructTreeByPreAndIn(preorder, inorder, 0, len - 1, 0, len - 1, indexOfInorder);
    }

    // TODO:注意下标
    public TreeNode constructTreeByPreAndIn(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> indexOfInorder) {
        if (inLeft > inRight || preLeft > preRight) return null;
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = indexOfInorder.get(root.val);
        int leftCount = rootIndex - inLeft;
        root.left = constructTreeByPreAndIn(preorder, inorder, preLeft + 1, preLeft + leftCount, inLeft, rootIndex - 1, indexOfInorder);
        root.right = constructTreeByPreAndIn(preorder, inorder, preLeft + leftCount + 1, preRight, rootIndex + 1, inRight, indexOfInorder);
        return root;
    }

    // TODO：迭代方法太难了吧，我还没有掌握
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
