package com.atsinglecase;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*加法计数器*/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("***************召唤神龙");
        });
        for (int i = 0; i <7 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                System.out.println( Thread.currentThread().getName()+"\t 收集到第："+tempInt+"龙珠");
                try{
                    cyclicBarrier.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException ex){
                    ex.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
