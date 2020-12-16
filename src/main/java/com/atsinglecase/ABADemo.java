package com.atsinglecase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {//ABA问题解决 加时间戳（版本号）
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("==================以下时ABA问题的产生================");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);//睡眠1秒钟保证线程t1处理完成

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        System.out.println( atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get().toString());
        System.out.println("==================以下时ABA问题的解决=======================");

        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println("第1次版本号"+"\t "+stamp);
            //t3暂停1秒
            try { TimeUnit.SECONDS.sleep(1);
                 } catch (InterruptedException e) {
                e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第2次的版本号" +atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第3次的版本号" +atomicStampedReference.getStamp());
        },"t3").start();
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println("第1次版本号"+"\t "+stamp);
            //t4暂停4秒钟保证t3完成一次ABA
            try { TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace(); }
            boolean result=atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t 修改是否成功 "+result+"\t当前最新版本号"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t 当前实际最新值 "+atomicStampedReference.getReference());
        },"t4").start();
    }
}
