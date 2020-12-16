package com.atsinglecase;

import com.atsinglecase.pojo.Person;

public class TestTransFerValue {
    public void changeAge(int age){//基本类型传的复印件 main里面没有改
        age=30;
    }
    public void changePersonName(Person person){
        person.setPersonName("XXX");
    }
    public void changeString(String str){
        str="***";
    }
    public static void main(String[] args) {
        TestTransFerValue test=new TestTransFerValue();
        int age=20;
        test.changeAge(age);
        System.out.println(age);

        Person person=new Person("aa");
        test.changePersonName(person);
        System.out.println(person.getPersonName());

        String str="abc";//字符串常量池
        test.changeString(str);
        System.out.println(str);


    }
}
