package com.xiudongxu.common.algorithm.tree.LCA;

import javax.swing.tree.TreeNode;

/**
 * @author dongxu.xiu
 * @since 2019-07-04 下午5:59
 */
public class BinarySearchTree {

    /**
     * 二叉搜索树的 最小公共祖先问题
     */

    //数据结构定义
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        @Override
        public String toString() {
            return "TreeNode [value=" + value + "]";
        }
    }

    public static void main(String[] args) {

        //放树节点的数组
        TreeNode node[] = new TreeNode[7];

        //给节点赋值的数组
        int arr[] = {4, 2, 6, 1, 3, 5, 7};

        //初始化节点完毕
        /*
                 4
                /  \
               2    6
              / \  / \
             1   3 5  7
        */
        //处理引用关系
        //从0开始对节点编号的话，第i个节点的左节点下标为2*i + 1，右节点为2*i + 2
        for (int i = 6; i >= 0; i--) {
            node[i] = new TreeNode();
            node[i].value = arr[i];
            //如果不是叶子节点的话（叶子节点没有子节点），把引用指向叶子节点
            if (i < node.length / 2) {
                node[i].left = node[2 * i + 1];
                node[i].right = node[2 * i + 2];
            }
        }

        //找值为3的节点和值为5的节点的LCA
        TreeNode lca = findLCA(node[4], node[5], node[0]);
        System.out.println(lca.value);
    }

    private static TreeNode findLCA(TreeNode node1, TreeNode node2, TreeNode currentNode) {
        //当前节点的值
        int value = currentNode.value;

        if(value > node1.value && value > node2.value){
            return findLCA(node1,node2,currentNode.left);
        }
        if(value < node1.value && value < node2.value){
            return findLCA(node1,node2,currentNode.right);
        }
        return currentNode;
    }


}
