package com.atsinglecase;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*多线程抢多个资源，停车场*/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);//模拟三个停车位
        for (int i = 0; i <6 ; i++) {//模拟6辆汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    try{TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 停车三秒后释放车位");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
