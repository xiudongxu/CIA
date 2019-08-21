package com.xiudongxu.common.algorithm.Common;

/**
 * @author dongxu.xiu
 * @since 2019-07-24 上午10:32
 */
public class IsAnagram {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram0(s, t));
    }


    private static boolean isAnagram0(String s, String t){
        int[] sCount = new int[26];
        int[] tCount = new int[26];

        for(char ch : s.toCharArray()){
            sCount[ch - 'a']++;
        }

        for(char ch : t.toCharArray()){
            tCount[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if(sCount[i] != tCount[i]){
                return false;
            }
        }

        return true;
    }
}
