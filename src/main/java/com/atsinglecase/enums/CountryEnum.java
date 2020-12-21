package com.atsinglecase.enums;

import lombok.Getter;

public enum CountryEnum {
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),FOUR(4,"赵国"),FIVE(5,"魏国"),SIX(0,"韩国");

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    @Getter private Integer retCode;
    @Getter private String retMessage;
    public static CountryEnum foreach_CountryEnum(int index){
        CountryEnum[] countryEnums=CountryEnum.values();
        for(CountryEnum element :countryEnums){
            if(index==element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
