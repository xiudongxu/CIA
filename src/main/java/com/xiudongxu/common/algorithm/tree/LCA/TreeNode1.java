package com.xiudongxu.common.algorithm.tree.LCA;

/**
 * @author dongxu.xiu
 * @since 2019-07-04 下午4:32
 */
public class TreeNode1 {

    char value;
    private String a;
    private String b;
    private String c;
    private String d;
    TreeNode1 childs[];

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public TreeNode1[] getChilds() {
        return childs;
    }

    public void setChilds(TreeNode1[] childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "TreeNode1 [value=" + value + "]";
    }
}
