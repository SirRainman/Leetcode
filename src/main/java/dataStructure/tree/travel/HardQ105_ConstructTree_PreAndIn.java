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
        int n = preorder.length;
        Map<Integer, Integer> indexOfInOrder = new HashMap<>();
        for(int i = 0; i < n; i++) {
            indexOfInOrder.put(inorder[i], i);
        }
        return constructTree(preorder, inorder, 0, n - 1, 0, n - 1, indexOfInOrder);
    }

    // TODO:注意下标
    private TreeNode constructTree(int[] pre, int[] in, int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> indexOfInOrder) {
        if(preLeft > preRight || inLeft > inRight) return null;
        TreeNode root = new TreeNode(pre[preLeft]);
        int i = indexOfInOrder.get(pre[preLeft]);
        int leftCount = i - inLeft;
        root.left = constructTree(pre, in, preLeft + 1, preLeft + leftCount, inLeft, i - 1, indexOfInOrder);
        root.right = constructTree(pre, in, preLeft + leftCount + 1, preRight, i + 1, inRight, indexOfInOrder);
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
