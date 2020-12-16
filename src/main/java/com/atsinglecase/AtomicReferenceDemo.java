package com.atsinglecase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.util.concurrent.atomic.AtomicReference;

@Getter//lombok下注解
@ToString
@AllArgsConstructor
class User{
    String name;
    int age;
}
public class AtomicReferenceDemo {//原子引用类型
    public static void main(String[] args) {
        User z3=new User("z3",23);
        User li4=new User("li4",25);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+"/t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"/t"+atomicReference.get().toString());
    }
}
