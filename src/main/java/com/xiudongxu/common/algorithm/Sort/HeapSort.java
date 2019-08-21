package com.xiudongxu.common.algorithm.Sort;

/**
 * @author dongxu.xiu
 * @since 2019-07-13 下午4:06
 */
public class HeapSort {


    /**
     * [4, 10, 3, 5, 1, 2]
     * [10, 5, 8, 3, 4, 6, 7, 1, 2]
     * <p>
     * 原理 就是 在子树上 进行选择最大值的操作
     * 然后把最大值 搞到parent 上
     * <p>
     * parent = (i -1) / 2
     * c1 = 2i + 1
     * c2 = 2i + 2
     * <p>
     * <p>
     * 如果是一个全部无序的树 搞成一个堆的话  就从 第 h-1 层来处理。
     * <p>
     * <p>
     * arr 是源数据
     * n : 总量
     * i : 对第几个元素进行 heapify
     */
    public static void heapify(int[] tree, int n, int i) {
        //出口   必须要一个n 来防止出界的  i 是当前值 是为了后面循环变量用的
        if(i >= n){
            return ;
        }

        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;

        //找到最大值
        if (c1 < n && tree[c1] > tree[max]) {
            max = c1;
        }
        if (c2 < n && tree[c2] > tree[max]) {
            max = c2;
        }

        //对max 进行 heapify
        if (max != i) {
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    private static void build_heap(int tree[], int n){
        int last_node = n - 1;
        int parent = (last_node - 1) / 2;
        for (int i = parent; i >= 0 ; i--) {
            heapify(tree , n ,i);
        }
    }

    private static void heap_sort(int[] tree, int n){
        build_heap(tree, n);
        for (int i = n - 1;i >= 0 ; i++) {
            swap(tree, i, 0);
            heapify(tree, i, 0);
        }
    }
    private static void swap(int[] tree, int i, int j) {
        int temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    public static void main(String[] args) {
//        int[] tree = {4, 10, 3, 5, 1, 2};
//        int size = 6;
//        heapify(tree, size, 0);

        int[] tree = {2, 5, 3, 1, 10, 4};
        int size = 6;
        build_heap(tree, size);

        for (int i = 0; i < tree.length; i++) {
            System.out.println(tree[i]);
        }
    }

}
