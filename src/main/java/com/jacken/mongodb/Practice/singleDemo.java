package com.jacken.mongodb.Practice;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class singleDemo {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(new ThreadRunner(i+1)).start();
        }

        long l1 = System.currentTimeMillis();
        System.out.println("单线程共耗时："+(l1-l)+"毫秒");
    }
}
