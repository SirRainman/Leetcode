# 使用接口进行排序
## 对一维数组排序
## 对二维数组排序
```java
// 根据每个区间的起始点进行排序
Arrays.sort(intervals, new Comparator<int[]>() {
    @Override
    public int compare(int[] interval1, int[] interval2) {
        // TODO : 这里可以根据优先级进行相应的排序
        return interval1[0] - interval2[0];
    }
});
```
## 对list进行排序 
```java
list.sort(Comparator.comparingInt(x -> x));
```


# 快速排序
1. 确定分界点 n[left]， n[right]，n[random]
2. 调整顺序：分为两个区间，左区间的数都比分界点小，右区间的数都比分界点大
   1. 如何优雅的把区间划分为两部分？
3. 递归：递归处理左区间 / 右区间