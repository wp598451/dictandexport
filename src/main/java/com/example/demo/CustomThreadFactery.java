package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactery implements ThreadFactory {

    /**
     *  线程号
     */
    private final AtomicInteger threadNum = new AtomicInteger(1);

    /**
     *  线程名称
     */
    private String threadName;

    public CustomThreadFactery(String threadName) {
        this.threadName = threadName + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(threadName + threadNum.getAndIncrement());
        return thread;
    }
}