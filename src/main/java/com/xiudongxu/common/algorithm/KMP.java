package com.xiudongxu.common.algorithm;

/**
 * @author dongxu.xiu
 * @since 2019-02-21 上午11:45
 */
public class KMP {



    public static void main(String[] args) {
    }

    //获取next数组
    public static int[] getNext(String ps){
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;

        int j = 0;
        int k = -1;
        while(j < p.length -1 ){
            if(k == -1 || p[j] == p[k]){
                next[++j] = ++k;
            }else{
                k = next[k];
            }
        }
        return next;
    }
    //进行KMP匹配
    public static int KMP(String ts,String ps){
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();

        int i = 0;
        int j = 0;

        int[] next = getNext(ps);

        while(i < t.length && j < p.length){
            if(j == -1 || t[i] == p[j]){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j == p.length){
            return i - j;
        }
        return -1;
    }
}
