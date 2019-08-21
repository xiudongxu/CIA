package com.xiudongxu.common.algorithm.List;

/**
 * @author dongxu.xiu
 * @since 2019-07-05 上午10:30
 */
public class MergeList {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 合并两个有序链表 之 递归方式
     */
    //递归方式建立
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = null;

        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists1(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists1(l1, l2.next);
        }
        return head;
    }

    /**
     * 合并两个有序链表 之 普通方式
     */
    //普通方式建立
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        //设置一个新的链表（合并后）的头结点
        ListNode temp = new ListNode(-1);
        //result代表返回的头结点
        ListNode result = temp;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
                temp = temp.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
                temp = temp.next;
            }
        }
        if (l1 == null) {
            temp.next = l2;
        }
        if (l2 == null) {
            temp.next = l1;
        }
        return result.next;
    }

    /**
     * 合并 k 有序链表
     *
     * 方法一：非常辣鸡 不写了  把所有的都存到数组里面 然后排序，再生成链表。
     * 方法二：先写出对两个链表进行合并的方法，然后采用归并 将链表两两合并。
     *
     */

    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 1){
            return lists[0];
        }else if(lists == null){
           return null;
        }
        return MSort(lists, 0, lists.length - 1);
    }

    public ListNode MSort(ListNode[] lists, int low,int high){
        if (low < high){
            int mid = (low + high) / 2;
            ListNode leftList = MSort(lists, low, mid);
            ListNode rightList = MSort(lists, mid, high);
            return mergeTwoLists1(leftList,rightList);
        }else if(low == high){
            return lists[low];
        }else{
            return null;
        }
    }
}