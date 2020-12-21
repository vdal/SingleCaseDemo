package com.atsinglecase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*读写锁
 * 多个线程同时读取一个资源类没有任何问题 ，为了满足并发量，读取共享资源应该可以同事进行
 * 如果有一个线程想去写共享资源类，那么就不应该有其他线程对共享资源类读或写
 * 小总结
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 不能共存*/
class  MyCache{

   private volatile Map<String,Object> map=new HashMap<>();
   private ReentrantReadWriteLock writeLock=new ReentrantReadWriteLock();

    public  void put(String key,Object value){
        writeLock.writeLock().lock();
        try{
            System.out.println( Thread.currentThread().getName()+"\t 正在写入 " +key);
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception ex){
            ex.printStackTrace();
        }
       finally {
            writeLock.writeLock().unlock();

        }

    }
    public void get(String key){
      writeLock.readLock().lock();
      try{
          System.out.println( Thread.currentThread().getName()+"\t 正在去读 " +key);
          try{
              TimeUnit.MILLISECONDS.sleep(300);
          }catch (InterruptedException e){e.printStackTrace();}
          Object result=map.get(key);
          System.out.println(Thread.currentThread().getName()+"\t 读取完成");
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          writeLock.readLock().unlock();
      }
    }
  /*  public void clearMap(){
        map.clear();
    }*/
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 0; i <5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
