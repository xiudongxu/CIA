package com.xiudongxu.common.algorithm.tree.LCA;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dongxu.xiu
 * @since 2019-07-04 下午4:34
 */
public class WithoutParentNode {

    static final int maxNumOfChilds = 10;
    //使用两个链表来保存根节点到所求节点的路径
    static LinkedList list1 = new LinkedList();
    static LinkedList list2 = new LinkedList();

    /**
     * 这里面树可以 不是 二叉树
     * 用两个链表来保存
     */

    public static void main(String[] args) {
                 /*
                  A
                 / \
                B   C
               /  \
               D    E
             / \   / \
            F   G  H  J
           */
        //找G,H节点的LCA
        List<TreeNode1> list = buildTree();
        TreeNode1 root = list.get(0);
        TreeNode1 G = list.get(1);
        TreeNode1 H = list.get(2);
        TreeNode1 lca = findLCA(G, H, root);
        System.out.println(lca);
    }

    private static boolean getPathFromRootToNode(TreeNode1 node, TreeNode1 currentRoot, LinkedList list) {
        //找到就直接返回true
        if(node.value == currentRoot.value){
            return true;
        }
        //找不到就将当前节点加入路径，push是在链表的头插入的 offer是尾部
        list.push(currentRoot);
        boolean found = false;
        TreeNode1[] childs = currentRoot.childs;
        if(childs != null && childs.length > 0){
            //遍历当前节点的所有节点，在子节点里面找
            for (int i = 0; i < childs.length; i++) {
                if(found){
                    break;
                }else{
                    found = getPathFromRootToNode(node,childs[i],list);
                }
            }
        }

        //找不到就将当前节点从路径中删除，因为是递归，当递归回到这里的时候，当前节点一定是list的最后一个节点  即栈顶
        if(!found){
            list.pop();
        }

        return found;
    }

    private static TreeNode1 findLCA(TreeNode1 node1, TreeNode1 node2, TreeNode1 root) {

        //组建了两个链表
        getPathFromRootToNode(node1,root,list1);
        getPathFromRootToNode(node2,root,list2);

        //list1 : D -- B -- A
        //list2 : E -- B -- A

        int index = 0;
        int length1 = list1.size();
        int length2 = list2.size();

        //计算长度差值  目前是0
        int sub = length1 > length2 ? length1 - length2 : length2 - length1;

        if(length2 > length1){
            LinkedList temp = list1;
            list1 = list2;
            list2 = temp;
        }

        //遍历到最大值
        while(index != length2 - 1){
            if(((TreeNode1)list1.get(index+sub)).value == ((TreeNode1)list2.get(index)).value){
                return (TreeNode1)list2.get(index);
            }else{
                index++;
            }
        }

        return null;
    }

    private static List<TreeNode1> buildTree() {
        /*
                  A
                 / \
                B   C
               /  \
               D    E
             / \   / \
            F   G  H  J
           */
        //找G,H节点的LCA
        TreeNode1 A = new TreeNode1();
        A.value = 'A';
        TreeNode1 B = new TreeNode1();
        B.value = 'B';
        TreeNode1 C = new TreeNode1();
        C.value = 'C';
        TreeNode1 D = new TreeNode1();
        D.value = 'D';
        TreeNode1 E = new TreeNode1();
        E.value = 'E';
        TreeNode1 F = new TreeNode1();
        F.value = 'F';
        TreeNode1 G = new TreeNode1();
        G.value = 'G';
        TreeNode1 H = new TreeNode1();
        H.value = 'H';
        TreeNode1 I = new TreeNode1();
        I.value = 'I';
        TreeNode1 J = new TreeNode1();
        J.value = 'J';
        A.childs = new TreeNode1[]{B, C};
        B.childs = new TreeNode1[]{D, E};
        D.childs = new TreeNode1[]{F, G};
        E.childs = new TreeNode1[]{H, J};
        List<TreeNode1> list = Lists.newArrayList();
        list.add(A);
        list.add(G);
        list.add(H);
        return list;
    }
}
