package com.xiudongxu.common.algorithm.Array;

/**
 * @author dongxu.xiu
 * @since 2019-08-10 上午10:45
 */
public class FindDiagonalOrder {


    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        arr[0] = new int[]{1, 2, 3};
        arr[1] = new int[]{4, 5, 6};
        arr[2] = new int[]{7, 8, 9};
        System.out.println(findDiagonalOrder(arr));
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        // m 是 行边界 n是列边界
        int m = matrix.length;
        int n = matrix[0].length;
        int[] order = new int[m * n];
        int row = 0;
        int col = 0;
        //存储方向改变值，右上，或者左下
        int[][] dirs = {{-1, 1},
                {1, -1}};
        int k = 0;
        for (int i = 0; i < order.length; i++) {
            //将当前坐标赋值给新数组
            order[i] = matrix[row][col];
            //计算下一个点的坐标
            row += dirs[k][0];
            col += dirs[k][1];
            //根据边界条件，修正下一个点的坐标值.触碰边界，必然对方向取反
            //右上方向碰到边界
            if (col > n - 1) {
                col = n - 1;
                row += 2;
                //方向取反
                k = 1 - k;
            }
            if (row < 0) {
                row = 0;
                k = 1 - k;
            }
            //左下方向碰到边界
            if (row > m - 1) {
                row = m - 1;
                col += 2;
                k = 1 - k;
            }
            if (col < 0) {
                col = 0;
                k = 1 - k;
            }
        }
        return order;
    }
}
