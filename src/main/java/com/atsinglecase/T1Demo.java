package com.atsinglecase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1Demo {

    /*公平锁：指多线程根据申请锁的顺序来取锁，类似于打饭 先来后到
    * 非公平锁：是指多个线程获取锁的顺序并不是申请锁的顺序，有可能后面申请的线程回比先申请的线程先获取锁
    * 再高并发下，有可能造成优先级反转或者饥饿现象
    * 非公平锁的优点在于吞吐量比公平锁大*/
    public static void main(String[] args) {
        /*关于两者的区别 ：
        * 公平锁 ：就是很公平，在并发环境中每个线程在获取锁时会先查看此锁维护的等待队列，如果为空，
        * 或者当前线城市等待队列的第一个，就占有锁，否则就会加入到等待队列中，
        * 以后会按照FIFO的规则从队列中取到自己
        * 非公平锁：非公平锁比较粗鲁，上来就直接常识占有锁，如果常识失败，就在采用类似公平锁那种方式
        * ReentrantLock和synchronized默认为非公平的可重入锁
        * 可重入锁（也叫递归锁）的最大作用是避免死锁*/
        Lock lock=new ReentrantLock();//并发包中，创建ReentrantLock可以指定构造函数boolean类型来得到公平锁或者非公平锁 true 为公平锁  括号里什么都不写 是非公平锁

    }
}
