package dataStructure.tree.level;

import dataStructure.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 示例:
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示:这与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明:不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q297_SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        TreeNode cur = root;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        while(!queue.isEmpty()) {
            cur = queue.poll();
            if(cur != null) {
                sb.append(cur.val + ",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                sb.append("#,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("#")) return null;
        String[] s = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for(int i = 1; i < s.length; i++) {
            TreeNode cur = queue.poll();
            if(!s[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(s[i]));
                cur.left = left;
                queue.offer(left);
            }
            i++;
            if(!s[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(s[i]));
                cur.right = right;
                queue.offer(right);
            }
        }
        return root;
    }



    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if(root == null) return "#";
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return buildTree(data_list);
    }

    public TreeNode buildTree(List<String> list) {
        if(list.get(0).equals("#")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = buildTree(list);
        root.right = buildTree(list);
        return root;
    }

}
