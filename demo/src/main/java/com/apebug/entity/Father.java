package com.apebug.entity;

public class Father {
    {
        System.out.println("父类 非静态代码块a");
    }
    static {
        System.out.println("父类 静态代码块");
    }
    
    public Father() {
        System.out.println("父类 构造函数");
    }
    
    {
        System.out.println("父类 非静态代码块b");
    }
}
