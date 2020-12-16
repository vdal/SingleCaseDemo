package com.atsinglecase;

import java.util.concurrent.atomic.AtomicInteger;

/*
* 为什么用cas不用synchronized 后者同一时间段只允许一个线程访问 一致性得到保证 并发性下降
* cas 核心用unsafe 和自旋锁底层用do while进行比较没有加锁 既保证一致性又保证并发性
* cas （Compare And Swap） 比较并交换  比较当前工作内存的值和主内存的值 如果相同则执行指定操作
* 否则则继续比较主内存和工作内存的值 知道一致为止
* cas应用 有三个值 内存值v 旧的预期值a 和要修改的值b 当且仅当预期值a内存值v相同时 将v 修改为b 否则什么也不做
* cas 缺点 1.循环时间长开销大（do while 如果cas失败则一直进行尝试，如果cas长时间不成功则会对cpu带来大的开销）
*          2.只能保证一个共享变量的原子操作（对多个共享变量造作是,循环cas就无法保证操作的原子性 这个时候就可以用锁来保证原子性）
*          3.引出来ABA问题  假设两个线程 一个线程one  一个线程two  同时从主物理内存取出值A  two线程操作快 先将改为B 又将B改为A 这是著物理内存得值是A
*  one线程这时进行第一次操作比较并修改时会比较成功 会成功将A会修改为要修改的值 虽然操作成功单并不代表过程没有问题 因为two线程进行了多次操作（只管开头和尾巴 不管过程）
* */
public class CasDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger=new AtomicInteger(1);//原子整型
        atomicInteger.compareAndSet(1,5);
        System.out.println(atomicInteger.get());
    }
}
