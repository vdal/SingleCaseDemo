package com.atsinglecase;
/*

JDK中预定义一些注解
   @Override:检测被该注解标注的方法是否是继承自父类
   @Deprecated: 该注解标注的内容，表示已经过时
   @SuppressWarnings ：压制警告
* */
@SuppressWarnings("all")//压制所有警告
public class AnnotationDemo {
    public void demo(){
        show1();
    }
    @Override
   public String toString(){
        return super.toString();
    }
    @Deprecated
    public void show1(){
        //有缺陷
    }

    public void show2(){
        //替代show1方法
    }
}

