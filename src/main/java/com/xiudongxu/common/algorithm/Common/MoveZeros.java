package com.xiudongxu.common.algorithm.Common;

/**
 * @author dongxu.xiu
 * @since 2019-07-24 上午10:27
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 12};
        moveZeros0(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static int moveZeros0(int[] arr){
        int i = 0; //记录0 的个数

        for (int j = 0; j < arr.length; j++) {
            if(arr[j] != 0){
                arr[i++] = arr[j];
            }
        }

        for (int k = i; k < arr.length; k++) {
            arr[k] = 0;
        }
        
        return i;
    }
}
