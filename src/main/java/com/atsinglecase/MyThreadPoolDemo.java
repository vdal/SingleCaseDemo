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
    * 3、keepAlivaTime:多余的空闲线程的存活时间
    * 当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，
    * 多余空闲线程会被销毁直到只剩下corePoolSize个线程为止
    *4、unit:keepAlivaTime的单位
    *5、workQueue:任务队列，被提交但尚未被执行的任务。
    *6、threadFactory:表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可
    *7、handler :拒绝策略，表示当前队列满了并且工作线程大院等于线程池最大线程数
    *
    * JDK内置的拒绝策略
    * AbortPolicy(默认)：直接抛出RejectedExcutionException异常阻止系统正常运行。
    * CallerRunsRolicy:"调用者运行" 一种调节机制，改策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用方
    * DiscaedOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队列中常识再次提交当前任务
    * DiscardPolicy:直接丢弃任务，不予任何处理也不抛异常，如果允许任务丢失 这是最好的一种
    *
    *
    * 配置线程数
    * CPU密集型=核数+1个线程的线程池（CPU密集型的意思是该任务需要大量的运算，而且没有阻塞，CPU一直全速运行、需要多核cpu）
    * IO密集型 （1）由于IO密集型任务线程并不是一直在执行任务,则应配置尽可能多的线程，如CPU核数*2
    *          （2）IO密集型时，大部分线程都阻塞，故需要配置线程数：
    *                 参考工时:CPU核数/1-阻塞系数   阻塞系数在0.8-0.9之间  入股8核CPU：8/(1-0。9)=80个线程数
    *
    * */
    public static void main(String[] args) {
       // ThreadPoolInit();
    }

    private static void ThreadPoolInit() {
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
