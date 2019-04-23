package com.apebug.entity;

/**
 * @author EX-liuzhenyu001
 * @ClassName: Son
 * @Description:
 * @date 2019/4/23 15:59
 */

public class Son extends Father{
    {
        System.out.println("子类代码块a");
    }
    static {
        System.out.println("子类静代码块");
    }
    
    public Son() {
        System.out.println("子类 构造函数");
    }
    
    {
        System.out.println("子类代码块b");
    }
}
