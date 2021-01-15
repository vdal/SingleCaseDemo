package com.atsinglecase;
/*死锁是只两个或两个以上的进程在执行过程中，
因争夺资源而造成的互相等待的现象
若无外力干涉那他们都姜无法推进下去
*
*
* */

class HoldLockThread implements  Runnable
{
    private String lockA;
    private String lockB;
    public HoldLockThread(String locakA,String lockB){
        this.lockA=locakA;
        this.lockB=lockB;
    }
    @Override
    public void run(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
        }
    }
}

public class DeadLockDemo  {
    public static void main(String[] args) {

    }
}
