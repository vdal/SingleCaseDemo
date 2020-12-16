package com.atsinglecase;
///高并发单例子模式
public class SingleDemo {
    private static volatile SingleDemo instance=null;
    public SingleDemo(){
        System.out.printf(Thread.currentThread().getName()+"\t 我是构造方法SingleApplication（）");
    }
    //DCL（double check lock双端检锁）包含指令重排volatile禁止指令重排
    public static SingleDemo getInstance(){
        if(instance==null){
            //锁定代码块
            synchronized (SingleDemo.class){
                if(instance==null){
                    instance=new SingleDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {

        for(int  i=0;i<10;i++){
            new Thread(()->{
                SingleDemo.getInstance();
            },String.valueOf(i)).start();
        }

        //        System.out.println(SingleApplication.getInstance()==SingleApplication.getInstance());
//        System.out.println(SingleApplication.getInstance()==SingleApplication.getInstance());
//        System.out.println(SingleApplication.getInstance()==SingleApplication.getInstance());
    }
}
