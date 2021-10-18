package algorithm.AAAcontest.ms20210930;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: Leetcode
 * @description:
 * @author: Rain
 * @create: 2021-09-30 14:40
 **/
public class Main3 {
    private static class Pair {
        char c;
        int cnt;

        public Pair(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }

    private static void add(Deque<Pair> queue, char c) {
        if (queue.isEmpty() || queue.peekLast().c != c) queue.addLast(new Pair(c, 1));
        else queue.peekLast().cnt++;
    }

    private static int get(Deque<Pair> left, Deque<Pair> right) {
        Pair t = null;
        if (!left.isEmpty() && !right.isEmpty() && left.peekLast().c == right.peekFirst().c) {
            t = right.removeFirst();
            left.peekLast().cnt += t.cnt;
        }
        int res = get(left) + get(right);
        if (t != null) {
            right.addFirst(t);
            left.peekLast().cnt -= t.cnt;
        }
        return res;
    }

    private static int get(Deque<Pair> queue) {
        int res = 0;
        for (Pair p : queue) {
            res++;
            int t = p.cnt;
            while (t > 1) {
                res++;
                t /= 10;
            }
        }
        return res;
    }

    private static int solution(String S, int K) {
        char[] str = S.toCharArray();
        int n = str.length;
        Deque<Pair> left = new LinkedList<>();
        Deque<Pair> right = new LinkedList<>();
        for (int i = K; i < n; i++)
            add(right, str[i]);
        int min = get(right);
        for (int i = 0; i + K < n; i++) {
            add(left, str[i]);
            if (--right.peekFirst().cnt == 0) right.removeFirst();
            min = Math.min(min, get(left, right));
        }
        return min;
    }

    public static void main(String[] args) {
        String s = "ABCDDDEFG";
        int K = 2;
        System.out.println(solution(s, K));
    }
}