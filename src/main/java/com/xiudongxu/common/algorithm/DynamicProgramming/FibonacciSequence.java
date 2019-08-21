package com.xiudongxu.common.algorithm.DynamicProgramming;

/**
 * @author dongxu.xiu
 * @since 2019-07-07 下午2:51
 */
public class FibonacciSequence {


    /**
     * 使用斐波那契数列 来入门DP
     *       ----------------
     * value:|1 1 2 3 5 8 13|
     *       ----------------
     * index:|0 1 2 3 4 5 6 |
     *       ----------------
     */
    public static void main(String[] args) {
        System.out.println(dp_fib(5));
        System.out.println(rec_fib(5));
    }


    public static int rec_fib(int n) {
        if (n == 0)
            return 1;
        else if(n == 1)
            return 1;
        else{
            return rec_fib(n-1) + rec_fib(n-2);
        }
    }

    public static int dp_fib(int n){
        int i = 2;
        int first,second = 1;
        int result = 2;
        while (i < n){
            first = second;
            second = result;
            result = first + second;
            i++;
        }
        return result;
    }
}
