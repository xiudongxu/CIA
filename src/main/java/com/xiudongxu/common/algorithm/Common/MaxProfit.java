package com.xiudongxu.common.algorithm.Common;

/**
 * @author dongxu.xiu
 * @since 2019-07-24 上午10:42
 */
public class MaxProfit {
    //股票买卖的最佳时机


    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};


    }


    private static int maxProfit0(int[] prices) {
        if(prices == null || prices.length < 1 ){
            return -1;
        }
        //这里面需要两个变量 一个用来记录最小值  一个用来记录最大和

        int min = prices[0];
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < min){
                min = prices[i];
            }else{
                if((prices[i] - min) > maxProfit){
                    maxProfit = (prices[i] - min);
                }
            }
        }
        return maxProfit;
    }


}
