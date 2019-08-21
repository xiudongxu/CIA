package com.xiudongxu.common.algorithm.String;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dongxu.xiu
 * @since 2019-08-07 上午10:34
 */
public class LengthOfLongestSubstring {


    public static void main(String[] args) {
        //双指针法
        System.out.println(Solution2("abcabcbb"));
        System.out.println(Solution2("bbbbb"));
        System.out.println(Solution2("pwwkew"));
    }

    private static int Solution1(String s) {

        int n = s.length();
        Set<Character> set = Sets.newHashSet();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                //如果不存在就继续
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - 1);
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }

    private static int Solution2(String s){
        int n = s.length(), ans = 0;
        // current index of character

        int[] index = new int[128];
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public String Solution3(String s){
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(s.charAt(length-i-1));
        }
        return stringBuilder.toString();
    }

    public String Solution4(String s){
        char[] arrs = s.toCharArray();
        char temp;
        int length = arrs.length;
        for(int i = 0,j = length - 1; i < (length - 1) / 2; i++, j--){
            temp = arrs[i];
            arrs[i] = arrs[j];
            arrs[j] = temp;
        }
        return new String(arrs);
    }
}
