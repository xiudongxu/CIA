package com.xiudongxu.common.algorithm.DynamicProgramming;

/**
 * @author dongxu.xiu
 * @since 2019-07-07 下午8:54
 */
public class FindTarget {


    /**      ---------------------------
     *  arr  | 3 | 34 | 4 | 12 | 5 | 2 |
     *       ---------------------------
     * index   0   1    2   3    4   5
     *
     * target : 9
     *
     * 题目： 在以上数组中找到可以组成 target 的组合  返回true/false
     *
     * --------------------
     * |0.先根据要求 自己推断|
     * --------------------
     *             Subset(arr[5], 9)
     *
     *        选                         不选
     *
     *   Subset(arr[4], 7)   or    Subset(arr[4], 9)
     *
     * -----------------
     * |1.根据推断总结公式|
     * ------------------
     *
     * 选    Subset(arr, i-1, s-arr[i])
     * 不选  Subset(arr, i-1, s)
     *
     *
     * -----------
     * |2.确定出口|
     * -----------
     *
     * 1.  if s == 0  return true;
     * 2.  if i == 0  return arr[i] == s;
     * 3.  if arr[i] > s  return Subset(arr, i-1 s);
     *
     */

    public static void main(String[] args) {

        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println(rec_subset(arr, arr.length - 1 , 9));



    }

    public static boolean rec_subset(int[] arr, int i, int s){
        if( s == 0)
            return true;
        else if( i == 0)
            return arr[i] == s;
        else if( arr[i] > s)
            return rec_subset(arr, i-1, s);
        else{
            return rec_subset(arr , i-1, s-arr[i]) || rec_subset(arr, i-1, s);
        }
    }


    public static boolean dp_subset(int[] arr, int s){
        boolean[][] subset = new boolean[arr.length][s+1];
        //赋值初始值
         return false;
    }

}
