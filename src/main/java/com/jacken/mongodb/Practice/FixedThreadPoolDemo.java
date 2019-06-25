package com.jacken.mongodb.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i = 0 ; i < 50 ; i++){
            executorService.execute(new ThreadRunner((i + 1)));

        }

        long l1 = System.currentTimeMillis();
        System.out.println("使用线程池共耗时："+(l1-l)+"毫秒");
        executorService.shutdown();
    }
}
