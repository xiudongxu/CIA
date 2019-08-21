package com.xiudongxu.common.algorithm.search;

/**
 * @author dongxu.xiu
 * @since 2019-07-04 下午5:26
 */
public class BinarySearch {

    public static void main(String[] args) {
        //初始化一个有序的数组
        int[] arr = {1, 3, 6, 8, 9};
        System.out.println(binarySearch(arr,6));
    }

    private static int binarySearch(int[] arr, int target) {

        if (arr == null || arr.length == 0) return -1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high){
            int mid = high - (high - low) / 2;
            if(arr[mid] ==  target){
                return mid;
            }else if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }
}
