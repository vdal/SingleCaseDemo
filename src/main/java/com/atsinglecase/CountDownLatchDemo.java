package com.atsinglecase;

import com.atsinglecase.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/*倒计时计算器*/
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
