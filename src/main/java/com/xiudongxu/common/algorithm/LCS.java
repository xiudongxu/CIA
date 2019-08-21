package com.xiudongxu.common.algorithm;

/**
 * @author dongxu.xiu
 * @since 2019-02-22 下午3:35
 */
public class LCS {


    public static void main(String[] args) {

        String str1 = "fjssharpsword";
        String str2 = "helloworld";
        //计算lcs递归矩阵
        int[][] re = longestCommonSubsequence(str1, str2);
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                System.out.println(re[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        print(re,str1,str2,str1.length(),str2.length());
    }

    //返回两个字符串的最长公共子序列的长度
    public static int[][] longestCommonSubsequence(String str1,String str2){
        //建立二维矩阵
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        //初始化边界条件
        for (int i = 0; i <= str1.length(); i++) {
            matrix[i][0] = 0;//每行第一列都置零
        }
        for (int i = 0; i <= str2.length(); i++) {
            matrix[0][i] = 0;//每列第一行置零
        }
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                }else{
                    matrix[i][j] = matrix[i-1][j] > matrix[i][j-1] ? matrix[i-1][j] : matrix[i][j-1];
                }
            }
        }
        return matrix;
    }

    public static void print(int[][] opt,String x,String y,int i,int j){
        if(i == 0 || j == 0){
            return ;
        }
        if(x.charAt(i-1) == y.charAt(j-1)){
            print(opt,x,y,i-1,j-1);
            System.out.println(x.charAt(i-1));
        }else if(opt[i-1][j] >= opt[i][j]){
            print(opt,x,y,i-1,j);
        }else{
            print(opt,x,y,i,j-1);
        }
    }
}
