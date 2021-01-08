package com.atsinglecase;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;

/*使用stream流的方式，遍历集合，对集合中的数据进行过滤
* stream流是jdk1.8之后出现的
* 关注的是做什么而不是怎么做*/
public class StreamDemo {
    public static void main(String[] args) {
       // listForeash();
        /*stream并不会存储数据，只是对数据进行计算*/
        List<String> list =new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream()
                .filter(name->name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));

    }

    private static void listForeash() {
        //创建一个list集合，存储姓名
        List<String> list =new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        //对list集合中的元素进行过滤。，只要张开头的元素，存储到一个新的集合中
        List<String> listA=new ArrayList<>();
        for (String s: list){
            if(s.startsWith("张")){
                listA.add(s);
            }
        }
        //对listA集合进行过滤，只要行明长度为3的人，存储到一个新集合中
        List<String> listB=new ArrayList<>();
        for(String s:listA){
            if(s.length()==3){
                listB.add(s);
            }
        }
        //便利listB集合
        for(String s:listB){
            System.out.println(s);
        }
    }
}
