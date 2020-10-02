import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Test {

    public int minOperations(String[] logs) {
        Deque<String> stack = new LinkedList<>();
        for(String op : logs) {
            if(op.equals("../")) {
                // 判断是否在主目录
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else if(op.equals("./")) {
                // do nothing
            } else {
                // 进入子目录
                stack.push(op);
            }
        }

        return stack.size();
    }
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int profit = 0;
        int waiting = 0;
        for(int i = 0; i < customers.length; i++) {
            int n = customers[i];
            if(n + waiting >= 4) {
                // 上四个人
                waiting = n + waiting - 4;
                profit = 4 * boardingCost - runningCost;
            } else {
                profit = (n + waiting) * boardingCost - runningCost;
                waiting = 0;
            }
        }
        // while(waiting > 0) {
        //     if(waiting >= 4) {
        //         profit = 4 * boardingCost - runningCost;
        //         waiting -= 4;
        //     } else {
        //         profit = waiting * boardingCost - runningCost;
        //     }
        // }
        if(waiting > 0) {
            int times = (int) Math.ceil(waiting / 4);
            profit = waiting * boardingCost - times * runningCost;
        }
        return profit > 0 ? profit : -1;
    }
}
