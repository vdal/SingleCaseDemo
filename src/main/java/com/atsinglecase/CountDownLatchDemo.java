package com.atsinglecase;

import com.atsinglecase.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/*倒计时计算器
* CountDownLatch主要有两个方法，当一个或多个线程调用await方法是，
* 调用线程会被阻塞，其他线程调用countDown方法会将计数器减1（调用countDown方法线程不会阻塞）
* 当计数器的值变为零时，其调用await方法被阻塞的线程会被唤醒，继续执行
* */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=0;i<6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 被灭");
                countDownLatch.countDown();
            }, CountryEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t **********统一六国");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=0;i<6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 被灭");
                countDownLatch.countDown();
            }, CountryEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t **********统一六国");
    }
}
