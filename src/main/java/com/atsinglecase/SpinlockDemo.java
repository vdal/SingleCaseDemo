package com.atsinglecase;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*自旋锁：是尝试获取所得线程不会立即阻塞，而是采用循环的方式获取锁，没有类似wait的阻塞
这样的好处是减少线程上下文切换的消耗，坏处是循环会消耗cpu
题目 实现一个自旋锁
自旋的本质do while +cas
*/
public class SpinlockDemo {
    AtomicReference<Thread> threadAtomicReference=new AtomicReference<>();//()里没有值默认为Null
    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"come in here");
        while(!threadAtomicReference.compareAndSet(null,thread)){
          System.out.println(Thread.currentThread().getName()+"occupy lock" );
        }
    }
    public void unLock(){
        Thread thread=Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"invoke unLock");
    }
    public static void main(String[] args) {
        SpinlockDemo spinlockDemo=new SpinlockDemo();
        new Thread (()->{
            spinlockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            spinlockDemo.unLock();
        },"AA").start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        new Thread(()->{
            spinlockDemo.myLock();
            spinlockDemo.unLock();
        },"BB").start();//b线程通过不断地循环来获取锁是否可用
    }
}
