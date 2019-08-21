package com.xiudongxu.common.algorithm.Common;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @author dongxu.xiu
 * @since 2019-08-08 上午11:31
 */
public class TwoSum {
    //方法1.双层循环
    //方法2.排序双指针下标
    //方法3.map 一次遍历

    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] nums, int target){
        int[] lable = new int[2];
        HashMap<Integer, Integer> hashMap = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(target - nums[i]) && hashMap.get(target - nums[i]) != i){
                lable[0] = i;
                lable[1] = hashMap.get(target - nums[i]);
                break;
            }
        }

        return lable;
    }
}
