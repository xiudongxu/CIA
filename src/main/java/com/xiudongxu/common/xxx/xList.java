package com.xiudongxu.common.xxx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author dongxu.xiu
 * @since 2019-03-25 上午10:54
 */
public class xList {


    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.size();

        List<String> linkedList = new LinkedList<>();
        linkedList.size();

        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.size();

        List<String> unmodifiableList = Collections.unmodifiableList(copyOnWriteArrayList);
        unmodifiableList.size();
    }
}
