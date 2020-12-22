package com.atsinglecase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
/*ArrayBlockingQueue 是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出） 原则对元素进行排序
* LinkedBlockingDeque 一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素 吞吐量通常高于ArrayBlockingQueue
* SynchronousQueue 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常高于ArrayBlockingQueue
*
* 1.队列
* 2.阻塞队列   当阻塞队列是空时，从队列中获取元素的操作会被阻塞 当阻塞队列时满时，往队列里添加元素的操作会被阻塞
*   2.1阻塞队列没有好的一面
*   2.2不得不阻塞，如何管理
*  */

public class BlockingQueueDemo {
    public static void main(String[] args) {

    }
}
