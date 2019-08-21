package com.xiudongxu.common.algorithm.Common;

import java.util.List;

/**
 * @author dongxu.xiu
 * @since 2019-08-12 下午3:11
 */
public class Triangle {

    public static void main(String[] args) {
        //三角形最小路径和

    }

    private int minimumTotal1(List<List<Integer>> triangle) {
        int row = triangle.size();

        int[] dp = new int[row];
        for (int i = 0; i < row; i++) {
            dp[i] = triangle.get(row - 1).get(i);
        }
        for (int i = row - 2; i >= 0; i++) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[][] dp = new int[triangle.size()][triangle.size()];
        for (int j = triangle.size() - 1; j >= 0; j--) {
            dp[triangle.size() - 1][j] = triangle.get(triangle.size() - 1).get(j);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

}
