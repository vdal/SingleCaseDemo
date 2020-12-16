package com.atsinglecase;


import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Condition;
import java.util.stream.Stream;

/*线程不安全因素 number++,单例模式，ArrayList(默认长度是10) map 和set  也是线程不安全的集合类*/
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
       // listNotSafe();
       // setNoSafe();
       // Map<String, String> map=new HashMap<>();
       // Map<String, String> map=Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map= new ConcurrentHashMap();
        for(int i=1;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }

    private static void setNoSafe() {
        //new HashSet<>()底层就是hasMap
        //Set<String> set=new HashSet<>();
        // Set<String> set=Collections.synchronizedSet(new HashSet<>());
        Set<String> set=new CopyOnWriteArraySet<>();
        for(int i=1;i<30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();

        }
    }

    /*高并发下list不安全*/
    private static void listNotSafe() {
    /*   Stream.of("Will I eat the apple?".split(" "))
               .map(w -> w + " ")
               .peek(System.out::println)
               .map(String::toUpperCase)
               .peek(System.out::println)
               .map(String::toLowerCase)
               .forEach(System.out::println);  流式编程*/
//        List<String> list= Arrays.asList("a","b","c","d");
//        list.forEach(System.out::println);
//        list.stream().forEach(item->{
//            System.out.println("item: "+item);
//        });
//        list.stream().filter(item->item=="a");stream 流式编程
//        list.forEach((t) -> System.out.println(t));
//        list.forEach((String t) -> System.out.println(t));
        // List<String> list=new Vector<>();vector加了锁 并发性下降 一致性又保证
        //List<String> list=new ArrayList<>();
        List<String> list= new CopyOnWriteArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list+"\t"+Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException  ArrayList在高并发多线程线程不安全的常见问题
        /*
           1. 故障现象
           java.util.ConcurrentModificationException  ArrayList在高并发多线程线程不安全的常见问题
           2. 异常原因
             并发争抢导致 参考花名册签名情况
             一个人正在写入，另一个人进行抢夺，导致数据不一致异常，并发修改一场
           3.解决方案
              (1) new Vector
              (2) Collections.synchronizedList(new ArrayList<>());
              (3) CopyOnWriteArrayList 写时复刻
           */
        /*笔记
* 写实复刻
* CopyOnWrite容器即写实复刻容器，往一个容器添加元素的时候，不直接往当前容器object[]添加，而是先将当前容器object[]进行copy
* 复制出一个新的容器object[] newElements,然后新的容器object[] newElements里面添加元素，添加玩元素之后再将原容器的引用指向新的容器setArray(newElements)
* 这样做的好处是可以对CopyOnWrite容器进行并发的读而不是需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，
* 读和写不同的容器
public boolean add(E e)
{
final ReentrantLock local=this.lock;
lock.lock();
 try{
  Object[] elements=getArray();
  int len =elements.length;
  Object[] newElements=Arrays.copyOf(elements,len+1);
  newElements[len]=e;
  setArray(newElements);
  return true;
 }
 finally{
  local.unlock();
 }
}
* */
    }
}


