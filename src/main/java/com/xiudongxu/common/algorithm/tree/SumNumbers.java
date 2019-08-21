package com.xiudongxu.common.algorithm.tree;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxu.xiu
 * @since 2019-08-12 下午5:35
 */
public class SumNumbers {

    public static void main(String[] args) {
        Solution1();
    }

    private static int Solution1() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        System.out.println(sum(node1, 0));
        return -1;
    }

    private static int sum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        sum = 10 * sum + root.val;
        if(root.left == null && root.right == null){
            return sum;
        }
        return sum(root.left, sum) + sum(root.right, sum);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
