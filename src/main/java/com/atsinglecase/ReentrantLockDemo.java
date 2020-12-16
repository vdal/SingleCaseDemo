package com.atsinglecase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*可重入锁（递归锁）
* 指的是同一线程外层函数获得锁后，内层递归函数仍然能获取该锁的代码，在同一个线程在外城方法获取锁的时候，
* 在进入内层方法会自动取锁
*
* 也就是说 ，线程可以进入认河一个他已经拥有所得同步代码块
*
* synchronized和ReentrantLock就是一个典型的可重入锁
* */
class Phone  implements Runnable{
    public synchronized  void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"  invoked sendSMS");
        sendEmail();
    }
    public synchronized  void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"  invoked sendEmail");
    }
    Lock lock=new ReentrantLock();
    @Override
    public void run(){
        get();
    }
    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"  invoked get");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"  invoked set");
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone=new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"ti").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread thread=new Thread(phone,"T3");
        Thread thread2=new Thread(phone,"T4");
        thread.start();
        thread2.start();
    }
}
