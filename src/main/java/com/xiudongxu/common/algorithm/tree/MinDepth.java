package com.xiudongxu.common.algorithm.tree;

import javafx.util.Pair;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author dongxu.xiu
 * @since 2019-08-12 上午10:52
 */
public class MinDepth {

    public static void main(String[] args) {


    }

    //深度优先搜索迭代
    private static int minDepth(TreeNode root){
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if(root == null){
            return 0;
        }else{
            stack.add(new Pair<>(root, 1));
        }
        int minDepth = Integer.MAX_VALUE;
        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int currentDepth = current.getValue();
            //说明是叶子节点
            if((root.left == null) && (root.right == null)){
                minDepth = Math.min(minDepth, currentDepth);
            }

        }
        return -1;

    }

    class TreeNode{

        public int val;
        public TreeNode left;
        public TreeNode right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
