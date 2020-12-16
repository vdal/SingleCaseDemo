package com.atsinglecase.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Person {
    private Integer age;
    private String personName;
    public Person(String personName){
        this.personName=personName;
    }
}
