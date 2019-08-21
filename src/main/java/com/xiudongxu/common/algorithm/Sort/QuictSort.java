package com.xiudongxu.common.algorithm.Sort;

/**
 * @author dongxu.xiu
 * @since 2019-07-21 下午2:45
 */
public class QuictSort {

    private static int count;

    private static void QuickSort(int[] num, int left, int right){
        //如果left等于right 即数组只有一个元素的话 那就直接返回
        if(left >= right){
            return ;
        }
        //设置最左边的元素为基准值
        int key = num[left];
        //数组中比key小的放在左边，比key大的放在右边 key值下标为i
        int i = left;
        int j = right;
        while(i < j){
            //j向左移动 直到遇到比key小的值
            while(num[j] >= key && i < j){
                j--;
            }
            //i向右移动，直到遇到比key大的值
            while(num[i] <= key && i < j){
                i++;
            }
            //i和j指向的元素交换
            if(i < j){
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }

        num[left] = num[i];
        num[i] = key;
        count++;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }


}
