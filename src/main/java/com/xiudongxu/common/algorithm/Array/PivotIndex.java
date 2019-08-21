package com.xiudongxu.common.algorithm.Array;

import javassist.bytecode.analysis.FramePrinter;

/**
 * @author dongxu.xiu
 * @since 2019-08-08 下午8:27
 */
public class PivotIndex {


    public static void main(String[] args) {

        int[] nums = new int[]{-1, -1, -1, -1, -1, -1};
        int[] nums1 = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println(Solution2(nums1));

    }
    public static int Solution2(int[] nums){
        int sum = 0, leftTotal = 0, rightTotal = 0;

        for(int n : nums){
            sum = sum + n;
        }

        for (int i = 0; i < nums.length; i++) {
            if(i == 0){
                leftTotal = 0;
            }else{
                leftTotal = leftTotal + nums[i - 1];
            }
            rightTotal = sum - leftTotal - nums[i];

            if(leftTotal == rightTotal){
                return i;
            }
        }
        return -1;
    }

    public static int Solution1(int[] nums){
        if(nums.length == 0){
            return -1;
        }
        int leftTotal = nums[0];
        int rightTotal = nums[nums.length - 1];

        int i = 0, j = nums.length - 1;

        while( i < j){
            if(leftTotal < rightTotal){
                leftTotal += nums[++i];
            }else if(leftTotal > rightTotal){
                rightTotal += nums[--j];
            }else{
                leftTotal += nums[++i];
                rightTotal += nums[--j];
            }
        }

        if(leftTotal == rightTotal){
            return i + 1;
        }else{
            return -1;
        }
    }
}
