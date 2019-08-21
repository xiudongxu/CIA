package com.xiudongxu.common.algorithm.Math;

/**
 * @author dongxu.xiu
 * @since 2019-07-21 下午12:38
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(sqrt(8));
        System.out.println(sqrt(9));
    }

    public static int sqrt(int y){
        int low = 0, high = y;
        int result = 0;
        while(low <= high){
            int mid = (high + low) / 2 ;
            if(mid * mid <= y){
                low = mid + 1;
                result = mid;
            }else if(mid * mid > y){
                high = mid - 1;
            }
        }
        return result;
    }
}
