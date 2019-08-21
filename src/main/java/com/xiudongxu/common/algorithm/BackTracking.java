package com.xiudongxu.common.algorithm;

/**
 * @author dongxu.xiu
 * @since 2019-02-23 下午1:06
 */
public class BackTracking {

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("1");

        int[] str = new int[2];
        int length = str.length;
        String s = "abcd";
        pailie(s,"");
//        System.out.println( s.substring(0,1)+s.substring(2,s.length()));
//        System.out.println(s.charAt(0));


    }

    public static void pailie(String s,String temp){
         if(s.length() == 0){
            System.out.println(temp);
            return ;
        }
        for (int i = 0; i < s.length(); i++) {
            String news=s.substring(0, i)+s.substring(i+1,s.length());//去掉String中的某个字母
            String s1 = s.charAt(i) + temp;
            pailie(news, s1);
        }

    }
}
