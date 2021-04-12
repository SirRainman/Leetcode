package dataStructure.array.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Leetcode
 * @description:
 * 不使用运算符+ 和-，计算两整数a、b之和。
 *
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Rain
 * @create: 2021-04-02 09:50
 **/
public class Q371_SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int carry = a & b, xor = a ^ b;
        while(carry != 0) {
            int t_xor = (carry << 1) ^ xor;
            int t_carry = (carry << 1) & xor;
            xor = t_xor;
            carry = t_carry;
        }
        return xor;
    }

    public static void main(String[] args) {
        System.out.println(new Q371_SumOfTwoIntegers().getSum(-5, -8));
    }


    class LRUCache {
        private class Node {
            int key, value;
            Node prev, next;

            public Node() {}

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        private int capacity;
        private Map<Integer, Node> cache;
        private Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if(node == null) return -1;
            moveToHead(node);
            return node.value;
        }

        private void moveToHead(Node node) {
            deleteNode(node);
            addToHead(node);
        }


        private void deleteNode(Node node) {
            Node pre = node.prev, next = node.next;
            pre.next = node.next;
            next.prev = node.prev;
            node = null;
        }

        private void addToHead(Node node) {
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if(node == null) {
                node = new Node(key, value);
                addToHead(node);
                if(cache.size() == capacity) {
                    cache.remove(tail.prev.key);
                    deleteTail();
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
            cache.put(key, node);
        }

        private void deleteTail() {
            deleteNode(tail.prev);
        }
    }
}
