package com.xiudongxu.common.CAS;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author dongxu.xiu
 * @since 2019-05-17 下午6:37
 */
public class NonBlock {

    private static volatile NonBlock nonBlock;

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static NonBlock getInstance(){
        if(nonBlock == null){
            if(atomicBoolean.compareAndSet(false,true)){
                nonBlock = new NonBlock();
            }
        }
        return nonBlock;
    }

}
