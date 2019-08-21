package com.xiudongxu.common.algorithm.DynamicProgramming;

/**
 * @author dongxu.xiu
 * @since 2019-07-07 下午7:47
 */
public class FindTwoMax {

    /**      -----------------------------
     *  arr  | 1 | 2 | 4 | 1 | 7 | 8 | 3 |
     *       -----------------------------
     * index   0   1   2   3   4   5   6
     *
     * 在以上长度的数组中 找求和值最大的方案
     *
     * 要求：选择的数 不能挨着
     * 例如：选择了 index 为0的 就不能选择 index 为1的
     *      选择了 index 为5的 就不能选择 index 为3和5的
     *
     * 提示：OPT(6)
     *
     * |---------------------------------------------|
     * |         以上都是题目，下面是解析               |
     * |---------------------------------------------|
     *
     * --------------------
     * |0.先根据要求 自己推断|
     * --------------------
     *
     *                    OPT(6)
     *
     *             |选|             |不选|
     *
     *       Max(OPT(4)+arr[6]  ,  OPT(5) );
     *
     * -----------------
     * |1.根据推断总结公式|
     * ------------------
     *               --
     *               |选    OPT(i-2) + arr[i]
     * OPT(i) max <--|
     *               |不选  OPT(i-1)
     *               --
     * -----------
     * |2.确定出口|
     * -----------
     *
     * OPT(0) = arr[0]
     * OPT(2) = Max(arr[0], arr[1])
     *
     *
     */


    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 1, 7, 8, 3};
        System.out.println(rec_opt(arr, 6));
        System.out.println(dp_opt(arr));
    }

    public static int rec_opt(int[] arr, int i){
        if( i == 0)
            return arr[0];
        else if( i == 1){
            return Math.max(arr[0], arr[1]);
        }else{
            int a = rec_opt(arr, i - 2) + arr[i];
            int b = rec_opt(arr , i -1);
            return Math.max(a, b);
        }
    }

    public static int dp_opt(int[] arr){
        //1.创建opt数组
        int[] opt = new int[arr.length];
        opt[0] = arr[0];
        opt[1] = Math.max(arr[0], arr[1]);
        //2.类比 斐波那契数列 计算
        for (int i = 2; i < arr.length; i++) {
            int a = opt[i -2] + arr[i];
            int b = opt[i];
            opt[i] = Math.max(a, b);
        }
        return opt[arr.length - 1];
    }
}
