package com.atsinglecase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*线程池做的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务
* ，如果线程数量超过了最大数量超出数量的线程排队等候，等其他线程执行完毕，再冲队列中去除任务来执行
* 主要特点为：线程复用：控制最大并发数：管理线程
* 第一：降低资源消耗，通过重复利用已经创建的线程降低线程创建和销毁带来的消耗
* 第二：提高响应速度，当任务到达时，任务可以不需要的等到线程创建就能立即执行
* 第三：提高线程的可管理性，线程时稀缺资源，如无限制的创建，不仅辉消耗系统资源们还会降低系统的稳定性，
* 使用线程池可以统一的分配，调优和监控
* 底层就是阻塞队列
* */
public class MyThreadPoolDemo {
    /*1、corePoolSize:线程池中常驻核心线程数
    * 2、maximumPoolSize:线程池能够容纳同事执行的最大线程数，此值必须大于等于1
    * 3、keepAlivaTime:多余的空闲线程的存活时间*/
    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());//获取cpu核数
        // ExecutorService threadPool= Executors.newFixedThreadPool(5);
        // ExecutorService threadPool= Executors.newSingleThreadExecutor();
        ExecutorService threadPool= Executors.newCachedThreadPool();
        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程

        try {
            for(int i=0;i<200;i++){

                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
